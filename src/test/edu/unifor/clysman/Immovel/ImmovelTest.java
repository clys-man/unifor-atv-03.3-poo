package edu.unifor.clysman.Immovel;

import edu.unifor.clysman.TestCase;

public class ImmovelTest extends TestCase {
    public void testIPTU() {
        String owner = "owner";
        double area = 100.0;
        double sellPrice = 1000.0;
        Type type = Type.HOUSE;
        boolean isNobreArea = true;
        int constructionYear = 2000;

        Immovel immovel = new Immovel(owner, area, sellPrice, type, isNobreArea, constructionYear);

        assertEquals(10.0, immovel.iptu());
    }

    public void testWhenImovelIsNobreAreaAndAreaIsGreaterThan100SalePriceIs180Percent() {
        Immovel immovel = new Immovel("owner", 200.0, 1000.0, Type.HOUSE, true, 2000);

        double salePrice = immovel.salePrice();

        assertEquals(1800.0, salePrice);
    }

    public void testWhenImovelIsNobreAreaAndAreaIsLessThanOrEquals100SalePriceIs110Percent() {
        Immovel immovel = new Immovel("owner", 50.0, 1000.0, Type.HOUSE, true, 2000);

        double salePrice = immovel.salePrice();

        assertEquals(1100.0, salePrice);
    }

    public void testWhenImovelIsNotNobreAreaAndAreaIsGreaterThan100SalePriceIs120Percent() {
        Immovel immovel = new Immovel("owner", 200.0, 1000.0, Type.HOUSE, false, 2000);

        double salePrice = immovel.salePrice();

        assertEquals(1200.0, salePrice);
    }

    public void testWhenImovelIsNotNobreAreaAndAreaIsLessThanOrEquals100SalePriceIs105Percent() {
        Immovel immovel = new Immovel("owner", 50.0, 1000.0, Type.HOUSE, false, 2000);

        double salePrice = immovel.salePrice();

        assertEquals(1050.0, salePrice);
    }
}
