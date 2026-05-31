package simulation;

import io.SimulationPrinter;
import model.zone.Zone;

// Simulasyonun ana akisini yoneten class
public class Simulation {

    private final Grid grid;
    private int currentTick;

    public Simulation(Grid grid) {
        this.grid = grid;
        this.currentTick = 0;
    }

    public int getCurrentTick() {
        return currentTick;
    }

    public Grid getGrid() {
        return grid;
    }

    // verilen tick sayisi kadar calistirir
    public void run(int tickCount) {
        for (int i = 0; i < tickCount; i++) {
            runOneTick();
        }
    }

    // Tek bir tick calistirir
    public void runOneTick() {
        currentTick++;

        resetZones();

        ServiceDistributor.distribute(grid);

        UtilityDistributor.distribute(grid);

        ResourceDistributor.distribute(grid);

        updateZones();

        computeOutputs();
    }

    //  Eski verileri temizler
    private void resetZones() {
        for (Zone zone : grid.getAllZones()) {
            zone.resetTickData();
        }
    }

    // Zone seviyelerini gunceller
    private void updateZones() {
        for (Zone zone : grid.getAllZones()) {
            zone.updateLevel();
        }
    }

    // Tick sonunda yeni uretimleri hesaplar
    private void computeOutputs() {
        for (Zone zone : grid.getAllZones()) {
            zone.computeOutput();
        }
    }
}
