package edu.unifor.clysman.Impost;

import edu.unifor.clysman.Immovel.Immovel;
import edu.unifor.clysman.Immovel.Type;

public class IPTU extends Impost {
    public IPTU(Immovel immovel) {
        super(immovel);
    }

    @Override
    public double calculate() {
        double percentage = 1.0;
        Immovel immovel = this.getImmovel();

        if (immovel.type() == Type.APARTMENT && immovel.area() > 100) {
            percentage = 0.05;
        } else if (immovel.type() == Type.APARTMENT && immovel.area() <= 100) {
            percentage = 0.02;
        } else if (immovel.type() == Type.HOUSE && immovel.area() > 100) {
            percentage = 0.03;
        } else if (immovel.type() == Type.HOUSE && immovel.area() <= 100) {
            percentage = 0.01;
        }

        return immovel.sellPrice() * percentage;
    }
}
