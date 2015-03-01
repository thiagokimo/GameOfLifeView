package rules;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.World;

/**
 * Rule #4: Any dead cell with exactly three live neighbours becomes a live
 * cell, as if by reproduction.
 */
public class Rule4Test extends TestCase {

    @Test
    public void testDeadCellsWith3LiveNeighboursWillRevive() {
        World world = new World(3,3);

        world.kill(1, 2);
        world.kill(2, 2);
        world.kill(2, 1);
        world.kill(2, 0);
        world.kill(1, 0);
        world.kill(1, 1);

        world.rotate();

        Assert.assertTrue(world.get(1, 1).isAlive);
    }
}
