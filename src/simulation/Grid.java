package simulation;

import model.Cell;
import model.zone.Zone;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;
import model.utility.UtilityProvider;
import model.utility.PowerPlant;
import model.utility.WaterStation;
import model.utility.InternetHub;
import model.service.ServiceProvider;
import model.service.PoliceStation;
import model.service.Hospital;
import model.service.School;

import java.util.ArrayList;
import java.util.List;

// Oyun tahtasini temsil ediyor.
// Hucreler burada tutuluyor ve listeleniyor turlerine gore.
public class Grid {

    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Grid(int rows, int cols, Cell[][] cells) {
        this.rows  = rows;
        this.cols  = cols;
        this.cells = cells;
    }

    public int getRows() {
        return rows;
    }
    public int getCols()  {
        return cols;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean inBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Tum zone'lari donduruyor.
    public List<Zone> getAllZones() {
        List<Zone> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof Zone)
                    list.add((Zone) cells[r][c]);
        return list;
    }

    public List<Housing> getHousingZones() {
        List<Housing> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof Housing)
                    list.add((Housing) cells[r][c]);
        return list;
    }

    public List<Industrial> getIndustrialZones() {
        List<Industrial> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof Industrial)
                    list.add((Industrial) cells[r][c]);
        return list;
    }

    public List<Commercial> getCommercialZones() {
        List<Commercial> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof Commercial)
                    list.add((Commercial) cells[r][c]);
        return list;
    }

    public List<PowerPlant> getPowerPlants() {
        List<PowerPlant> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof PowerPlant)
                    list.add((PowerPlant) cells[r][c]);
        return list;
    }

    public List<WaterStation> getWaterStations() {
        List<WaterStation> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof WaterStation)
                    list.add((WaterStation) cells[r][c]);
        return list;
    }

    public List<InternetHub> getInternetHubs() {
        List<InternetHub> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof InternetHub)
                    list.add((InternetHub) cells[r][c]);
        return list;
    }

    public List<ServiceProvider> getServiceProviders() {
        List<ServiceProvider> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof ServiceProvider)
                    list.add((ServiceProvider) cells[r][c]);
        return list;
    }

    public List<PoliceStation> getPoliceStations() {
        List<PoliceStation> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof PoliceStation)
                    list.add((PoliceStation) cells[r][c]);
        return list;
    }

    public List<Hospital> getHospitals() {
        List<Hospital> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof Hospital)
                    list.add((Hospital) cells[r][c]);
        return list;
    }

    public List<School> getSchools() {
        List<School> list = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (cells[r][c] instanceof School)
                    list.add((School) cells[r][c]);
        return list;
    }
}