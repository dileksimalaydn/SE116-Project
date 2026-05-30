package simulation;

import model.Cell;
import model.zone.Zone;
import model.utility.PowerPlant;
import model.utility.WaterStation;
import model.utility.InternetHub;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class UtilityDistributor {

    public static void distribute(Grid grid) {
        // Elektrik dağıtımı
        for (PowerPlant plant : grid.getPowerPlants()) {
            runGenericBFS(grid, plant, plant.getCapacity(),
                    Zone::getRemainingElecDemand, 
                    Zone::receiveElectricity);
        }

        // Su dağıtımı
        for (WaterStation station : grid.getWaterStations()) {
            runGenericBFS(grid, station, station.getCapacity(),
                    Zone::getRemainingWaterDemand, 
                    Zone::receiveWater);
        }

        // İnternet dağıtımı
        for (InternetHub hub : grid.getInternetHubs()) {
            runGenericBFS(grid, hub, hub.getCapacity(),
                    Zone::getRemainingNetDemand, 
                    Zone::receiveInternet);
        }
    }

    // Altyapı elemanlarının harita üzerinden dağıtımını yapan ortak BFS algoritması
    private static void runGenericBFS(Grid grid, Cell start, int initialCapacity,
                                      Function<Zone, Integer> getDemand,
                                      BiConsumer<Zone, Integer> receiveResource) {
        
        int remainingCapacity = initialCapacity;
        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.getRows()][grid.getCols()];

        queue.add(start);
        visited[start.getRow()][start.getCol()] = true;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty() && remainingCapacity > 0) {
            Cell curr = queue.poll();

            // Eğer hücre bir bina ise talebe göre kaynak aktarımı yapılıyor
            if (curr instanceof Zone) {
                Zone zone = (Zone) curr;
                int demand = getDemand.apply(zone);
                int allocated = Math.min(remainingCapacity, demand);
                
                if (allocated > 0) {
                    receiveResource.accept(zone, allocated);
                    remainingCapacity -= allocated;
                }
            }

            // 4 yöndeki komşuları kontrol etme
            for (int i = 0; i < 4; i++) {
                int nRow = curr.getRow() + dRow[i];
                int nCol = curr.getCol() + dCol[i];

                // Harita sınırları ve ziyaret kontrolü
                if (grid.inBounds(nRow, nCol) && !visited[nRow][nCol]) {
                    Cell neighbor = grid.getCell(nRow, nCol);
                    // Sadece geçilebilir hücreler (yollar veya diğer binalar) üzerinden ilerlenebilir
                    if (neighbor.isPassable()) {
                        visited[nRow][nCol] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}
