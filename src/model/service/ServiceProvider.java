package model.service;

import model.Cell;

//Polis,hastane ve okul binalarinin baslangic sinifi
//Belirli yaricaplara gore servis saglar
public abstract class ServiceProvider extends Cell {

    protected final int radius;

    public ServiceProvider(int row, int col, int radius) {
        super(row, col);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    // Servis binalari BFS'e gecirmiyor.
    @Override
    public boolean isPassable() {
        return false;
    }
}
