package model.service;

//Guvenlik saglayan servis.
//5 mesefa icindeki tum zonelara servis verir.
public class PoliceStation extends ServiceProvider {

    public PoliceStation(int row, int col) {
        super(row, col, 5);
    }
    @Override
    public char getSymbol() {
        return 'F';
    }

}
