package hse.kpo.factories.cars;

import hse.kpo.domains.Car;
import hse.kpo.domains.CatamaranWithWheels;
import hse.kpo.interfaces.cars.CarFactory;
import hse.kpo.params.CatamaranWithWheelsEngineParams;
import org.springframework.stereotype.Component;

@Component
public class CatamaranWithWheelsFactory implements CarFactory<CatamaranWithWheelsEngineParams> {
    @Override
    public Car create(CatamaranWithWheelsEngineParams params, int carNumber) {
        return new CatamaranWithWheels(params.catamaran(), carNumber);
    }
}