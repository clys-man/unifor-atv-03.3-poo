package edu.unifor.clysman.Impost;

import edu.unifor.clysman.Immovel.Immovel;

abstract class Impost {
    private final Immovel immovel;

    public Impost(Immovel immovel) {
        this.immovel = immovel;
    }

    public abstract double calculate();

    public Immovel getImmovel() {
        return immovel;
    }
}
