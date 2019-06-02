package com.ifrs.carlos.locusstudio;

import com.ifrs.carlos.locusstudio.data.Estabelecimento;
import com.ifrs.carlos.locusstudio.data.LocusBeacon;
import com.ifrs.carlos.locusstudio.data.Piso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 14/01/18.
 */

public class LocusData {

    public static final String ID_ESTABELECIMENTO = "com.ifrs.carlos.locusstudio.ID_ESTABELECIMENTO";
    public static final String ID_PISO = "com.ifrs.carlos.locusstudio.ID_PISO";

    public static ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
    public static ArrayList<Piso> pisos = new ArrayList<Piso>();
    public static ArrayList<LocusBeacon> locusBeacons = new ArrayList<LocusBeacon>();

    public static void updateDistance(String UUID, String Major, String Minor, double distance) {

        for (LocusBeacon i : locusBeacons) {
            if (i.getUUID().equals(UUID) &&
                    i.getMajor().equals(Major) &&
                    i.getMinor().equals(Minor)) {
                i.setDistance(distance);
            }
        }

    }
    public static void activeBeacon(String UUID, String Major, String Minor) {

        for (LocusBeacon i : locusBeacons) {
            if (i.getUUID().equals(UUID) &&
                    i.getMajor().equals(Major) &&
                    i.getMinor().equals(Minor)) {
                i.setActive(true);
                i.setDistance(0);
            }
        }
    }
    public static void deactiveBeacon(String UUID, String Major, String Minor) {

        for (LocusBeacon i : locusBeacons) {
            if (i.getUUID().equals(UUID) &&
                    i.getMajor().equals(Major) &&
                    i.getMinor().equals(Minor)) {
                i.setActive(false);
                i.setDistance(0);
            }
        }
    }

}
