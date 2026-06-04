package model.zone;

// Sanayi bolgesi. Mal (goods) uretiyor, elektrik ve su ile calisiyor.
public class Industrial extends Zone {

    public Industrial(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'I';
    }

    // Internet kullanmiyor, sadece elektrik ve su
    @Override
    public int computeM() {
        return Math.min(electricityReceived, waterReceived);
    }

    // Level 3'te nufus uretime bonus katki sagliyor
    @Override
    public int computeOutput() {
        int m = computeM();
        switch (level) {
            case 1: return m;
            case 2: return 2 * m;
            case 3: return 2 * m + populationReceived;
            default: return 0;
        }
    }

    @Override
    public int getGoodsProduced() {
        return computeOutput();
    }

    // Internet talep etmiyor, sifir donduruyoruz
    @Override
    public int getRemainingNetDemand() {
        return 0;
    }

    @Override
    public void updateLevel() {
        int oldLevel = level;

        // Elektrik veya su kesilirse direkt sifira dusuyor
        if (electricityReceived == 0 || waterReceived == 0) {
            level = 0;
            lastTickOutput = computeOutput();
            printOutput(oldLevel);
            return;
        }

        // Level 1 icin sadece elektrik ve su yeterli
        boolean cond1 = electricityReceived > 0 && waterReceived > 0;
        // Level 2 icin guvenlik de lazim
        boolean cond2 = cond1 && securityCovered;
        // Level 3 icin nufus gelmeli
        boolean cond3 = cond2 && populationReceived > 0;

        level = computeNewLevel(cond1, cond2, cond3);
        lastTickOutput = computeOutput();
        printOutput(oldLevel);
    }

    // Uretim ve seviye degisimini yazdirir
    private void printOutput(int oldLevel) {
        if (lastTickOutput > 0) {
            System.out.println("Industrial at (" + getRow() + "," + getCol() + ") generated " + lastTickOutput + " goods");
        }
        if (level != oldLevel) {
            String direction = level > oldLevel ? "levels up" : "levels down";
            System.out.println("Industrial at (" + getRow() + "," + getCol() + ") " + direction + " from " + oldLevel + " to " + level);
        }
    }

    private int computeNewLevel(boolean cond1, boolean cond2, boolean cond3) {
        boolean meetsCurrentLevel;
        switch (level) {
            case 0: meetsCurrentLevel = true; break;
            case 1: meetsCurrentLevel = cond1; break;
            case 2: meetsCurrentLevel = cond2; break;
            case 3: meetsCurrentLevel = cond3; break;
            default: meetsCurrentLevel = false;
        }

        if (!meetsCurrentLevel) {
            return level - 1;
        }

        if (level == 0 && cond1) return 1;
        if (level == 1 && cond2) return 2;
        if (level == 2 && cond3) return 3;
        return level;
    }
}