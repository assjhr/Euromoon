package treinen;

import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse stelt een trein voor.
 * Een trein bestaat uit één locomotief en een lijst van wagons.
 * Het type van de locomotief bepaalt hoeveel wagons de trein heeft.
 * De totale capaciteit van de trein bedraagt 80 personen en wordt
 * verdeeld over de wagons van eerste en tweede klasse.
 * Deze klasse berekent ook de beschikbare capaciteit per reisklasse
 * en wordt gebruikt bij de verkoop van tickets.
 */
public class Trein {

    //Locomotief die de trein aandrijft
    private Locomotief locomotief;

    //Lijst van wagons die aan de trein gekoppeld zijn
    private List<Wagon> wagons;

    //Constructor: maakt een trein aan met een bepaalde locomotief
    //De lijst van wagons start leeg, maar kan maximaal getMaxWagons wagons bevatten
    public  Trein(Locomotief locomotief) {
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
    //geeft de totale capaciteit van de trein terug
    public int getTotaleCapaciteit(){
        return locomotief.getCapaciteit();
    }

    //telt hoeveel wagons er zijn van een bepaalde klasse
    public int getAantalWagonsVanKlasse(Klasse klasse){
        int teller = 0;

        //we lopen alle wagons af en tellen die met de juiste klasse
        for (Wagon w : wagons) {
            if(w.getKlasse() == klasse){
                teller++;
            }
        }
        return teller;
    }

    //berekent hoeveel plaatsen er zijn voor een bepaalde klasse
    //we verdelen de 80 plaatsen gelijk over alle wagons
    public int getCapaciteitVoorKlasse(Klasse klasse){

        //totale plaatsen (80)
        int totaal = getTotaleCapaciteit();

        //totaal aantal wagons
        int aantalWagons = wagons.size();

        //veiligheid; als er geen wagons zij , kan je niets verdelen
        if (aantalWagons == 0){
            return 0;
        }

        int plaatsenPerWagon = totaal / aantalWagons;
        int rest = totaal % aantalWagons;
        int wagonsInKlasse = getAantalWagonsVanKlasse(klasse);
        int capaciteit = wagonsInKlasse * plaatsenPerWagon;

        if (klasse == Klasse.TWEEDE_KLASSE){
            capaciteit += rest;
        }
        return capaciteit;
    }

}


