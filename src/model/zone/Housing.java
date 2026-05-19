package model.zone;

// Konut bolgesi. Nufus uretiyor. Elektrik, su ve internet alirsa level 1'e cikiyor. Daha yukarisi icin servisler ve lifestyle da olmali.
public class Housing extends Zone {

    public Housing(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    // 3 utility'nin de minimumu kullaniliyor. Yani en az olan ne kadar verirse o kadar uretim.
    @Override
    public int computeM() {
        return Math.min(electricityReceived, Math.min(waterReceived, internetReceived));
    }

    // Seviyeye gore uretilen nufus miktari.
    @Override
    public int computeOutput() {
        int m = computeM();
        switch (level) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return 2 * m + lifestyleReceived;
            default:
                return 0;
        }
    }

    // Nufus uretiyor housing.
    @Override
    public int getPopulationProduced() {
        return computeOutput();
    }

    @Override
    public void updateLevel() {
        // Herhangi bir utility sifirlanirsa zone hemen level 0'a dusuyor.
        if (electricityReceived == 0 || waterReceived == 0 || internetReceived == 0) {
            level = 0;
            lastTickOutput = computeOutput();
            return;
        }

        // 3 utility'de gelirse level 1 sarti tamamdir.
        boolean cond1 = true;
        // Level 2 icin tum servisler lazim.
        boolean cond2 = cond1 && securityCovered && healthCovered && educationCovered;
        // Level 3 icin lifestyle da gelmeli.
        boolean cond3 = cond2 && lifestyleReceived > 0;

        level = computeNewLevel(cond1, cond2, cond3);
        lastTickOutput = computeOutput();
    }

    // Seviyeyi hesaplayan metod.
    // Su anki seviyenin kosulu bozulursa bir asagi, sartlar saglaniyorsa bir yukari hareket eder.
    private int computeNewLevel(boolean cond1, boolean cond2, boolean cond3) {
        boolean meetsCurrentLevel;
        switch (level) {
            case 0: meetsCurrentLevel = true;  break;
            case 1: meetsCurrentLevel = cond1; break;
            case 2: meetsCurrentLevel = cond2; break;
            case 3: meetsCurrentLevel = cond3; break;
            default: meetsCurrentLevel = false;
        }

        if (!meetsCurrentLevel) {
            return level - 1;
        }

        // Bir uste cikilabilir mi?
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