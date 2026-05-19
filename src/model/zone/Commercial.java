package model.zone;

// Ticari bolge ve lifestyle uretiyor. Hem nufus hem goods hem de 3 utility birden olmali.
public class Commercial extends Zone {

    public Commercial(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    // Ayni housing gibi 3 utility'nin de minimumuna bakiyor.
    @Override
    public int computeM() {
        return Math.min(electricityReceived, Math.min(waterReceived, internetReceived));
    }

    // Level 3'te nufus ve goods arasindaki minimum bonus olarak geliyor.
    @Override
    public int computeOutput() {
        int m = computeM();
        switch (level) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return 2 * m + Math.min(populationReceived, goodsReceived);
            default:
                return 0;
        }
    }

    // Lifestyle uretiyor ve Housing'e donuyor.
    @Override
    public int getLifestyleProduced() {
        return computeOutput();
    }

    @Override
    public void updateLevel() {
        // Utility kesilirse direkt sifira duser.
        if (electricityReceived == 0 || waterReceived == 0 || internetReceived == 0) {
            level = 0;
            lastTickOutput = computeOutput();
            return;
        }

        // Level 1 icin nufus + goods gelmeli.
        boolean cond1 = populationReceived > 0 && goodsReceived > 0;
        // Level 2 icin guvenlik de gerekli.
        boolean cond2 = cond1 && securityCovered;
        // Level 3 icin kaynaklar gelmeye devam etmeli.
        boolean cond3 = cond2 && populationReceived > 0 && goodsReceived > 0;

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