package model.service;

//egitim hizmeti saglar.
//4 mesafe icindeki tum zonelara servis verir.
public class School extends ServiceProvider {

    public School(int row, int col) {
        super(row, col, 4);
    }
    @Override
    public char getSymbol() {
        return 'S';
    }
}
