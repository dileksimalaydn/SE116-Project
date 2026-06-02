import io.MapReader;
import simulation.Grid;
import simulation.Simulation;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Harita dosyasının adı (Proje ana dizininde olmalı)
        String mapFileName = "sample_map.txt";

        // Simülasyonun kaç adım (tick) çalışacağı
        int tickCount = 10;

        System.out.println("=================================================");
        System.out.println("    OBJECTVILLE CITY SIMULATION BEGINS           ");
        System.out.println("=================================================\n");

        try {
            // 1. io.MapReader kullanarak txt dosyasından Grid'i (haritayı) oluşturuyoruz
            System.out.println("-> Reading '" + mapFileName + "' file...");
            Grid grid = MapReader.readMap(mapFileName);
            System.out.println("-> Map loaded successfully! (Size: " + grid.getRows() + "x" + grid.getCols() + ")\n");

            // 2. Simülasyon motorunu oluşturduğumuz Grid ile başlatıyoruz
            Simulation simulation = new Simulation(grid);

            // 3. Simülasyonu belirtilen tick sayısı kadar çalıştırıyoruz
            System.out.println("-> Running simulation for " + tickCount + " ticks...\n");
            simulation.run(tickCount);

            System.out.println("=================================================");
            System.out.println("    SIMULATION COMPLETED SUCCESSFULLY            ");
            System.out.println("=================================================");

        } catch (IOException e) {
            System.err.println("[ERROR] An error occurred while reading the map file!");
            System.err.println("Please ensure that '" + mapFileName + "' is located in the project root directory.");
            System.err.println("Error Detail: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] An unexpected error occurred while running the simulation!");
            e.printStackTrace();
        }
    }
}
