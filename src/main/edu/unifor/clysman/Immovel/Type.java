package edu.unifor.clysman.Immovel;

public enum Type {
    HOUSE(1), APARTMENT(2);

    private final int value;

    Type(int i) {
        if (i < 1 || i > 2) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = i;
    }

    public  String toString() {
        return switch (this.value) {
            case 1 -> "Casa";
            case 2 -> "Apartamento";
            default -> throw new IllegalStateException("Unexpected value: " + this.value);
        };
    }
}
