package hse.kpo.interfaces;

public interface AbstractProductionFactory<TParams> {

    public abstract ICarFactory<TParams> createCarFactory();

    public abstract ICatamaranFactory<TParams> createCatamaranFactory();

}
