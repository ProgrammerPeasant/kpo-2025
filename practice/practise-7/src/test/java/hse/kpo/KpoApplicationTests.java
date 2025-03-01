package hse.kpo;

import hse.kpo.facade.Hse;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KpoApplicationTests {

	@Autowired
	private Hse hse;

	@Autowired
	private HandCatamaranFactory handCatamaranFactory;

	@Test
	@DisplayName("Тест загрузки контекста")
	void contextLoads() {

		Assertions.assertNotNull(hse);
	}

	@Test
	@DisplayName("Тест загрузки контекста")
	void hseCarServiceTest() {
		hse.addCustomer("Ivan1", 6, 4, 100);
		hse.addCustomer("Maksim", 4, 6, 110);
		hse.addCustomer("Petya", 6, 6, 120);
		hse.addCustomer("Nikita", 4, 4, 90);

		hse.addPedalCar(6);
		hse.addPedalCar(6);
		hse.addHandCar();
		hse.addHandCar();

		hse.addHandCar();
		hse.addHandCatamaranWithWheels();

		hse.sell();

		System.out.println(hse.generateReport());
	}
}
