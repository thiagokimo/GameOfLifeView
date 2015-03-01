package rules;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.World;

/**
 * Rule #2: Any live cell with two or three live neighbours lives on to the
 * next generation.
 */
public class Rule2Test extends TestCase {

    @Test
    public void testCellsWith2LiveNeighboursWillLive() {
        World world = new World(3,3);

        world.kill(0,2);
        world.kill(1,2);
        world.kill(2,2);
        world.kill(0,0);
        world.kill(1,0);
        world.kill(2,0);

        world.rotate();

        Assert.assertTrue(world.get(1, 1).isAlive);
    }

    @Test
    public void testCellsWith3LiveNeighboursWillLive() {
        World world = new World(3,3);

        world.kill(1,1);
        world.kill(2,1);
        world.kill(1,0);
        world.kill(2,0);

        world.rotate();

        Assert.assertTrue(world.get(0, 2).isAlive);
    }
}
