package studying;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@RequiredArgsConstructor
public class FactoryAF {
    // Фабрика в целом, как паттерн, реализаует принципы полиморфизма и инкапсуляции
    private int carNumber;

    private List<Car> cars = new ArrayList<>();

    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    } // Пример ассоциации

    public void addCar(int engineSize) {
        var number = ++carNumber;

        cars.add(new Car(number, engineSize)); // Пример композиции
        // Инкапсуляция, логика конструктора машины сокрыта от пользователя
    }

    public void saleCar() {
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    if (!cars.isEmpty()) {
                        customer.setCar(cars.getFirst());
                        cars.removeFirst();
                    }
                });

        cars.clear(); // Весь цикл представляет собой реализацию
    }

    public void printCars() {
        cars.stream().map(Car::toString).forEach(System.out::println);
    }

    public void printCustomers() {
        customers.stream().map(Customer::toString).forEach(System.out::println);
    }

    // Полиморфизм, в процессее выполнения становиться известно какой конкретно метод будет вызвано от фабрики
    // При этом заранее изветно, например, что фабрика может печатать машины или печатать покупателей, добавлять их соотвественно
}
