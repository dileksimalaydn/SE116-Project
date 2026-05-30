package simulation;

import model.zone.Zone;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;

import java.util.List;

public class ResourceDistributor {

    public static void distribute(Grid grid) {
        int totalPopulationPool = 0;
        int totalGoodsPool = 0;
        int totalLifestylePool = 0;

        // Haritadaki tüm zone'ların ürettiği kaynakları havuzda toplar
        for (Zone zone : grid.getAllZones()) {
            totalPopulationPool += zone.getPopulationProduced();
            totalGoodsPool += zone.getGoodsProduced();
            totalLifestylePool += zone.getLifestyleProduced();
        }

        List<Industrial> industrialZones = grid.getIndustrialZones();
        List<Commercial> commercialZones = grid.getCommercialZones();
        List<Housing> housingZones = grid.getHousingZones();

        // Nüfus dağıtımı (Industrial ve Commercial binalarına sırayla birer birer)
        while (totalPopulationPool > 0 && (!industrialZones.isEmpty() || !commercialZones.isEmpty())) {
            boolean distributedAny = false;
            
            for (Industrial ind : industrialZones) {
                if (totalPopulationPool > 0) {
                    ind.setPopulationReceived(ind.getPopulationReceived() + 1);
                    totalPopulationPool--;
                    distributedAny = true;
                }
            }
            for (Commercial com : commercialZones) {
                if (totalPopulationPool > 0) {
                    com.setPopulationReceived(com.getPopulationReceived() + 1);
                    totalPopulationPool--;
                    distributedAny = true;
                }
            }
            
            if (!distributedAny) break; 
        }

        // Malların (Goods) dağıtımı (Sadece Commercial binalarına)
        while (totalGoodsPool > 0 && !commercialZones.isEmpty()) {
            boolean distributedAny = false;
            
            for (Commercial com : commercialZones) {
                if (totalGoodsPool > 0) {
                    com.setGoodsReceived(com.getGoodsReceived() + 1);
                    totalGoodsPool--;
                    distributedAny = true;
                }
            }
            
            if (!distributedAny) break;
        }

        // Lifestyle dağıtımı (Sadece Housing binalarına)
        while (totalLifestylePool > 0 && !housingZones.isEmpty()) {
            boolean distributedAny = false;
            
            for (Housing house : housingZones) {
                if (totalLifestylePool > 0) {
                    house.setLifestyleReceived(house.getLifestyleReceived() + 1);
                    totalLifestylePool--;
                    distributedAny = true;
                }
            }
            
            if (!distributedAny) break;
        }
    }
}
