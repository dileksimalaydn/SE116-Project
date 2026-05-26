package model.service;

//Saglik hizmeti saglar.
//3 mesafe icindeki tum zonelara hizmet verir.
public class Hospital extends ServiceProvider {

    public Hospital(int row, int col) {
        super(row, col, 3);
    }

    @Override
    public char getSymbol() {
        return 'D';
    }
}
