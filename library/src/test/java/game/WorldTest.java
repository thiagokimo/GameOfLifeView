package game;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.Cell;
import io.kimo.gameoflifeview.game.World;

public class WorldTest extends TestCase {
    @Test
    public void testCountTheLiveNeighbours() {
        World world = new World(3,3);

        world.kill(0, 0);
        world.kill(0, 1);

        Assert.assertEquals(6, world.liveNeighboursOf(1,1).length);
    }

    @Test
    public void testReviveCell() {
        World world = new World(2,2);

        world.kill(1,0);

        Assert.assertFalse(world.get(1, 0).isAlive);

        world.revive(1,0);

        Assert.assertTrue(world.get(1, 0).isAlive);
    }

    @Test
    public void testKillCell() {
        World world = new World(2,2);

        Cell expectedCell = new Cell(1,0,false);

        world.kill(1,0);

        Assert.assertEquals(expectedCell, world.get(1, 0));
    }

    @Test
    public void testGetCell() {
        World world = new World(2,2);

        Cell expectedCell = new Cell(1,1);

        Assert.assertEquals(expectedCell, world.get(1,1));
    }

    @Test
    public void testSetup() {
        World world = new World(3,3);

        assertNotNull(world.getCells());
        assertNotNull(world.getBoard());
    }

    @Test
    public void testIntegrityOfCells() {
        World world = new World(2,2);

        Cell firstCell = new Cell(0,0);
        Cell secondCell = new Cell(0,1);
        Cell thirdCell = new Cell(1,0);
        Cell fourthCell = new Cell(1,1);

        Cell[] expectedCells = {firstCell,secondCell,thirdCell,fourthCell};

        Assert.assertArrayEquals(expectedCells, world.getCells());
    }

    @Test
    public void testWorldDimensions() {
        int width = 3;
        int height = 3;

        World world = new World(width,height);

        assertEquals(width, world.getWidth());
        assertEquals(height, world.getHeight());
    }

    @Test
    public void testNumberOfCells() {
        World world = new World(3,3);

        int expectedNumberOfCells = 9;

        assertEquals(expectedNumberOfCells, world.getCells().length);
    }
}
