import io.MapReader;
import simulation.Grid;
import simulation.Simulation;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String mapFileName = args[0];
        int tickCount = Integer.parseInt(args[1]);

        Grid grid = MapReader.readMap(mapFileName);
        Simulation simulation = new Simulation(grid);
        simulation.run(tickCount);
    }
}