package edu.unifor.clysman;

import edu.unifor.clysman.Immovel.Immovel;
import edu.unifor.clysman.Immovel.Type;
import edu.unifor.clysman.Report.ImmovelReport;
import edu.unifor.clysman.Repository.ImmovelRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ImmovelRepository immovelRepository = new ImmovelRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de imóveis: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("Imóvel " + i);
            System.out.println("Digite o nome do dono: ");
            String owner = scanner.next();
            System.out.println("Digite a área do imóvel: ");
            double area = scanner.nextDouble();
            System.out.println("Digite o valor de venda: ");
            double sellPrice = scanner.nextDouble();
            System.out.println("Digite o tipo do imóvel(1 Apartamento, 2 Casa): ");
            Type type = Type.values()[scanner.nextInt() - 1];
            System.out.println("É em uma área nobre(0 Não, 1 Sim): ");
            boolean isNobreArea = (scanner.nextInt() == 1);
            System.out.println("Digite o ano de construção: ");
            int constructionYear = scanner.nextInt();

            Immovel immovel = new Immovel(owner, area, sellPrice, type, isNobreArea, constructionYear);
            immovelRepository.save(immovel);
        }

        ImmovelReport immovelReport = new ImmovelReport(immovelRepository);
        immovelReport.generate();
    }
}
