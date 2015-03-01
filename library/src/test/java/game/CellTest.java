package game;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import io.kimo.gameoflifeview.game.Cell;

public class CellTest extends TestCase {
    @Test
    public void testEquals() {
        Cell cell_1 = new Cell(1,2);
        Cell cell_2 = new Cell(1,2);

        Assert.assertEquals("Must be true if cells have same coordinates", true, cell_1.equals(cell_2));
    }

    @Test
    public void testCoordinates() {
        Cell cell = new Cell();

        Assert.assertNotNull("Must have the X coordinate", cell.x);
        Assert.assertNotNull("Must have the Y coordinate", cell.y);
    }

    @Test
    public void testDie() {
        Cell cell = new Cell();

        Assert.assertTrue("it was alive",cell.isAlive);
        cell.die();
        Assert.assertFalse("should be dead", cell.isAlive);
    }

    @Test
    public void testReborn() {
        Cell cell = new Cell();

        cell.die();
        Assert.assertFalse("kill cell",cell.isAlive);
        cell.reborn();
        Assert.assertTrue("it must reborn",cell.isAlive);
    }
}
