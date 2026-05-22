package model.utility;

// Su ureten bina,100 birim su uretir.
public class WaterStation extends UtilityProvider {

    public WaterStation(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'W';
    }
}