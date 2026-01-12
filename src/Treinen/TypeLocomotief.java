package Treinen;

public enum TypeLocomotief {

    CLASS_373(12),
    CLASS_374(14);


    private int maxWagons;

    TypeLocomotief(int maxWagons) {
        this.maxWagons = maxWagons;
    }

    public int getMaxWagons() {
        return maxWagons;
    }
}
