package Treinen;

import java.util.ArrayList;

public class Trein {
    private Locomotief locomotief;
    private ArrayList<Wagon> wagons;

    public Trein(Locomotief locomotief) {
        this.locomotief = locomotief;
        this.wagons = new ArrayList<>(this.locomotief.getType().getMaxWagons());
    }
}
