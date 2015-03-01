package rules;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.World;

/**
 * Rule #3: Any live cell with more than three live neighbours dies, as if
 * by overcrowding.
 */
public class Rule3Test extends TestCase {

    @Test
    public void testCellsWith4LiveNeighboursWillDie() {
        World world = new World(3,3);

        world.kill(0, 2);
        world.kill(1, 2);
        world.kill(2, 2);
        world.kill(2, 0);

        world.rotate();

        Assert.assertFalse(world.get(1, 1).isAlive);
    }

    @Test
    public void testCellsSurroundedByLiveNeighboursWillDie() {
        World world = new World(3,3);

        world.rotate();

        Assert.assertFalse(world.get(1, 1).isAlive);
    }
}
