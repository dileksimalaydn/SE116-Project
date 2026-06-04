import io.MapReader;
import simulation.Grid;
import simulation.Simulation;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Arguman kontrolu yapiyoruz.
        if (args.length < 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <map_file> <tick_count>");
            System.out.println("Example: java -jar ObjectVilleGame.jar map00.txt 10");
            return;
        }

        String mapFileName = args[0];
        int tickCount;

        // Tick sayisi integer olmali, onun kontrolu.
        try {
            tickCount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Tick count must be a valid integer. Example: 10");
            return;
        }

        // Dosya okunamayabilir veya bulunamayabilir, kontrol ediyoruz.
        try {
            Grid grid = MapReader.readMap(mapFileName);
            Simulation simulation = new Simulation(grid);
            simulation.run(tickCount);
        } catch (IOException e) {
            System.out.println("Error: Could not find or read file '" + mapFileName + "'.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}