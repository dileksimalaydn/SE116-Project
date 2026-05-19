package model.zone;

// Sanayi bolgesi ve goods uretiyor. Housing'den gelen nufus ile calisiyor. Elektrik ve su yeterli.
public class Industrial extends Zone {

    public Industrial(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'I';
    }

    // Internet yok, sadece elektrik ve su.
    @Override
    public int computeM() {
        return Math.min(electricityReceived, waterReceived);
    }

    // Level 3 ile gelen nufusta uretime katki sagliyor.
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

    // Goods uretiyor.
    @Override
    public int getGoodsProduced() {
        return computeOutput();
    }

    @Override
    public void updateLevel() {
        // Elektrik veya su gelmezse level direk 0.
        if (electricityReceived == 0 || waterReceived == 0) {
            level = 0;
            lastTickOutput = computeOutput();
            return;
        }

        // Level 1 icin nufus lazim.
        boolean cond1 = populationReceived > 0;
        // Level 2 icin guvenlik te lazim.
        boolean cond2 = cond1 && securityCovered;
        // Level 3 icin nufus gelmeye devam etmeli.
        boolean cond3 = cond2 && populationReceived > 0;

        level = computeNewLevel(cond1, cond2, cond3);
        lastTickOutput = computeOutput();
    }

    private int computeNewLevel(boolean cond1, boolean cond2, boolean cond3) {
        boolean meetsCurrentLevel;
        switch (level) {
            case 0:
                meetsCurrentLevel = true;
                break;
            case 1:
                meetsCurrentLevel = cond1;
                break;
            case 2:
                meetsCurrentLevel = cond2;
                break;
            case 3:
                meetsCurrentLevel = cond3;
                break;
            default:
                meetsCurrentLevel = false;
        }

        if (!meetsCurrentLevel) {
            return level - 1;
        }

        if (level == 0 && cond1) {
            return 1;
        }
        if (level == 1 && cond2) {
            return 2;
        }
        if (level == 2 && cond3) {
            return 3;
        }
        return level;
    }
}