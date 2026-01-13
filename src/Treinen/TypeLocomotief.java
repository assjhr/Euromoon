package Treinen;


// Enum die de verschillende types locomotieven definieert
// Elke locomotief heeft een maximum aantal wagons
// Het voordeel van een enum: types zijn vastgelegd en makkelijk uit te breiden
public enum TypeLocomotief {

    CLASS_373(12),
    CLASS_374(14);


    // Aantal wagons dat deze locomotief maximaal kan trekken
    private int maxWagons;

    // Constructor van de enum, wordt gebruikt om maxWagons in te stellen
    TypeLocomotief(int maxWagons) {
        this.maxWagons = maxWagons;
    }

    // Getter om het maximum aantal wagons van dit type op te vragen
    public int getMaxWagons() {
        return maxWagons;
    }
}
