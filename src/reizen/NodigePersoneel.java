package reizen;

import personen.Personeel;
import personen.PersoneelTypes;

import java.util.ArrayList;
import java.util.List;
/**
 * Deze klasse stelt het nodige personeel voor dat vereist is
 * om een reis te laten vertrekken.
 *
 * Een geldige personeelsbezetting bestaat uit:
 * - exact één bestuurder
 * - minstens drie stewards
 *
 * De klasse bevat controles om te garanderen dat enkel
 * personeel met het juiste type kan worden toegevoegd.
 */
public class NodigePersoneel {
    private  Personeel bestuurder;

    private List<Personeel> stewards;

    public NodigePersoneel() {
        this.stewards = new ArrayList<>();
    }

    //Methode om een bestuurder toe te wijzen
    /**
     * Wijst een bestuurder toe aan de reis.
     * @param personeel het personeelslid dat bestuurder moet zijn
     * @throws IllegalArgumentException als het personeelslid geen bestuurder is
     */
    public void stelBestuurderIn(Personeel personeel){

        //controle: enkel personeel met type bestuurder mag bestuurder zijn
        if(personeel.getType() != PersoneelTypes.BESTUURDER){
            throw new IllegalArgumentException("Dit personeelslid is geen bestuurder.");
        }

        //bestuurder wordt opgeslagen
        this.bestuurder = personeel;
    }

    //Methode om een steward toe te voegen
    /**
     * Wijst een bestuurder toe aan de reis.
     * @param personeel het personeelslid dat bestuurder moet zijn
     * @throws IllegalArgumentException als het personeelslid geen bestuurder is
     */
    public void voegStewardToe(Personeel personeel){

        // controle; enkel personeel met type steward mag toegevoegd worden
        if(personeel.getType() != PersoneelTypes.STEWARD){
            throw new IllegalArgumentException("Dit personeelslid is geen steward.");
        }

        stewards.add(personeel);
    }
    public Personeel getBestuurder(){
        return bestuurder;
    }
    public List<Personeel> getStewards(){
        return stewards;
    }


    public boolean isGeldigVoorVertrek(){
        boolean bestuurderAanwezig = bestuurder != null;
        boolean stewardsAanwezig = stewards.size() >= 3;

        return bestuurderAanwezig && stewardsAanwezig;
    }
}
