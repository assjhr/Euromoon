import personen.Passagier;
import personen.Personeel;
import personen.PersoneelTypes;

import reizen.Reis;
import reizen.Station;
import reizen.NodigePersoneel;

import treinen.*;

import tickets.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // STATIONS AANMAKEN
        Station brussel = new Station("Brussel");
        Station amsterdam = new Station("Amsterdam");


        // TREIN AANMAKEN
        // Locomotief type (bepaalt max wagons)
        Locomotief loco = new Locomotief(TypeLocomotief.CLASS_373);

        // Trein met die locomotief
        Trein trein = new Trein(loco);

        // REIS AANMAKEN
        Reis reis = new Reis(
                brussel,
                amsterdam,
                LocalDateTime.of(2026, 1, 20, 9, 30),
                trein
        );


        // PERSONEEL AANMAKEN

        // Bestuurder
        Personeel bestuurder = new Personeel(
                "Jan",
                "Peeters",
                "01.01.01-001.01",
                LocalDate.of(1985, 5, 10),
                PersoneelTypes.BESTUURDER
        );

        // Stewards
        Personeel steward1 = new Personeel("Sara", "Janssens", "02", LocalDate.of(1990,1,1), PersoneelTypes.STEWARD);
        Personeel steward2 = new Personeel("Tom", "Vermeulen", "03", LocalDate.of(1991,2,2), PersoneelTypes.STEWARD);
        Personeel steward3 = new Personeel("Lina", "De Smet", "04", LocalDate.of(1992,3,3), PersoneelTypes.STEWARD);

        // Personeel toewijzen aan de reis
        reis.getNodigePersoneel().stelBestuurderIn(bestuurder);
        reis.getNodigePersoneel().voegStewardToe(steward1);
        reis.getNodigePersoneel().voegStewardToe(steward2);
        reis.getNodigePersoneel().voegStewardToe(steward3);


        // PASSAGIERS AANMAKEN
        Passagier p1 = new Passagier("Assia", "Jouhri", "11", LocalDate.of(2004, 10, 20));
        Passagier p2 = new Passagier("Amin", "Jouhri", "12", LocalDate.of(2001, 7, 17));


        // TICKETS VERKOPEN
        try {
            Ticket t1 = reis.verkoopTicket(p1, Klasse.TWEEDE_KLASSE);
            Ticket t2 = reis.verkoopTicket(p2, Klasse.EERSTE_KLASSE);

            System.out.println("Uw aankop werd goed ontvangen");
            System.out.println(t1);
            System.out.println(t2);

        } catch (IllegalStateException e) {
            // Als de trein vol is
            System.err.println("Fout bij ticketverkoop: " + e.getMessage());
        }


        // OVERZICHT
        System.out.println("\nOverzicht reis:");
        System.out.println(reis);

        System.out.println("Aantal tickets verkocht: " + reis.getTickets().size());
        System.out.println("Eerste klasse tickets: " + reis.aantalTicketsInKlasse(Klasse.EERSTE_KLASSE));
        System.out.println("Tweede klasse tickets: " + reis.aantalTicketsInKlasse(Klasse.TWEEDE_KLASSE));
    }
}