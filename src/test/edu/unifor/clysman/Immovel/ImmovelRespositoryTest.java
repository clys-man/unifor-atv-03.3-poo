package edu.unifor.clysman.Immovel;

import edu.unifor.clysman.TestCase;
import edu.unifor.clysman.Repository.ImmovelRepository;

import java.util.Comparator;

public class ImmovelRespositoryTest extends TestCase {
    public void testSave() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);

        assertEquals(1, immovelRepository.findAll().size());
    }

    public void testWhere() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 1000, Type.APARTMENT, true, 2010);
        Immovel immovel2 = new Immovel("Pedro", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);
        immovelRepository.save(immovel2);

        assertEquals(1, immovelRepository.where(i -> i.owner().equals("João")).count());
    }

    public void testSort() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 100, Type.APARTMENT, true, 2010);
        Immovel immovel2 = new Immovel("Pedro", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);
        immovelRepository.save(immovel2);

        assertEquals(immovel2, immovelRepository.sort(Comparator.comparing(Immovel::sellPrice), "desc").first());
    }

    public void testCount() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 100, Type.APARTMENT, true, 2010);
        Immovel immovel2 = new Immovel("Pedro", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);
        immovelRepository.save(immovel2);

        assertEquals(2, immovelRepository.count());
    }

    public void testFirst() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 100, Type.APARTMENT, true, 2010);
        Immovel immovel2 = new Immovel("Pedro", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);
        immovelRepository.save(immovel2);

        assertEquals(immovel, immovelRepository.first());
    }

    public void testDelete() {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Immovel immovel = new Immovel("João", 100, 100, Type.APARTMENT, true, 2010);
        Immovel immovel2 = new Immovel("Pedro", 100, 1000, Type.APARTMENT, true, 2010);

        immovelRepository.save(immovel);
        immovelRepository.save(immovel2);
        immovelRepository.delete(immovel);

        assertEquals(immovel2, immovelRepository.first());
    }
}
