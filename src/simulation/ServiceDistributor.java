package simulation;

import model.zone.Zone;
import model.zone.Housing;
import model.zone.Industrial;
import model.zone.Commercial;
import model.service.ServiceProvider;
import model.service.PoliceStation;
import model.service.Hospital;
import model.service.School;

// Servis binalarını harita sırasıyla işleyip zone'lara dağıtıyor
public class ServiceDistributor {

    public static void distribute(Grid grid) {
        // Tüm servis binaları harita tarama sırasıyla işleniyor (satır satır, soldan sağa)
        for (ServiceProvider provider : grid.getServiceProviders()) {
            for (Zone zone : grid.getAllZones()) {
                int dr = provider.getRow() - zone.getRow();
                int dc = provider.getCol() - zone.getCol();
                double distance = Math.sqrt(dr * dr + dc * dc);

                if (distance <= provider.getRadius()) {
                    if (provider instanceof School) {
                        // Okul sadece konutlara eğitim veriyor
                        if (zone instanceof Housing) {
                            zone.setEducation(true);
                            System.out.println("House at (" + zone.getRow() + "," + zone.getCol() + ") received education service");
                        }
                    } else if (provider instanceof Hospital) {
                        // Hastane sadece konutlara sağlık hizmeti veriyor
                        if (zone instanceof Housing) {
                            zone.setHealth(true);
                            System.out.println("House at (" + zone.getRow() + "," + zone.getCol() + ") received health service");
                        }
                    } else if (provider instanceof PoliceStation) {
                        // Polis her zone tipine güvenlik sağlıyor
                        zone.setSecurity(true);
                        System.out.println(getZoneName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") received security service");
                    }
                }
            }
        }
    }

    // Zone tipine göre yazdırma için isim döndürüyor
    private static String getZoneName(Zone zone) {
        if (zone instanceof Housing) return "House";
        if (zone instanceof Industrial) return "Industrial";
        return "Commercial";
    }
}