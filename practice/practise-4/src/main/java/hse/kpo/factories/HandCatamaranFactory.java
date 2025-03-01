package hse.kpo.factories;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.ICatamaranFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

@Component
public class HandCatamaranFactory implements ICatamaranFactory<EmptyEngineParams> {
    @Override
    public Catamaran createCatamaran(EmptyEngineParams catamaranParams, int catamaranNumber) {
        var engine = new HandEngine();

        return new Catamaran(catamaranNumber, engine);
    }
}