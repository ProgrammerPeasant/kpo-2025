package studying;

import studying.domains.Car;
import studying.domains.Customer;
import studying.domains.HandEngine;
import studying.domains.PedalEngine;
import studying.factories.FlyingCarFactory;
import studying.factories.HandCarFactory;
import studying.factories.PedalCarFactory;
import studying.params.EmptyEngineParams;
import studying.params.FlyingEngineParams;
import studying.params.PedalEngineParams;
import studying.services.CarService;
import studying.services.CustomerStorage;
import studying.services.HseCarService;


public class Main {
    public static void main(String[] args) {
        System.out.println("HSE");

        CarService carService = new CarService();

        CustomerStorage customerStorage = new CustomerStorage();

        HseCarService hseCarService = new HseCarService(carService, customerStorage);
        PedalCarFactory pedalCarFactory = new PedalCarFactory();
        HandCarFactory handCarFactory = new HandCarFactory();
        FlyingCarFactory flyingCarFactory = new FlyingCarFactory();

        Customer first = new Customer("fisrt", 6, 4, 120);
        Customer second = new Customer("second", 4, 6, 150);
        Customer third = new Customer("third", 6, 6, 90);
        Customer fourth = new Customer("fourth", 4, 4, 100);
        Customer fifth = new Customer("fifth", 2, 2, 320);

        customerStorage.addCustomer(first);
        customerStorage.addCustomer(second);
        customerStorage.addCustomer(third);
        customerStorage.addCustomer(fourth);
        customerStorage.addCustomer(fifth);

        carService.addCar(pedalCarFactory, new PedalEngineParams(5));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(handCarFactory, new EmptyEngineParams());
        carService.addCar(handCarFactory, new EmptyEngineParams());
        carService.addCar(flyingCarFactory, new FlyingEngineParams());

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
        hseCarService.sellCars();
        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);


    }
}
