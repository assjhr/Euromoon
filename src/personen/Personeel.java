package personen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Deze klasse stelt een personeelslid voor.
 * Een personeelslid is een persoon die werkt op de trein,
 * zoals een bestuurder, steward of bagagepersoneel,
 * en kan beschikken over verschillende certificaten.
 */
public class Personeel extends Persoon {

    //Het type personeel
    private PersoneelTypes type;

    //Lijst met certificaten
    private List<Certificaten> certicatenPersoneel;

    public Personeel(
            String naam,
            String achternaam,
            String rijksNr,
            LocalDate geboortedatum,
            PersoneelTypes type
    ){
        super(naam, achternaam, rijksNr, geboortedatum);
        this.type = type;

        //We maken zelf een lege lijst aan
        this.certicatenPersoneel = new ArrayList<>();
    }

    public PersoneelTypes getType() {
        return type;
    }

    public void setType (PersoneelTypes type) {
        this.type = type;
    }

    public List<Certificaten> getCerticatenPersoneel() {
        return certicatenPersoneel;
    }

    public void voegCertificaatToe(Certificaten certificaat){
        certicatenPersoneel.add(certificaat);
    }
}


