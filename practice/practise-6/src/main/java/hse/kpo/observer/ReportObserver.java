import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import hse.kpo.domains.Customer;
import hse.kpo.domains.Report;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.SalesObserver;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.ReportBuilder;

/**
 * Implementation of the SalesObserver interface to handle sales events
 * and generate reports based on customer activities.
 */
@Component
@RequiredArgsConstructor
public class ReportSalesObserver implements SalesObserver {

    // Dependency for handling customer-related operations
    private final CustomerStorage customerStorage;

    // Dependency for report building
    private final ReportBuilder reportBuilder;

    /**
     * Creates and returns a sales report.
     *
     * @return A fully built Report object containing the collected data.
     */
    @Override
    public Report buildReport() {
        return reportBuilder.build();
    }

    /**
     * Handles a sales transaction.
     * Records the sale event with details about the customer and the product.
     *
     * @param customer    The customer who made the purchase.
     * @param productType The type of the product sold.
     * @param vin         The unique identifier of the product (e.g., Vehicle Identification Number).
     */
    @Override
    public void onSale(Customer customer, ProductionTypes productType, int vin) {
        String message = String.format(
                "Sale: %s with VIN-%d to customer %s (Hand Power: %d, Leg Power: %d, IQ: %d)",
                productType, vin, customer.getName(),
                customer.getHandPower(), customer.getLegPower(), customer.getIq()
        );
        reportBuilder.addOperation(message);
    }

    /**
     * Processes and evaluates the current state of customers.
     * Adds customer data to the report.
     */
    @Override
    public void checkCustomers() {
        reportBuilder.addCustomers(customerStorage.getCustomers());
    }
}