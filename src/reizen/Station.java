package reizen;

public class Station {

    // De naam van het station
    private final String naam;

    // Constructor: maakt een nieuw station aan met een naam
    public Station(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public String toString() {
        return "Station{" +
                "naam='" + naam + '\'' +
                '}';
    }
}


