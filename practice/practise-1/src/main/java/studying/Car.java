package studying;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Car {

    private Engine engine;

    @Getter
    private int VIN;

    public Car(int VIN, int engineSize) {
        this.VIN = VIN;
        this.engine = new Engine(engineSize);
    } // Пример копозиции или строгой связи, объект Car управляет жизненным циклом Engine, помимо этого
    // Нет возможности подставить свой созданный в другом месте объект

}
