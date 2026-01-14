package tickets;

import personen.Passagier;
import reizen.Reis;
import treinen.Klasse;
/**
 * Deze klasse stelt een ticket voor.
 * Een ticket koppelt een passagier aan een specifieke reis
 * en bevat informatie over de gekozen reisklasse
 * (eerste of tweede klasse).
 * Tickets worden aangemaakt bij de verkoop van een reis
 * en worden gebruikt voor boarding en controle.
 */

public class Ticket {

    //de passagier die het ticket koopt
    private Passagier passagier;

    //de reis waarvoor het ticket geldt
    private Reis reis;

    //1e of 2e klasse
    private Klasse klasse;

    public Ticket(Passagier passagier, Reis reis, Klasse klasse) {
        this.passagier = passagier;
        this.reis = reis;
        this.klasse = klasse;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public Reis getReis() {
        return reis;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passagier=" + passagier +
                ", reis=" + reis +
                ", klasse=" + klasse +
                '}';
    }
}

