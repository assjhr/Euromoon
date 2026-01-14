package reizen;

import personen.Personeel;
import personen.PersoneelTypes;

import java.util.ArrayList;
import java.util.List;

public class NodigePersoneel {
    private Personeel bestuurder;

    private List<Personeel> stewards;

    public NodigePersoneel() {
        this.stewards = new ArrayList<>();
    }

    //Methode om een bestuurder toe te wijzen
    public void stelBestuurderIn(Personeel personeel){

        //controle: enkel personeel met type bestuurder mag bestuurder zijn
        if(personeel.getType() != PersoneelTypes.BESTUURDER){
            throw new IllegalArgumentException("Dit personeelslid is geen bestuurder.");
        }

        //bestuurder wordt opgeslagen
        this.bestuurder = personeel;
    }

    //Methode om een steward toe te voegen
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
