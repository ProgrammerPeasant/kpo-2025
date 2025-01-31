package studying.factories;

import studying.domains.Car;
import studying.domains.FlyingEngine;
import studying.interfaces.ICarFactory;
import studying.params.FlyingEngineParams;

public class FlyingCarFactory implements ICarFactory<FlyingEngineParams> {
    @Override
    public Car createCar(FlyingEngineParams carParams, int carNumber) {
        var engine = new FlyingEngine();

        return new Car(carNumber, engine);
    }
}