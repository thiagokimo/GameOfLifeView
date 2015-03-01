package rules;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.Cell;
import io.kimo.gameoflifeview.game.World;

/**
 * Rule #1: Any live cell with fewer than two live neighbours dies, as if caused by
 * under-population.
 */
public class Rule1Test extends TestCase {

    @Test
    public void testCellsWithOneNeighboursShouldDie() {
        World world = new World(2, 2);

        world.kill(1, 1);
        world.kill(0, 0);

        world.rotate();

        Cell expectedCells[] = { new Cell(0, 0, false), new Cell(0, 1, false),
                new Cell(1, 0, false), new Cell(1, 1, false) };

        Assert.assertArrayEquals(expectedCells, world.getCells());
    }

    @Test
    public void testCellsWithNoLiveNeighboursShouldDie() {
        World world = new World(3,3);

        world.kill(0,0);
        world.kill(1,0);
        world.kill(2,0);
        world.kill(0,1);
        world.kill(2,1);
        world.kill(0,2);
        world.kill(1,2);
        world.kill(2,2);

        world.rotate();

        Assert.assertFalse(world.get(1, 1).isAlive);
    }
}
