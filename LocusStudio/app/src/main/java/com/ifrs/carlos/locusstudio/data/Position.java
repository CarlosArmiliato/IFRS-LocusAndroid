package com.ifrs.carlos.locusstudio.data;

/**
 * Created by carlos on 15/01/18.
 */

public class Position {

    private double xMetros;
    private double yMetros;

    public Position(){}
    public Position(double x, double y){
        this.xMetros = x;
        this.yMetros = y;
    }

    public double getxMetros() {
        return xMetros;
    }

    public Position setxMetros(double xMetros) {
        this.xMetros = xMetros;
        return this;
    }

    public double getyMetros() {
        return yMetros;
    }

    public Position setyMetros(double yMetros) {
        this.yMetros = yMetros;
        return this;
    }
}
