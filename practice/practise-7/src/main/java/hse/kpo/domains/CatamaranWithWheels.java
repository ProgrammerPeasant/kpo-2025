package hse.kpo.domains;

public class CatamaranWithWheels extends Car {
    private final Catamaran catamaran;

    public CatamaranWithWheels(Catamaran catamaran, int carNumber) {
        super(carNumber, catamaran.getEngine());
        this.catamaran = catamaran;
    }

    @Override
    public boolean isCompatible(Customer customer) {
        return catamaran.isCompatible(customer);
    }
}

