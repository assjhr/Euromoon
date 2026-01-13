package Treinen;

import java.util.ArrayList;
import java.util.List;

//Een trein bestaat uit 1 locomotief en een lijst wagons
public class Trein {

    //Locomotief die de trein aandrijft
    private Locomotief locomotief;

    //Lijst van wagons die aan de trein gekoppeld zijn
    private List<Wagon> wagons;

    //Constructor: maakt een trein aan met een bepaalde locomotief
    //De lijst van wagons start leeg, maar kan maximaal getMaxWagons wagons bevatten
    public Trein(Locomotief locomotief) {
        this.locomotief = locomotief;
        this.wagons = new ArrayList<>(); // start met lege lijst


        //Het aantal wagons wordt automatisch bepaald door het type locomotief
        int aantalWagons = locomotief.getMaxWagons();

        // Vul de lijst van wagons met het exacte aantal wagons
        //Eerste 2 wagons = Eerste klasse, rest = Tweede klasse
        for (int i = 0; i < aantalWagons; i++) {
            if(i < 2) {
                wagons.add(new Wagon(Klasse.EERSTE_KLASSE));
            }else{
                wagons.add(new Wagon(Klasse.TWEEDE_KLASSE));// Maak een nieuwe Wagon aan
            }
        }
    }

    //Getter voor de locomotief
    public Locomotief getLocomotief() {
        return locomotief;
    }

    //Getter voor de lijst van wagons
    public List<Wagon> getWagons() {
        return wagons;
    }
}


