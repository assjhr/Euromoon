package personen;

import java.time.LocalDate;
/**
 * Deze abstracte klasse stelt een persoon voor binnen het systeem.
 * Een persoon bevat de gemeenschappelijke gegevens die gelden
 * voor alle types personen, zoals naam, achternaam,
 * rijksregisternummer en geboortedatum.
 * Deze klasse kan niet rechtstreeks geïnstantieerd worden
 * en dient als superklasse voor de klassen Passagier en Personeel.
 */
abstract class Persoon {

    private String naam;
    private String achternaam;
    private String rijksNr;
    private LocalDate geboortedatum;

    public Persoon(String naam, String achternaam, String rijksNr, LocalDate geboortedatum) {
        this.naam = naam;
        this.achternaam = achternaam;
        this.rijksNr = rijksNr;
        this.geboortedatum = geboortedatum;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getRijksNr() {
        return rijksNr;
    }

    public void setRijksNr(String rijksNr) {
        this.rijksNr = rijksNr;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "naam='" + naam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", rijksNr='" + rijksNr + '\'' +
                ", geboortedatum=" + geboortedatum +
                '}';
    }
}
