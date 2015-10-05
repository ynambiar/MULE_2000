package Model;

/**
 * Created by tuckerlocicero on 10/2/15.
 */
public class Player {

    //These three fields DO NOT require setters, because they should not change
    //after being instantiated with the constructor
    private String name;
    private String human;
    private String race;

    //These fields DO require setters
    private int money;

    public Player(String name, String human, String race) {
        this.name = name;
        this.human = human;
        this.race = race;
        if (race.equals("Flapper")) {
            this.money = 1600;
        } else if (race.equals("Human")) {
            this.money = 600;
        } else {
            this.money = 1000;
        }
    }

    public String getName() {
        return name;
    }

    public String getHuman() {
        return human;
    }

    public String getRace() {
        return race;
    }

    public int getMoney() {
        return money;
    }
}
