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

    public RandomEvent() {
        Random r = new Random();
    }
}
