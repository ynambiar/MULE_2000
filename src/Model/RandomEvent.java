package Model;
import java.util.Random;

/**
 * Created by yamininambiar on 10/7/15.
 */
public class RandomEvent {
    /*
    1. YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.
    2. A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.
    3. THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.
    4. YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.
    5. FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.
    6. MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.
    7. OUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.
    */


    public enum EventType {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, PLAGUE, FLOOD;
    }

    private Random random;
    private EventType eventType;
    private Game game;
    private double chanceOfHappening;

    public RandomEvent(EventType e) {
        random = new Random();
        eventType = e;
        game = Main.myGame;
        chanceOfHappening = 0.27;
    }

    public void setChanceOfHappening(double c) {
        chanceOfHappening = c;
    }



    public void runRandomEvent() {
        if (eventType == EventType.ONE) {
            game.getCurrentPlayer().addFood(3);
            game.getCurrentPlayer().addEnergy(2);
        }
        if (eventType == EventType.TWO) {
            game.getCurrentPlayer().addSmithore(2);
        }
        if (eventType == EventType.THREE) {
            int m = game.getRoundNumber();
            game.getCurrentPlayer().addMoney(8 * m);
        }
        if (eventType == EventType.FOUR) {
            int m = game.getRoundNumber();
            game.getCurrentPlayer().addMoney(2 * m);
        }
        if (eventType == EventType.FIVE) {
            int m = game.getRoundNumber();
            game.getCurrentPlayer().addMoney(-4 * m);
        }
        if (eventType == EventType.SIX) {
            int halfFood = game.getCurrentPlayer().getFood() / 2;
            game.getCurrentPlayer().addFood(-halfFood);
        }
        if (eventType == EventType.SEVEN) {
            int m = game.getRoundNumber();
            game.getCurrentPlayer().addMoney(-6 * m);
        }
        if (eventType == EventType.FLOOD) {
            //TODO define this event
        }
        if (eventType == EventType.PLAGUE) {
            int m = game.getRoundNumber();
            game.getCurrentPlayer().addEnergy(-5 * m);
        }

    }
}
