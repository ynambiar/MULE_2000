package Model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by yamininambiar on 11/3/15.
 */
public class GameTest {
    Game game;

    public GameTest() {
        game = new Game();
        game.currentPlayer = new Player("AI", "Flapper", "TesterBob", "blue");
    }


    @Test
    public void testGetTimeAfterFoodCheck() throws Exception {

        int[] foodRq = [0, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5];
        int food = game.getCurrentPlayer().getFood();
        if (game.getRoundNumber() == 1) {
            assertEquals(50, game.getTimeLeft());
        }
        if (food >= foodRq[game.getRoundNumber()]) {
            assertEquals(0, game.getCurrentPlayer().getFood());
            assertEquals(0, game.getTimeLeft());
        } else if (food == 0) {
            assertEquals(5, game.getTimeLeft());
        } else {
            assertEquals(food * -1, game.getCurrentPlayer().getFood());
            assertEquals(30, game.getTimeLeft());
        }
    }

    @Test
    public void testNumMules() {
        Player p = new Player();
        p.setMuleEmplaced(0, 0, Mule.FOOD);
        assertEquals(1, p.getNumMules());
        p.setMuleEmplaced(5, 9, Mule.SMITHORE);
        assertEquals(2, p.getNumMules());
        p.setMuleEmplaced(5, 0, Mule.FLOWER);
        assertEquals(3, p.getNumMules());
        p.setMuleEmplaced(0, 9, Mule.ENERGY);
        assertEquals(4, p.getNumMules());
    }
}
