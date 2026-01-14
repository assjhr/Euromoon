package treinen;

// Deze klasse stelt een locomotief voor die een trein kan aandrijven
public class Locomotief {

    //Het type van de locomotief (bepaalt o.a. het maximum aantal wagons)
    //Final: het type kan niet veranderen nadat de locomotief is aangemaakt
    private final TypeLocomotief type;

    //Elke locomotief heeft volgens de opdracht een vaste capaciteit van 80 personen
    private static final int CAPACITEIT = 80;

    //Constructor: maakt een locomotief aan op basis van een bepaald type
    public Locomotief(TypeLocomotief type) {
        this.type = type;
    }

    //Geeft het type van de locomotief terug
    public TypeLocomotief getType() {
        return type;
    }

    //Geeft het maximum aantal wagons terug
    //Deze waarde wordt bepaald door het type locomotief
    public int getMaxWagons(){
        return type.getMaxWagons();
    }

    //Geeft de capaciteit van de locomotief terug (vast: 80 personen)
    public int getCapaciteit() {
        return CAPACITEIT;
    }


}
