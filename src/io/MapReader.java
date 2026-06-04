package io;

import model.Cell;
import model.EmptyCell;
import model.Road;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;
import model.utility.PowerPlant;
import model.utility.WaterStation;
import model.utility.InternetHub;
import model.service.PoliceStation;
import model.service.Hospital;
import model.service.School;
import simulation.Grid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Harita dosyasini okuyor ve Grid nesnesine çeviriyor
public class MapReader {

    public static Grid readMap(String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> rowDataList = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.trim().split("\\s+");
            String rowData = (parts.length >= 2 && Character.isDigit(parts[0].charAt(0))) ? parts[1] : parts[0];
            rowDataList.add(rowData);
        }
        reader.close();

        int rows = rowDataList.size();
        int cols = rowDataList.get(0).length();
        Cell[][] cells = new Cell[rows][cols];

        // Her satırdaki her karakteri hücreye dönüştürüyoruz
        for (int r = 0; r < rows; r++) {
            String rowData = rowDataList.get(r);
            for (int c = 0; c < cols; c++) {
                cells[r][c] = createCell(String.valueOf(rowData.charAt(c)), r, c);
            }
        }

        return new Grid(rows, cols, cells);
    }

    // switch-case ile sembole bakıyor ve ona uygun hücre nesnesi oluşturuyor
    private static Cell createCell(String symbol, int row, int col) {

        symbol = symbol.toUpperCase();

        switch (symbol) {
            case "H":
                return new Housing(row, col);
            case "I":
                return new Industrial(row, col);
            case "C":
                return new Commercial(row, col);

            case "P":
                return new PowerPlant(row, col);
            case "W":
                return new WaterStation(row, col);
            case "T":
                return new InternetHub(row, col);

            case "F":
                return new PoliceStation(row, col);
            case "D":
                return new Hospital(row, col);
            case "S":
                return new School(row, col);

            case "R":
                return new Road(row, col);
            case "E":
                return new EmptyCell(row, col);

            default:
                return new EmptyCell(row, col);
        }
    }
}