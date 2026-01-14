package reizen;

import personen.Passagier;
import treinen.Trein;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tickets.Ticket;
import treinen.Klasse;
/**
 * Deze klasse stelt een reis voor tussen twee stations.
 * Een reis heeft een vertrekstation, een bestemmingsstation
 * en een vertrektijd  .
 * Aan een reis kan later een trein gekoppeld worden en
 * wordt het nodige personeel toegewezen.
 * Daarnaast houdt deze klasse alle verkochte tickets bij
 * en zorgt ze ervoor dat er niet meer tickets verkocht
 * worden dan de beschikbare capaciteit van de trein.
 */
public class Reis {

    //vertrekstation
    private Station vertrek;

    //bestemmingsstation
    private Station bestemming;

    //vertrektijd van de reis (datum + uur)
    private LocalDateTime vertrektijd;

    //trein die de reis uitvoert
    private Trein trein;

    //personeel dat nodig is voor deze reis
    private NodigePersoneel nodigePersoneel;

    //lijst met alle verkochte tickets voor deze reis
    private List<Ticket> tickets;

    public Reis(Station vertrek, Station bestemming, LocalDateTime vertrektijd) {
        this.vertrek = vertrek;
        this.bestemming = bestemming;
        this.vertrektijd = vertrektijd;
        this.trein = null;

        // Bij elke nieuwe reis start je met een leeg personeelsobject
        // (later stel je bestuurder in en voeg je stewards toe)
        this.nodigePersoneel = new NodigePersoneel();
        this.tickets = new ArrayList<>();
    }

    public Station getVertrek() {
        return vertrek;
    }

    public Station getBestemming() {
        return bestemming;
    }

    public LocalDateTime getVertrektijd() {
        return vertrektijd;
    }

    public Trein getTrein() {
        return trein;
    }

    public NodigePersoneel getNodigePersoneel() {
        return nodigePersoneel;
    }

    @Override
    public String toString() {
        return "Reis{" +
                "vertrek=" + vertrek +
                ", bestemming=" + bestemming +
                ", vertrektijd=" + vertrektijd +
                ", trein=" + trein +
                '}';
    }
    // Geeft de lijst van tickets terug (handig voor boarden of overzicht in de CLI)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTrein(Trein trein) {
        this.trein = trein;
    }

    // Verkoopt een ticket voor deze reis aan een passagier in een bepaalde klasse.
// Deze methode voorkomt dat je meer tickets verkoopt dan er plaatsen zijn.
    public Ticket verkoopTicket(Passagier passagier, Klasse klasse) {

        if (trein == null) {
            throw new IllegalStateException("Er is nog geen trein gekoppeld aan deze reis.");
        }
        // Controle: is er nog plaats in de gekozen klasse?
        if (!heeftPlaatsVrij(klasse)) {
            // We gooien een fout zodat de CLI dit kan opvangen met try/catch
            throw new IllegalStateException("Geen plaatsen meer beschikbaar in " + klasse);
        }

        // Ticket object aanmaken
        Ticket ticket = new Ticket(passagier, this, klasse);

        // Ticket opslaan in de lijst
        tickets.add(ticket);

        // Ticket teruggeven (handig om af te drukken)
        return ticket;
    }

    // Controleert of er nog een vrije plaats is in de gekozen klasse
    public boolean heeftPlaatsVrij(Klasse klasse) {

        // We tellen hoeveel tickets al verkocht zijn in deze klasse
        int verkocht = aantalTicketsInKlasse(klasse);
        int capaciteit = trein.getCapaciteitVoorKlasse(klasse);

        return verkocht < capaciteit;
    }

    // Telt hoeveel tickets verkocht zijn binnen een bepaalde klasse
    public int aantalTicketsInKlasse(Klasse klasse) {
        int teller = 0;

        // We lopen alle tickets af en tellen enkel die van de gevraagde klasse
        for (Ticket t : tickets) {
            if (t.getKlasse() == klasse) {
                teller++;
            }
        }

        return teller;
    }
}
