import personen.Passagier;
import personen.Personeel;
import personen.PersoneelTypes;
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
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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


    /**
     * Waarom List en niet ArrayList?
     * Best practice, je programmeert tegen de interface, flexibel voor later
     */
    private static final List<Passagier> passagiers = new ArrayList<>();
    private static final List<Reis> reizen = new ArrayList<>();
    private static final List<Personeel> personeelsleden = new ArrayList<>();

    //* Set met alle beschikbare steden
    //* We starten met een vaste lijst Europese steden
    //* HashSet zorgt ervoor dat elke stad maar één keer voorkomt
    private static final Set<String> BESCHIKBARE_STATIONS = new HashSet<>(Set.of(
            "Brussel",
            "Amsterdam",
            "Londen",
            "Berlijn",
            "Parijs",
            "Charleroi",
            "Manchester",
            "Rotterdam",
            "Leuven",
            "Antwerpen"
    ));





    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        initialiseerPersoneel();
        initialiseerPassagiers();

        //* while-loop zodat het menu blijft terugkomen
        //* het programma stopt pas wanneer running false wordt
        while (running) {
            toonMenu();
            String keuze = sc.nextLine().trim();

            //*switch is duidelijker dan veel if/else
            //*elke case komt overeen met één menu-optie
            switch (keuze) {
                case "1":
                    registreerPassagier(sc);
                    break;
                case "2":
                    registreerPersoneel(sc);
                    break;
                case "3":
                    maakReisAan(sc);
                    break;
                case "4":
                    koppelTreinAanReis(sc);
                    break;
                case "5":
                    verkoopTicket(sc);
                    break;
                case "6":
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
        System.out.println("2. Registreren personeel");
        System.out.println("3. Aanmaken reis");
        System.out.println("4. Trein koppelen aan reis");
        System.out.println("5. Ticket verkopen aan passagier");
        System.out.println("6. Afdrukken boardinglijst (tekstbestand)");
        System.out.println("0. Stoppen");
        System.out.print("Maak een keuze: ");
    }

    // PASSAGIER REGISTREREN
    private static void registreerPassagier(Scanner sc) {
        System.out.println("\n Passagier registreren");

        String naam;
        while (true) {
            System.out.print("Naam: ");
            naam = sc.nextLine().trim();

            if (!naam.isEmpty()) {
                break; // naam is oké
            }
            System.out.println("Fout: naam mag niet leeg zijn. Probeer opnieuw.");
        }

        String achternaam;
        while (true) {
            System.out.print("Achternaam: ");
            achternaam = sc.nextLine().trim();

            if (!achternaam.isEmpty()) {
                break;
            }
            System.out.println("Fout: achternaam mag niet leeg zijn. Probeer opnieuw.");
        }

        String rijksNr;
        while (true) {
            System.out.print("Rijksregisternummer: ");
            rijksNr = sc.nextLine().trim();

            if (!rijksNr.isEmpty()) {
                break;
            }
            System.out.println("Fout: rijksregisternummer mag niet leeg zijn. Probeer opnieuw.");
        }

        LocalDate geboortedatum;
        while (true) {
            System.out.print("Geboortedatum (YYYY-MM-DD): ");
            String datumStr = sc.nextLine().trim();

            try {
                geboortedatum = LocalDate.parse(datumStr);
                break; // datum is geldig
            } catch (Exception e) {
                System.out.println("Fout: geboortedatum moet in formaat YYYY-MM-DD zijn.");
            }
        }

        // Passagier aanmaken en opslaan
        Passagier p = new Passagier(naam, achternaam, rijksNr, geboortedatum);
        passagiers.add(p);

        System.out.println("Passagier geregistreerd.");
    }

    private static void initialiseerPassagiers() {

        passagiers.add(new Passagier(
                "Assia",
                "Jouhri",
                "01010100001",
                LocalDate.of(2001, 1, 1)
        ));


        System.out.println("Standaard passagiers geladen.");
    }

    //PERSONEEL REGISTREREN
    /**
     * Registreert een nieuwe passagier via de console.
     *
     * De  gebruiker moet een naam, achternaam, rijksregisternummer
     * en geboortedatum ingeven.
     * Lege invoer is niet toegestaan en de geboortedatum
     * moet het formaat YYYY-MM-DD volgen.
     *
     * @param sc Scanner die gebruikt wordt voor gebruikersinvoer
     */

    private static void initialiseerPersoneel() {

        personeelsleden.add(new Personeel(
                "Sabah",
                "Smail",
                "80010111111",
                LocalDate.of(1980, 1, 1),
                PersoneelTypes.BESTUURDER
        ));

        personeelsleden.add(new Personeel(
                "Mariam",
                "Jouhri",
                "90020222222",
                LocalDate.of(1990, 2, 2),
                PersoneelTypes.STEWARD
        ));

    }


    private static void registreerPersoneel(Scanner sc) {
        System.out.println("\n//* PERSONEEL REGISTREREN");

        System.out.print("Naam: ");
        String naam = sc.nextLine().trim();

        System.out.print("Achternaam: ");
        String achternaam = sc.nextLine().trim();

        System.out.print("Rijksregisternummer: ");
        String rijksNr = sc.nextLine().trim();

        //* Controle: rijksNr mag niet leeg zijn
        if (rijksNr.isEmpty()) {
            System.out.println("Fout: rijksregisternummer mag niet leeg zijn.");
            return;
        }

        //* Controle: zelfde rijksNr mag niet 2x voorkomen
        for (Personeel p : personeelsleden) {
            if (p.getRijksNr().equals(rijksNr)) {
                System.out.println("Fout: dit personeelslid bestaat al.");
                return;
            }
        }

        System.out.print("Geboortedatum (YYYY-MM-DD): ");
        String datumStr = sc.nextLine().trim();

        LocalDate geboortedatum;
        try {
            //* String omzetten naar LocalDate
            geboortedatum = LocalDate.parse(datumStr);
        } catch (Exception e) {
            System.out.println("Fout: datum moet in formaat YYYY-MM-DD zijn.");
            return;
        }

        //* Personeelstype kiezen via enum
        System.out.println("Kies type personeel:");
        System.out.println("1) BESTUURDER");
        System.out.println("2) STEWARD");
        System.out.println("3) BAGAGE_PERSONEEL");
        System.out.print("Keuze: ");

        String keuze = sc.nextLine().trim();

        PersoneelTypes type;
        switch (keuze) {
            case "1":
                type = PersoneelTypes.BESTUURDER;
                break;
            case "2":
                type = PersoneelTypes.STEWARD;
                break;
            case "3":
                type = PersoneelTypes.BAGAGE_PERSONEEL;
                break;
            default:
                System.out.println("Ongeldige keuze.");
                return;
        }

        //* Personeelsobject aanmaken
        Personeel personeel = new Personeel(
                naam,
                achternaam,
                rijksNr,
                geboortedatum,
                type
        );

        //* Personeel opslaan in de lijst
        personeelsleden.add(personeel);

        System.out.println("Personeelslid geregistreerd: " +
                personeel.getNaam() + " (" + personeel.getType() + ")");
    }


    //REIS AANMAKEN
    /**
     * Maakt een nieuwe reis aan.
     *
     * De gebruiker kiest een vertrekstation, bestemmingsstation
     * en een vertrektijd. Vertrek en bestemming mogen niet gelijk zijn.
     * De reis wordt aangemaakt zonder trein; die kan later gekoppeld worden.
     *
     * @param sc Scanner voor gebruikersinvoer
     */
    private static void maakReisAan(Scanner sc) {
        System.out.println("\nReis aanmaken");

        String vertrekNaam;
        String bestemmingNaam;

        while (true) {
            //* Vertrek- en bestemmingsstation vragen met foutcontrole
            vertrekNaam = vraagGeldigeStad(sc, "VERTREKSTATION: \n - Brussel \n - Amsterdam \n - Londen \n - Berlijn \n - Parijs \n - Charleroi \n - Manchester \n - Rotterdam \n - Leuven \n - Antwerpen");
            bestemmingNaam = vraagGeldigeStad(sc, "BESTEMMINGSSTATION: \n - Brussel \n - Amsterdam \n - Londen \n - Berlijn \n - Parijs \n - Charleroi \n - Manchester \n - Rotterdam \n - Leuven \n - Antwerpen");

            //* Controle: vertrek en bestemming mogen niet hetzelfde zijn
            if (vertrekNaam.equalsIgnoreCase(bestemmingNaam)) {
                System.out.println("Fout: vertrek en bestemming mogen niet hetzelfde zijn. Probeer opnieuw.");
                continue; // opnieuw vragen
            }

            //* Alles is geldig → loop verlaten
            break;
        }


        //* Controle: lege invoer niet toegelaten
        if (vertrekNaam.isEmpty() || bestemmingNaam.isEmpty()) {
            System.out.println("Fout: station mag niet leeg zijn.");
            return;
        }

//* Controle: bestaat vertrekstation in de vaste lijst?
        if (!BESCHIKBARE_STATIONS.contains(vertrekNaam)) {
            System.out.println("Fout: vertrekstation '" + vertrekNaam + "' is niet beschikbaar.");
            return;
        }

//* Controle: bestaat bestemmingsstation in de vaste lijst?
        if (!BESCHIKBARE_STATIONS.contains(bestemmingNaam)) {
            System.out.println("Fout: bestemmingsstation '" + bestemmingNaam + "' is niet beschikbaar.");
            return;
        }

//* Extra controle: vertrek en bestemming mogen niet hetzelfde zijn
        if (vertrekNaam.equalsIgnoreCase(bestemmingNaam)) {
            System.out.println("Fout: vertrek en bestemming mogen niet hetzelfde zijn.");
            return;
        }



        System.out.print("Vertrektijd (YYYY-MM-DDTHH:MM) bv. 2026-03-05T12:30: ");
        String tijdStr = sc.nextLine().trim();

        try {
            LocalDateTime vertrektijd = LocalDateTime.parse(tijdStr);

            Station vertrek = new Station(vertrekNaam);
            Station bestemming = new Station(bestemmingNaam);

            Reis r = new Reis(vertrek, bestemming, vertrektijd);
            reizen.add(r);

            //* Steden opslaan in de HashSet (duplicaten worden genegeerd)
            BESCHIKBARE_STATIONS.add(vertrekNaam.toLowerCase());
            BESCHIKBARE_STATIONS.add(bestemmingNaam.toLowerCase());

            System.out.println("Reis aangemaakt (zonder trein gekoppeld).");

        } catch (Exception e) {
            System.out.println("Fout: vertrektijd moet in formaat YYYY-MM-DDTHH:MM zijn.");
        }

    }

    //* Hulpmethode die blijft vragen tot de gebruiker een geldige stad ingeeft
    /**
     * Vraagt de gebruiker om een geldige stad (station) in te geven.
     *
     * De methode blijft de gebruiker opnieuw vragen totdat:
     * - de invoer niet leeg is
     * - de stad voorkomt in de lijst van beschikbare stations
     *
     * Deze methode wordt gebruikt bij het aanmaken van een reis
     * om te voorkomen dat onbestaande stations ingegeven worden.
     *
     * @param sc Scanner die gebruikt wordt voor gebruikersinvoer
     * @param titel Tekst die aangeeft welk station gevraagd wordt
     *              (bv. "Vertrekstation" of "Bestemmingsstation")
     * @return een geldige stadsnaam uit de lijst van beschikbare stations
     */
    private static String vraagGeldigeStad(Scanner sc, String titel) {

        while (true) {
            //* while(true) blijft lopen tot we zelf return doen
            System.out.print(titel + ": ");
            String stad = sc.nextLine().trim();

            //* Controle: lege invoer niet toegelaten
            if (stad.isEmpty()) {
                System.out.println("Fout: station mag niet leeg zijn. Probeer opnieuw.");
                continue;
            }

            //* Controle: bestaat de stad in de vaste lijst?
            if (!BESCHIKBARE_STATIONS.contains(stad)) {
                System.out.println("Fout: station '" + stad + "' is niet beschikbaar. Probeer opnieuw.");
                continue;
            }

            //* Geldige invoer → methode stopt hier
            return stad;
        }
    }


    //TREIN KOPPELEN AAN REIS
    /**
     * Koppelt een trein aan een bestaande reis.
     *
     * De gebruiker kiest eerst een reis en daarna het type locomotief.
     * Op basis van dit type wordt automatisch een trein aangemaakt
     * met het juiste aantal wagons.
     *
     * Een reis kan pas tickets verkopen nadat er een trein aan gekoppeld is.
     *
     * @param sc Scanner die gebruikt wordt voor gebruikersinvoer
     */
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


    /**
     * Verkoopt een ticket aan een passagier voor een gekozen reis.
     *
     * Er wordt gecontroleerd of:
     * - er een trein gekoppeld is aan de reis
     * - er nog vrije plaatsen zijn in de gekozen klasse
     *
     * Indien de verkoop niet mogelijk is, wordt een foutmelding getoond.
     *
     * @param sc Scanner voor gebruikersinvoer
     */
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
    /**
     * Drukt de boardinglijst van een gekozen reis af naar een tekstbestand.
     *
     * De gebruiker kiest eerst een reis. Indien er geen tickets zijn
     * voor deze reis, wordt er geen bestand aangemaakt.
     *
     * Het bestand krijgt als naam:
     * vertrek_bestemming_vertrektijd.txt
     * (bijvoorbeeld: Brussel_Parijs_2026-03-05T12-30.txt)
     *
     * In het bestand worden alle passagiers met een ticket voor deze reis
     * opgelijst, samen met hun gegevens en reisklasse.
     *
     * @param sc Scanner die gebruikt wordt voor gebruikersinvoer
     */
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
    /**
     * Laat de gebruiker een passagier kiezen uit de lijst van geregistreerde passagiers.
     *
     * De passagiers worden genummerd weergegeven in de console.
     * De gebruiker kiest een passagier door een nummer in te geven.
     * Bij foutieve invoer of wanneer er geen passagiers zijn,
     * wordt null teruggegeven.
     *
     * @param sc Scanner die gebruikt wordt voor gebruikersinvoer
     * @return de gekozen Passagier of null indien de keuze ongeldig is
     */
    private static Passagier kiesPassagier(Scanner sc) {
        if (passagiers.isEmpty()) {
            System.out.println("Geen passagiers geregistreerd.");
            return null;
        }

        //* For-loop om alle passagiers in de lijst te tonen
        //* I gebruiken we om een nummer te geven aan elke passagier
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
    /**
     * Laat de gebruiker een reis kiezen uit de lijst van beschikbare reizen.
     *
     * De reizen worden genummerd weergegeven in de console.
     * Indien de invoer ongeldig is, wordt null teruggegeven.
     *
     * @param sc Scanner voor gebruikersinvoer
     * @return de gekozen Reis of null bij foutieve invoer
     */
    private static Reis kiesReis(Scanner sc) {

        //* Als er nog geen reizen zijn, kan de gebruiker niets kiezen
        //* We stoppen de methode en geven null terug
        if (reizen.isEmpty()) {
            System.out.println("Geen reizen aangemaakt.");
            return null;
        }

        System.out.println("Kies reis:");

        //* For-loop om alle reizen te tonen
        //* I gebruiken we om de reize te nummeren
        for (int i = 0; i < reizen.size(); i++) {

            //* We halen de reis op die positie i staat in de lijst
            Reis r = reizen.get(i);

            //* We tonen vertrek, bestemming en vertrektijd
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