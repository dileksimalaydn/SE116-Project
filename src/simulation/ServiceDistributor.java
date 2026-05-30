package simulation;

import model.zone.Zone;
import model.service.PoliceStation;
import model.service.Hospital;
import model.service.School;

//Servisleri uygun mu,mesafe sartini sagliyor mu diye hesaplayip zonelera veriyor.
//Mesafe hesabinda satir sutun farki (Manhattan) yaptim.

public class ServiceDistributor {
    public static void distribute(Grid grid) {
        //Polis icin sarti kontrol ediyor
        for (PoliceStation police : grid.getPoliceStations()) {
            for (Zone zone : grid.getAllZones()) {
                int distance = Math.abs(police.getRow() - zone.getRow())
                        + Math.abs(police.getCol() - zone.getCol());
                if (distance <= police.getRadius()) {
                    zone.setSecurity(true);
                }
            }
        }
        //Hastane icin sarti kontrol ediyor
        for (Hospital hospital : grid.getHospitals()) {
            for (Zone zone : grid.getAllZones()) {
                int distance = Math.abs(hospital.getRow() - zone.getRow())
                        + Math.abs(hospital.getCol() - zone.getCol());
                if (distance <= hospital.getRadius()) {
                    zone.setHealth(true);
                }
            }
        }
        //Okul icin sarti kontrol ediyor
        for (School school : grid.getSchools()) {
            for (Zone zone : grid.getAllZones()) {
                int distance = Math.abs(school.getRow() - zone.getRow())
                        + Math.abs(school.getCol() - zone.getCol());
                if (distance <= school.getRadius()) {
                    zone.setEducation(true);
                }
            }
        }
    }
}