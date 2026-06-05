// Simulasyon sirasinda grid'i yazdirmak icin yaptik fakat su an gerek kalmadi.
// Son duzenleme ve revizelerimizden sonra gerekli olmadigini fark ettik.
// Bu sebeple yorum icine aldik tum sinifi.

/*
package io;

import model.Cell;
import model.zone.Zone;
import simulation.Grid;

public class SimulationPrinter {
//haritayı ekrana yazdırıyor
    public static void printTick(Grid grid, int tick) {

        System.out.println("Tick " + tick + ":");

        for (int r = 0; r < grid.getRows(); r++) {

            StringBuilder sb = new StringBuilder();

            for (int c = 0; c < grid.getCols(); c++) {

                Cell cell = grid.getCell(r, c);

                sb.append(cellLabel(cell));

                if (c < grid.getCols() - 1)
                    sb.append(" ");
            }

            System.out.println(sb);
        }

        System.out.println();
    }
//hücre tipine bakıyor sonra ekranda gösterilecek etiketi oluşturuyor
    private static String cellLabel(Cell cell) {

        if (cell instanceof Zone) {
            Zone z = (Zone) cell;
            return "" + z.getSymbol() + z.getLevel();
        }

        return "" + cell.getSymbol() + " ";
    }
}
*/