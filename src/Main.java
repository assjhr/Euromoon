import personen.Passagier;
import reizen.Reis;
import reizen.Station;
import treinen.Klasse;
import treinen.Locomotief;
import treinen.Trein;
import treinen.TypeLocomotief;
import tickets.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Deze klasse bevat het startpunt van de applicatie.
 * Via een keuzemenu in de console kan de gebruiker:
 * - passagiers registreren
 * - reizen aanmaken
 * - een trein koppelen aan een reis
 * - tickets verkopen
 * - een boardinglijst afdrukken naar een tekstbestand
 * De applicatie bewaart alle objecten tijdelijk in lijsten tijdens het uitvoeren.
 */
public class Main {

    private static final List<Passagier> passagiers = new ArrayList<>();
    private static final List<Reis> reizen = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            toonMenu();
            String keuze = sc.nextLine().trim();

            switch (keuze) {
                case "1":
                    registreerPassagier(sc);
                    break;
                case "2":
                    maakReisAan(sc);
                    break;
                case "3":
                    koppelTreinAanReis(sc);
                    break;
                case "4":
                    verkoopTicket(sc);
                    break;
                case "5":
                    drukBoardinglijstAf(sc);
                    break;
                case "0":
                    running = false;
                    System.out.println("Applicatie afgesloten.");
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }

        sc.close();
    }

//MENU
    private static void toonMenu() {
        System.out.println("\n EUROMOON MENU");
        System.out.println("1. Registreren passagier");
        System.out.println("2. Aanmaken reis");
        System.out.println("3. Trein koppelen aan reis");
        System.out.println("4. Ticket verkopen aan passagier");
        System.out.println("5. Afdrukken boardinglijst (tekstbestand)");
        System.out.println("0. Stoppen");
        System.out.print("Maak een keuze: ");
    }

//PASSAGIER REGISTREREN
    private static void registreerPassagier(Scanner sc) {
        System.out.println("\n Passagier registreren");

        System.out.print("Naam: ");
        String naam = sc.nextLine().trim();

        System.out.print("Achternaam: ");
        String achternaam = sc.nextLine().trim();

        System.out.print("Rijksregisternummer: ");
        String rijksNr = sc.nextLine().trim();

        System.out.print("Geboortedatum (YYYY-MM-DD): ");
        String datumStr = sc.nextLine().trim();

        try {
            LocalDate geboortedatum = LocalDate.parse(datumStr);

            Passagier p = new Passagier(naam, achternaam, rijksNr, geboortedatum);
            passagiers.add(p);

            System.out.println("Passagier geregistreerd.");
        } catch (Exception e) {
            System.out.println("Fout: geboortedatum moet in formaat YYYY-MM-DD zijn.");
        }
    }

    //REIS AANMAKEN
    private static void maakReisAan(Scanner sc) {
        System.out.println("\n--- Reis aanmaken ---");

        System.out.print("Vertrekstation: ");
        String vertrekNaam = sc.nextLine().trim();

        System.out.print("Bestemmingsstation: ");
        String bestemmingNaam = sc.nextLine().trim();

        System.out.print("Vertrektijd (YYYY-MM-DDTHH:MM) bv. 2026-03-05T12:30: ");
        String tijdStr = sc.nextLine().trim();

        try {
            LocalDateTime vertrektijd = LocalDateTime.parse(tijdStr);

            Station vertrek = new Station(vertrekNaam);
            Station bestemming = new Station(bestemmingNaam);

            Reis r = new Reis(vertrek, bestemming, vertrektijd);
            reizen.add(r);

            System.out.println("Reis aangemaakt (zonder trein gekoppeld).");
        } catch (Exception e) {
            System.out.println("Fout: vertrektijd moet in formaat YYYY-MM-DDTHH:MM zijn.");
        }
    }

    //TREIN KOPPELEN AAN REIS
    private static void koppelTreinAanReis(Scanner sc) {
        System.out.println("\n Trein koppelen aan reis ");

        Reis r = kiesReis(sc);
        if (r == null) return;

        System.out.println("Kies locomotieftype:");
        System.out.println("1) CLASS_373");
        System.out.println("2) CLASS_374");
        System.out.print("Keuze: ");
        String keuze = sc.nextLine().trim();

        TypeLocomotief type;
        if (keuze.equals("1")) {
            type = TypeLocomotief.CLASS_373;
        } else if (keuze.equals("2")) {
            type = TypeLocomotief.CLASS_374;
        } else {
            System.out.println("Ongeldige keuze.");
            return;
        }

        Locomotief loco = new Locomotief(type);
        Trein trein = new Trein(loco);


        r.setTrein(trein);

        System.out.println("Trein gekoppeld aan reis.");
    }

//TICKET VERKOPEN
    private static void verkoopTicket(Scanner sc) {
        System.out.println("\n Ticket verkopen ");

        Passagier p = kiesPassagier(sc);
        if (p == null) return;

        Reis r = kiesReis(sc);
        if (r == null) return;

        // Foutcontrole: zonder trein geen ticketverkoop mogelijk
        if (r.getTrein() == null) {
            System.out.println("Fout: er is nog geen trein gekoppeld aan deze reis.");
            return;
        }

        System.out.println("Kies klasse:");
        System.out.println("1) EERSTE_KLASSE");
        System.out.println("2) TWEEDE_KLASSE");
        System.out.print("Keuze: ");
        String keuze = sc.nextLine().trim();

        Klasse klasse;
        if (keuze.equals("1")) {
            klasse = Klasse.EERSTE_KLASSE;
        } else if (keuze.equals("2")) {
            klasse = Klasse.TWEEDE_KLASSE;
        } else {
            System.out.println("Ongeldige keuze.");
            return;
        }

        try {
            Ticket ticket = r.verkoopTicket(p, klasse);
            System.out.println("Ticket verkocht.");
        } catch (IllegalStateException e) {
            // Trein vol
            System.out.println("Ticketverkoop mislukt: " + e.getMessage());
        }
    }

//BOARDINGLIJST NAAR BESTAND
    private static void drukBoardinglijstAf(Scanner sc) {
        System.out.println("\n Boardinglijst afdrukken ");

        Reis r = kiesReis(sc);
        if (r == null) return;

        if (r.getTickets().isEmpty()) {
            System.out.println("Geen tickets voor deze reis.");
            return;
        }

        // Bestandsnaam: Vertrek_Bestemming_2026-03-05T12:30.txt
        String bestandsnaam = r.getVertrek().getNaam() + "_" +
                r.getBestemming().getNaam() + "_" +
                r.getVertrektijd().toString().replace(":","-") + ".txt";


        try (FileWriter writer = new FileWriter(bestandsnaam)) {

            writer.write("Boardinglijst\n");
            writer.write("Reis: " + r.getVertrek().getNaam() + " -> " + r.getBestemming().getNaam() + "\n");
            writer.write("Vertrektijd: " + r.getVertrektijd() + "\n");

            for (Ticket t : r.getTickets()) {
                Passagier p = t.getPassagier();

                writer.write(
                        p.getNaam() + " " + p.getAchternaam() +
                                " | RijksNr: " + p.getRijksNr() +
                                " | Geboortedatum: " + p.getGeboortedatum() +
                                " | Klasse: " + t.getKlasse() +
                                "\n"
                );
            }

            System.out.println("Boardinglijst geschreven naar: " + bestandsnaam);

        } catch (IOException e) {
            System.out.println("Fout bij wegschrijven: " + e.getMessage());
        }
    }

//HULPFUNCTIES: KIEZEN

    private static Passagier kiesPassagier(Scanner sc) {
        if (passagiers.isEmpty()) {
            System.out.println("Geen passagiers geregistreerd.");
            return null;
        }

        System.out.println("Kies passagier:");
        for (int i = 0; i < passagiers.size(); i++) {
            Passagier p = passagiers.get(i);
            System.out.println((i + 1) + ") " + p.getNaam() + " " + p.getAchternaam());
        }

        System.out.print("Nummer: ");
        String input = sc.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= passagiers.size()) {
                System.out.println("Ongeldig nummer.");
                return null;
            }
            return passagiers.get(index);
        } catch (NumberFormatException e) {
            System.out.println("Geef een geldig nummer in.");
            return null;
        }
    }

    private static Reis kiesReis(Scanner sc) {
        if (reizen.isEmpty()) {
            System.out.println("Geen reizen aangemaakt.");
            return null;
        }

        System.out.println("Kies reis:");
        for (int i = 0; i < reizen.size(); i++) {
            Reis r = reizen.get(i);
            System.out.println((i + 1) + ") " +
                    r.getVertrek().getNaam() + " -> " +
                    r.getBestemming().getNaam() + " | " +
                    r.getVertrektijd());
        }

        System.out.print("Nummer: ");
        String input = sc.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= reizen.size()) {
                System.out.println("Ongeldig nummer.");
                return null;
            }
            return reizen.get(index);
        } catch (NumberFormatException e) {
            System.out.println("Geef een geldig nummer in.");
            return null;
        }
    }
}