package treinen;

/**
 * Deze enum stelt de verschillende types locomotieven voor.
 * Elk type locomotief bepaalt het maximum aantal wagons
 * dat aan de trein gekoppeld kan worden.
 * Door gebruik te maken van een enum zijn de mogelijke types
 * vastgelegd  en kan het systeem eenvoudig uitgebreid worden.
 */
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
