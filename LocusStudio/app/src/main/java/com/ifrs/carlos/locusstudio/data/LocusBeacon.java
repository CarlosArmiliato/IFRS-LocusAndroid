package com.ifrs.carlos.locusstudio.data;

import android.support.annotation.NonNull;

/**
 * Created by carlos on 14/01/18.
 */

public class LocusBeacon implements Comparable<LocusBeacon> {

    private String UUID;
    private String Major;
    private String Minor;
    private double xMetros;
    private double zMetros;
    private double yMetros;
    private double Proximity;
    private double Distance;
    private double RSSI;
    private boolean Active;
    private Piso piso;


    @Override
    public int compareTo(LocusBeacon lcBeacon){

        if(this.getDistance() == 0){
            return 1;
        }
        else if(lcBeacon.getDistance() == 0){
            return -1;
        }
        else if(this.getDistance() > lcBeacon.getDistance()){
            return 1;
        }
        else if (this.getDistance() < lcBeacon.getDistance()){
            return -1;
        }

        return 0;


    }


    public LocusBeacon(String UUID, String Major, String Minor) {
        this.UUID = UUID;
        this.Major = Major;
        this.Minor = Minor;
    }


    public Piso getPiso() {
        return piso;
    }

    public LocusBeacon setPiso(Piso piso) {
        this.piso = piso;
        return this;
    }

    public String getId() {
        return UUID + "-" + Major + "-" + Minor;
    }

    public String getUUID() {
        return UUID;
    }

    public LocusBeacon setUUID(String UUID) {
        this.UUID = UUID;
        return this;
    }

    public String getMajor() {
        return Major;
    }

    public LocusBeacon setMajor(String major) {
        Major = major;
        return this;
    }

    public String getMinor() {
        return Minor;
    }

    public LocusBeacon setMinor(String minor) {
        Minor = minor;
        return this;
    }

    public double getxMetros() {
        return xMetros;
    }

    public LocusBeacon setxMetros(double xMetros) {
        this.xMetros = xMetros;
        return this;
    }

    public double getzMetros() {
        return zMetros;
    }

    public LocusBeacon setzMetros(double zMetros) {
        this.zMetros = zMetros;
        return this;
    }

    public double getyMetros() {
        return yMetros;
    }

    public LocusBeacon setyMetros(double yMetros) {
        this.yMetros = yMetros;
        return this;
    }

    public double getProximity() {
        return Proximity;
    }

    public LocusBeacon setProximity(double proximity) {
        Proximity = proximity;
        return this;
    }

    public double getDistance() {
        return Distance;
    }

    public LocusBeacon setDistance(double distance) {
        Distance = distance;
        return this;
    }

    public double getRSSI() {
        return RSSI;
    }

    public LocusBeacon setRSSI(double RSSI) {
        this.RSSI = RSSI;
        return this;
    }

    public boolean isActive() {
        return Active;
    }

    public LocusBeacon setActive(boolean active) {
        Active = active;
        return this;
    }

}
