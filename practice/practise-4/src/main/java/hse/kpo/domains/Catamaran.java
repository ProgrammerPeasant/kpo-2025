package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Catamaran {
    private IEngine engine;

    @Getter
    private int VIN;

    /**
     *
     * @param VIN
     * @param engine
     */
    public Catamaran(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer);
    }
}
