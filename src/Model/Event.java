package Model;

/**
 * Created by William on 10/20/2015.
 */
public enum Event {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN;

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
                return "Your space gypsy inlaws made a mess of the town. It cost you $" + m * 6 + " to clean it up!";
        }
        return "";
    }
}
