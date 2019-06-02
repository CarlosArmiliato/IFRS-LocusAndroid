package com.ifrs.carlos.locusstudio.data;

/**
 * Created by carlos on 14/01/18.
 */

public class Estabelecimento {

    private int id;
    private String nomeCurto;


    public Estabelecimento(int id, String nomeCurto){
        this.id = id;
        this.nomeCurto = nomeCurto;
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

    public String toString() {
        return getId() + "-" + getNomeCurto();
    }

}
