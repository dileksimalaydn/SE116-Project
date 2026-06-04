package simulation;

import model.zone.Zone;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;

// Önceki tick'te üretilen kaynakları zone'lara eşit olarak dağıtıyor
public class ResourceDistributor {

    public static void distribute(Grid grid) {
        int totalPopulationPool = 0;
        int totalGoodsPool = 0;
        int totalLifestylePool = 0;

        // Önceki tick'teki üretimleri topluyoruz
        for (Zone zone : grid.getAllZones()) {
            if (zone instanceof Housing) {
                totalPopulationPool += zone.getLastTickOutput();
            } else if (zone instanceof Industrial) {
                totalGoodsPool += zone.getLastTickOutput();
            } else if (zone instanceof Commercial) {
                totalLifestylePool += zone.getLastTickOutput();
            }
        }

        int numPopReceivers = grid.getIndustrialZones().size() + grid.getCommercialZones().size();
        int numGoodsReceivers = grid.getCommercialZones().size();
        int numLifestyleReceivers = grid.getHousingZones().size();

        // Eşit bölme yapıyoruz, kalan atılıyor
        int populationPerZone = numPopReceivers > 0 ? totalPopulationPool / numPopReceivers : 0;
        int goodsPerZone = numGoodsReceivers > 0 ? totalGoodsPool / numGoodsReceivers : 0;
        int lifestylePerZone = numLifestyleReceivers > 0 ? totalLifestylePool / numLifestyleReceivers : 0;

        // Grid tarama sırasıyla dağıtım yapıyoruz ve yazdırıyoruz
        for (Zone zone : grid.getAllZones()) {
            if (zone instanceof Industrial) {
                if (populationPerZone > 0) {
                    zone.setPopulationReceived(populationPerZone);
                    System.out.println("Industrial at (" + zone.getRow() + "," + zone.getCol() + ") received " + populationPerZone + " population");
                }
            } else if (zone instanceof Commercial) {
                if (populationPerZone > 0) {
                    zone.setPopulationReceived(populationPerZone);
                    System.out.println("Commercial at (" + zone.getRow() + "," + zone.getCol() + ") received " + populationPerZone + " population");
                }
                if (goodsPerZone > 0) {
                    zone.setGoodsReceived(goodsPerZone);
                    System.out.println("Commercial at (" + zone.getRow() + "," + zone.getCol() + ") received " + goodsPerZone + " goods");
                }
            } else if (zone instanceof Housing) {
                if (lifestylePerZone > 0) {
                    zone.setLifestyleReceived(lifestylePerZone);
                    System.out.println("House at (" + zone.getRow() + "," + zone.getCol() + ") received " + lifestylePerZone + " lifestyle");
                }
            }
        }
    }
}