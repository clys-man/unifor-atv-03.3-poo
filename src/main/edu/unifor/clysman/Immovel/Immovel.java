package edu.unifor.clysman.Immovel;

import edu.unifor.clysman.Impost.IPTU;

import java.time.Year;

public record Immovel(
        String owner,
        double area,
        double sellPrice,
        Type type,
        boolean isNobreArea,
        int constructionYear
) {
    public Immovel {
        if (area < 0) {
            throw new IllegalArgumentException("Area must be greater than 0");
        }
        if (sellPrice < 0) {
            throw new IllegalArgumentException("Sell price must be greater than 0");
        }
        if (constructionYear < 0 || constructionYear > Year.now().getValue()) {
            throw new IllegalArgumentException("Construction year must be greater than 0 and less than current year");
        }
    }

    public double iptu() {
        return new IPTU(this).calculate();
    }

    public double salePrice() {
        double percentage = 1.0;

        if (this.isNobreArea() && this.area() > 100) {
            percentage = 1.8;
        } else if (this.isNobreArea() && this.area() <= 100) {
            percentage = 1.1;
        } else if (!this.isNobreArea() && this.area() > 100) {
            percentage = 1.2;
        } else if (!this.isNobreArea() && this.area() <= 100) {
            percentage = 1.05;
        }

        return this.sellPrice() * percentage;
    }

    public int age() {
        return Year.now().getValue() - this.constructionYear();
    }

    public String toString() {
        return String.format(
                "Dono: %s, Area: %.2f, Preco de venda: %.2f, Tipo: %s, Área Nobre: %s, ITPU: %.2f, Ano de construcao: %d",
                this.owner(),
                this.area(),
                this.sellPrice(),
                this.type(),
                this.isNobreArea() ? "Sim" : "Nao",
                this.iptu(),
                this.constructionYear()
        );
    }
}
