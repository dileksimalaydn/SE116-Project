package model.utility;

import model.Cell;

// Elektrik, su, internet saglayan binalarin temel sinifi.
// Her biri 100 birim uretir ve BFS ile dagitir.
public abstract class UtilityProvider extends Cell {

    protected static final int CAPACITY = 100;

    public UtilityProvider(int row, int col) {
        super(row, col);
    }

    // Kac birim urettiğini dondurur.
    public int getCapacity() {
        return CAPACITY;
    }

    // Utility saglayicilar gecis noktasi, BFS buradan gecer.
    @Override
    public boolean isPassable() {
        return true;
    }
}