package hse.kpo.services;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.interfaces.ICatamaranFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @Test
    void testTakeCar() {
        Car car1 = mock(Car.class);
        Car car2 = mock(Car.class);
        Customer customer = mock(Customer.class);

        carService.addCar((params, number) -> car1, null);
        carService.addCar((params, number) -> car2, null);

        Car takenCar = carService.takeCar(customer);

        assertEquals(car1, takenCar);

        takenCar = carService.takeCar(customer);
        assertEquals(car2, takenCar);

        takenCar = carService.takeCar(customer);
        assertNull(takenCar);
    }

    @Test
    void testAddCar() {
        ICarFactory<String> carFactory = mock(ICarFactory.class);

        Car newCar = mock(Car.class);

        when(carFactory.createCar(any(), eq(1))).thenReturn(newCar);

        carService.addCar(carFactory, "car_params_1");

        when(carFactory.createCar(any(), eq(2))).thenReturn(newCar);
        carService.addCar(carFactory, "car_params_2");

        verify(carFactory, times(1)).createCar("SomeCarParams", 1);
        verify(carFactory, times(1)).createCar("SomeOtherParams", 2);

        Customer customer = mock(Customer.class);
        when(newCar.isCompatible(customer)).thenReturn(true);

        assertEquals(newCar, carService.takeCar(customer));
    }
}