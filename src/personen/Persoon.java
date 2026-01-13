package personen;

import java.time.LocalDate;

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
