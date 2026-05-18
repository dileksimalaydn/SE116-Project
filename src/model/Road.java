package model;

// Yol hucresi. isPassable() true oldugu icin gecis var.
public class Road extends Cell {

    public Road(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}