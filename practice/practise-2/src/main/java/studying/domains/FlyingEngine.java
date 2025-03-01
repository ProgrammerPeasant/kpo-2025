package studying.domains;

import lombok.ToString;
import studying.interfaces.IEngine;

@ToString
public class FlyingEngine implements IEngine {

    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
