package Personen;

import java.security.cert.Certificate;
import java.time.LocalDate;
import java.util.ArrayList;

public class Personeel extends Persoon {
    private Types type;
    private ArrayList<Certificaten> certicatenPersoneel;

    public Personeel(String naam, String achternaam, String rijksNr, LocalDate geboortedatum, Types type, ArrayList<Certificaten> certicatenPersoneel) {
        super(naam, achternaam, rijksNr, geboortedatum);
        this.type = type;
        this.certicatenPersoneel = certicatenPersoneel;
    }

    public Types getType() {
        return type;
    }

    public void setType (Types type) {
        this.type = type;
    }

    public ArrayList<Certificaten> getCerticatenPersoneel() {
        return certicatenPersoneel;
    }

    public void setCerticatenPersoneel(ArrayList<Certificaten> certicatenPersoneel) {
        this.certicatenPersoneel = certicatenPersoneel;
    }
}


