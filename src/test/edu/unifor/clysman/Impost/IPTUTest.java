package edu.unifor.clysman.Impost;

import edu.unifor.clysman.Immovel.Immovel;
import edu.unifor.clysman.Immovel.Type;
import edu.unifor.clysman.TestCase;

public class IPTUTest extends TestCase {
    public void testWhenImmovelIsApartmentAndAreaIsGreaterThan100IPTUIs5Percent() {
        Immovel immovel = new Immovel("owner", 200.0, 1000.0, Type.APARTMENT, true, 2000);
        IPTU iptu = new IPTU(immovel);

        double iptuValue = iptu.calculate();

        assertEquals(50.0, iptuValue);
    }

    public void testWhenImmovelIsApartmentAndAreaIsLessThanOrEquals100IPTUIs2Percent() {
        Immovel immovel = new Immovel("testes", 50.0, 1000.0, Type.APARTMENT, true, 2000);
        IPTU iptu = new IPTU(immovel);

        double iptuValue = iptu.calculate();

        assertEquals(20.0, iptuValue);
    }

    public void testWhenImmovelIsHouseAndAreaIsGreaterThan100IPTUIs3Percent() {
        Immovel immovel = new Immovel("owner", 200.0, 1000.0, Type.HOUSE, true, 2000);
        IPTU iptu = new IPTU(immovel);

        double iptuValue = iptu.calculate();

        assertEquals(30.0, iptuValue);
    }

    public void testWhenImmovelIsHouseAndAreaIsLessThanOrEquals100IPTUIs1Percent() {
        Immovel immovel = new Immovel("testes", 50.0, 1000.0, Type.HOUSE, true, 2000);
        IPTU iptu = new IPTU(immovel);

        double iptuValue = iptu.calculate();

        assertEquals(10.0, iptuValue);
    }
}
