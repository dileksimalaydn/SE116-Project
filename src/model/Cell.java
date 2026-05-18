package model;

// Her hucrenin temel sinifi.
public abstract class Cell {
    private final int row;
    private final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    // Her alt sinif kendi sembolunu dondurecek.
    public abstract char getSymbol();

    // Elektrik, su ve internet bu hucreden gecebilir mi? Ve basta false yani gecisi bloklar.
    public boolean isPassable() {
        return false;
    }
}