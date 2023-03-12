package edu.unifor.clysman.Report;

import edu.unifor.clysman.Immovel.Immovel;
import edu.unifor.clysman.Immovel.Type;
import edu.unifor.clysman.Repository.ImmovelRepository;

import java.util.Comparator;

public class ImmovelReport {
    private final ImmovelRepository immovelRepository;

    public ImmovelReport(ImmovelRepository immovelRepository) {
        this.immovelRepository = immovelRepository;
    }

    public void generate() {
        System.out.println("==================================");
        System.out.println("Relatório de imóveis");
        System.out.println("Total de imóveis: " + this.immovelRepository.count());
        printIPTUAndSalePriceOfAllImmovels();
        printOwnerOfImmovelsWithAgeGreaterThan10();
        printQtyOfApartmentsAndHouses();
        printLowestIPTUOfNotNobreAreaHouse();
        printAreaOfImmovelWithLowestSalePrice();
    }

    private void printIPTUAndSalePriceOfAllImmovels() {
        System.out.println("IPTU e valor de venda de todos os imóveis:");
        immovelRepository.get().forEach(immovel -> {
            System.out.println("IPTU: " + immovel.iptu());
            System.out.println("Valor de venda: " + immovel.salePrice());
        });
    }

    private void printOwnerOfImmovelsWithAgeGreaterThan10() {
        System.out.println("Proprietários dos imóveis com mais de 10 anos:");
        immovelRepository
                .where(immovel -> immovel.age() > 10)
                .get()
                .forEach(immovel -> System.out.println("Proprietário: " + immovel.owner()));
    }

    private void printQtyOfApartmentsAndHouses() {
        System.out.println("Quantidade de apartamentos e casas:");
        long qtyOfApartments = immovelRepository
                .where(immovel -> immovel.type() == Type.APARTMENT)
                .count();
        long qtyOfHouses = immovelRepository
                .where(immovel -> immovel.type() == Type.HOUSE)
                .count();
        System.out.println("Apartamentos: " + qtyOfApartments);
        System.out.println("Casas: " + qtyOfHouses);
    }

    private void printLowestIPTUOfNotNobreAreaHouse() {
        Immovel lowestIPTUHouse = immovelRepository
                .where(immovel -> !immovel.isNobreArea() && immovel.type() == Type.HOUSE)
                .sort(Comparator.comparingDouble(Immovel::iptu))
                .first();

        if (lowestIPTUHouse != null) {
            System.out.println("Imóvel com o menor IPTU:");
            System.out.println("IPTU: " + lowestIPTUHouse.iptu());
        }
    }

    private void printAreaOfImmovelWithLowestSalePrice() {
        Immovel lowestSalePriceImmovel = immovelRepository
                .sort(Comparator.comparingDouble(Immovel::salePrice))
                .first();

        if (lowestSalePriceImmovel != null) {
            System.out.println("Área do imóvel com o menor valor de venda:");
            System.out.println("Área: " + lowestSalePriceImmovel.area());
        }
    }
}
