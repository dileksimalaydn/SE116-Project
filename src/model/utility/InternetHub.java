package model.utility;

// Internet saglayan bina,100 birim internet uretir.
public class InternetHub extends UtilityProvider {

    public InternetHub(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'T';
    }
}