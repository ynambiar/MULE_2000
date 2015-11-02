package Model;

import java.io.Serializable;

/**
 * Created by William on 10/20/2015.
 */
public enum Event implements Serializable {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, //affects single player
    ELEVEN, TWELVE; //affects all players

    private int m;
    int[] mVals = new int[] {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};


    //returns int[money, food, energy, smithore]
    public int[] getEffects() {
        m = mVals[Main.myGame.getRoundNumber()-1];
        switch (this) {
            case ONE:
                return new int[] {0, 3, 2, 0};
            case TWO:
                return new int[] {0, 0, 0, 2};
            case THREE:
                return new int[] {m * 8, 0, 0, 0};
            case FOUR:
                return new int[] {m * 2, 0, 0, 0};
            case FIVE:
                return new int[] {-m * 4, 0, 0, 0};
            case SIX:
                return new int[] {0, (int) (Main.myGame.getCurrentPlayer().getFood() * -.5), 0, 0};
            case SEVEN:
                return new int[] {-m * 6, 0, 0, 0};
            case EIGHT:
                return new int[] {0, 0, 0, m / 10};
            case NINE:
                return new int[] {0, -2, 0, 0};
            case TEN:
                return new int[] {0, m / 10, 0, 0};
            case ELEVEN:
                return new int[] {m * 4, 0, 0, 0};
            case TWELVE:
                return new int[] {0, -m, 0, 0};
        }
        return null;
    }

    public String toString() {
        m = mVals[Main.myGame.getRoundNumber()-1];
        switch (this) {
            case ONE:
                return "You just received a package from the GT alumni containing 3 food and 2 energy units!";
            case TWO:
                return "A wandering Tech student repaid your hospitality by leaving two bars of ore!";
            case THREE:
                return "The museum bought your antique personal computer for $" + m * 8 + "!";
            case FOUR:
                return "You found a dead moose rat and sold the hide for $" + m * 2 + "!";
            case FIVE:
                return "Flying cat-bugs ate the roof off your house. Repairs cost $" + m * 4 + "!";
            case SIX:
                return "Mischievous UGA students broke into your storage shed and stole half your food!";
            case SEVEN:
                return "Your space gypsy in-laws made a mess of the town. It cost you $" + m * 6 + " to clean it up!";
            case EIGHT:
                return "A clumsy mule dropped a pile of " + (m / 10) + " ore. Finders keepers, losers weepers!";
            case NINE:
                return "It started to rain and you lost 2 energy units wandering through the mountains.";
            case TEN:
                return "You happened upon a field of daisies and took a nap. You gained " + (m / 10) + " units of energy.";
            case ELEVEN:
                return "Production went up and the market's doing well! Everyone gains $" + (m * 4) + ".";
            case TWELVE:
                return "There is rampant crop damage and recent food units have been contaminated. Everyone loses "
                        + m + " units of food.";
        }
        return "";
    }
}
