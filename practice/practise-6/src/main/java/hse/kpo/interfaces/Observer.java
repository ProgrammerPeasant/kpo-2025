package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Report;
import hse.kpo.enums.ProductionTypes;

// Interface to observe and handle sales-related activities
public interface SalesObserver {

    /**
     * Generates a report based on sales data.
     *
     * @return A `Report` object containing sales statistics and insights.
     */
    Report buildReport();

    /**
     * Handles the sale event.
     * This method is called whenever a sale occurs.
     *
     * @param customer The customer who made the purchase.
     * @param productType The type of product that was sold (e.g., a specific production type).
     * @param vin A unique identifier for the purchased item (e.g., Vehicle Identification Number).
     */
    void onSale(Customer customer, ProductionTypes productType, int vin);

    /**
     * Analyzes and verifies customer data.
     * This method could be used to check customer activity, validate their details,
     * or ensure they are eligible for specific promotions or offers.
     */
    void checkCustomers();

}