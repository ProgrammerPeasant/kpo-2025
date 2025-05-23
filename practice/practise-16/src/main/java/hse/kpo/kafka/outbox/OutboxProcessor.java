package hse.kpo.kafka.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.kafka.CustomerAddedEvent;
import hse.kpo.kafka.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@EnableScheduling
public class OutboxProcessor {
    private static final Logger logger = LoggerFactory.getLogger(OutboxProcessor.class);

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Autowired
    public OutboxProcessor(
            OutboxEventRepository outboxEventRepository,
            KafkaProducerService kafkaProducerService,
            ObjectMapper objectMapper) {
        this.outboxEventRepository = outboxEventRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 5000)
    public void processOutboxEvents() {
        List<OutboxEvent> unsentEvents = outboxEventRepository.findAllBySentFalseOrderByCreatedAtAsc();

        for (OutboxEvent event : unsentEvents) {
            try {
                processEvent(event);
            } catch (Exception e) {
                logger.error("failed event id={}: {}", event.getId(), e.getMessage(), e);
            }
        }
    }

    @Transactional
    public void processEvent(OutboxEvent event) {
        try {

            if ("CustomerAdded".equals(event.getEventType())) {
                CustomerAddedEvent customerEvent = objectMapper.readValue(
                        event.getPayload(),
                        CustomerAddedEvent.class
                );

                kafkaProducerService.sendCustomerToTraining(customerEvent);

                event.setSent(true);
                outboxEventRepository.save(event);
            }
        } catch (Exception e) {
            logger.error("error processing outbox event id={}", event.getId(), e);
            throw new RuntimeException("failed to proc event: " + e.getMessage(), e);
        }
    }
}
