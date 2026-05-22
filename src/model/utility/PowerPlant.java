package model.utility;

// Elektrik ureten bina 100 birim elektrik uretir.
public class PowerPlant extends UtilityProvider {

    public PowerPlant(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}