package simulation;

import model.Cell;
import model.zone.Zone;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;
import model.utility.PowerPlant;
import model.utility.WaterStation;
import model.utility.InternetHub;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;

// Elektrik, su ve internet dağıtımını BFS ile yapan sınıf
public class UtilityDistributor {

    public static void distribute(Grid grid) {
        // Önce internet, sonra su, sonra elektrik dağıtılıyor
        for (InternetHub hub : grid.getInternetHubs()) {
            runGenericBFS(grid, hub, hub.getCapacity(),
                    Zone::getRemainingNetDemand,
                    Zone::receiveInternet,
                    "internet");
        }

        for (WaterStation station : grid.getWaterStations()) {
            runGenericBFS(grid, station, station.getCapacity(),
                    Zone::getRemainingWaterDemand,
                    Zone::receiveWater,
                    "water");
        }

        for (PowerPlant plant : grid.getPowerPlants()) {
            runGenericBFS(grid, plant, plant.getCapacity(),
                    Zone::getRemainingElecDemand,
                    Zone::receiveElectricity,
                    "electricity");
        }
    }

    // BFS ile kaynağı harita üzerinde yayıyor, yol ve zone'lardan geçiyor
    private static void runGenericBFS(Grid grid, Cell start, int initialCapacity,
                                      Function<Zone, Integer> getDemand,
                                      BiConsumer<Zone, Integer> receiveResource,
                                      String utilityName) {

        int remainingCapacity = initialCapacity;
        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.getRows()][grid.getCols()];

        queue.add(start);
        visited[start.getRow()][start.getCol()] = true;

        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        while (!queue.isEmpty() && remainingCapacity > 0) {
            Cell curr = queue.poll();

            // Zone'a ulaştıysak talep kadar kaynak veriyoruz
            if (curr instanceof Zone) {
                Zone zone = (Zone) curr;
                int demand = getDemand.apply(zone);
                int allocated = Math.min(remainingCapacity, demand);

                if (allocated > 0) {
                    receiveResource.accept(zone, allocated);
                    remainingCapacity -= allocated;
                    System.out.println(getZoneName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") received " + allocated + " " + utilityName);
                }
            }

            // 4 yönde komşu hücrelere bakıyoruz
            for (int i = 0; i < 4; i++) {
                int nRow = curr.getRow() + dRow[i];
                int nCol = curr.getCol() + dCol[i];

                if (grid.inBounds(nRow, nCol) && !visited[nRow][nCol]) {
                    Cell neighbor = grid.getCell(nRow, nCol);
                    // Sadece geçilebilir hücrelerden (yol veya zone) devam ediyoruz
                    if (neighbor.isPassable()) {
                        visited[nRow][nCol] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    // Zone tipine göre isim döndürüyor
    private static String getZoneName(Zone zone) {
        if (zone instanceof Housing) return "House";
        if (zone instanceof Industrial) return "Industrial";
        return "Commercial";
    }
}