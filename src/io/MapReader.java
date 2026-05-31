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

//Harita dosyasini okuyor ve Grid nesnesine çeviriyor
public class MapReader {

    public static Grid readMap(String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String firstLine = reader.readLine();

        if (firstLine == null) {
            reader.close();
            throw new IOException("The map file is empty!!");
        }

        String[] sizes = firstLine.trim().split("\\s+");

        int rows = Integer.parseInt(sizes[0]);
        int cols = Integer.parseInt(sizes[1]);

        Cell[][] cells = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {

            String line = reader.readLine();

            if (line == null) {
                reader.close();
                throw new IOException("There are missing rows on the map!");
            }

            String[] symbols = line.trim().split("\\s+");

            if (symbols.length < cols) {
                reader.close();
                throw new IOException("There are missing columns on the map!");
            }

            for (int c = 0; c < cols; c++) {
                cells[r][c] = createCell(symbols[c], r, c);
            }
        }

        reader.close();

        return new Grid(rows, cols, cells);
    }
  //switch-case ile sembole bakıyor ve ona uygun hücre nesnesi oluşturuyor

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
                System.out.println("Unknown symbol:\n" + symbol);
                return new EmptyCell(row, col);
        }
    }
}
