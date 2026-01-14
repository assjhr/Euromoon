package personen;

import java.time.LocalDate;
/**
 * Deze klasse stelt een passagier voor.
 * Een passagier is een persoon die tickets kan aankopen
 * voor een reis met de trein.
 */
public class Passagier extends Persoon {
    public Passagier(String naam, String achternaam, String rijksNr, LocalDate geboortedatum) {
        super(naam, achternaam, rijksNr, geboortedatum);
    }

}
