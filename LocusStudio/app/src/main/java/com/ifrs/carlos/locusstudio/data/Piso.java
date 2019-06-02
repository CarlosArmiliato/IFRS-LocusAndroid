package com.ifrs.carlos.locusstudio.data;

import java.util.ArrayList;

/**
 * Created by carlos on 14/01/18.
 */

public class Piso {

    private int id;
    private String nomeCurto;
    private Estabelecimento estab;
    private ArrayList<LocusBeacon> locusBeacons;
    private double xMetros;
    private double yMetros;

    public Piso(int id, String nomeCurto){
        this.id = id;
        this.nomeCurto = nomeCurto;
    }
    public Piso(int id, String nomeCurto, double xMetros, double yMetros){
        this.id = id;
        this.nomeCurto = nomeCurto;
        this.yMetros = yMetros;
        this.xMetros = xMetros;
    }
    public Piso(int id, String nomeCurto, Estabelecimento estab){
        this.id = id;
        this.nomeCurto = nomeCurto;
        this.estab = estab;
    }
    public Piso(int id, String nomeCurto, Estabelecimento estab, double xMetros, double yMetros){
        this.id = id;
        this.nomeCurto = nomeCurto;
        this.estab = estab;
        this.yMetros = yMetros;
        this.xMetros = xMetros;
    }

    public ArrayList<LocusBeacon> getLocusBeacons() {
        return locusBeacons;
    }

    public void setLocusBeacons(ArrayList<LocusBeacon> locusBeacons) {
        this.locusBeacons = locusBeacons;
    }

    public double getxMetros() {
        return xMetros;
    }

    public void setxMetros(double xMetros) {
        this.xMetros = xMetros;
    }

    public double getyMetros() {
        return yMetros;
    }

    public void setyMetros(double yMetros) {
        this.yMetros = yMetros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public Estabelecimento getEstab() {
        return estab;
    }

    public void setEstab(Estabelecimento estab) {
        this.estab = estab;
    }

}
