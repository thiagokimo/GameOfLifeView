package io.kimo.gameoflifeview.game;

/**
 * Class that represents a single cell in the game.
 */
public class Cell {
    public int x,y;
    public boolean isAlive;

    public Cell() {
        this.x = 0;
        this.y = 0;
        this.isAlive = true;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAlive = true;
    }

    public Cell(int x, int y, boolean status) {
        this.x = x;
        this.y = y;
        this.isAlive = status;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean equals(Object otherCell) {
        if(otherCell == null)
            return false;
        else if(otherCell == this)
            return true;
        else if(!(otherCell instanceof Cell))
            return false;
        else {
            return (this.hashCode() == otherCell.hashCode() );
        }
    }

    public void die() {
        this.isAlive = false;
    }

    public void reborn() {
        this.isAlive = true;
    }

    public String toString(){ return "("+this.x+","+this.y+")";}
}
