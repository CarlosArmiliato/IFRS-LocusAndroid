package com.ifrs.carlos.locusstudio;

import android.content.Context;
import android.widget.Toast;

import com.ifrs.carlos.locusstudio.data.LocusBeacon;
import com.ifrs.carlos.locusstudio.data.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by carlos on 14/01/18.
 */

public class Util {

    public static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    public static ArrayList<LocusBeacon> getFakeBeacons(){

        ArrayList<LocusBeacon> vlAux = new ArrayList<LocusBeacon>();

        vlAux.add(new LocusBeacon(
                "003e8c80-ea01-4ebb-b888-78da19df9e55",
                "768",
                "785")
                .setxMetros(4.85)
                .setyMetros(2.43)
                .setzMetros(0.8));
        vlAux.add(new LocusBeacon(
                "003e8c80-ea01-4ebb-b888-78da19df9e55",
                "768",
                "792")
                .setxMetros(1.93)
                .setyMetros(2.43)
                .setzMetros(0.8));
        vlAux.add(new LocusBeacon(
                "003e8c80-ea01-4ebb-b888-78da19df9e55",
                "768",
                "2554")
                .setxMetros(2.50)
                .setyMetros(0.01)
                .setzMetros(0.8));
        vlAux.add(new LocusBeacon(
                "003e8c80-ea01-4ebb-b888-78da19df9e55",
                "768",
                "1703")
                .setxMetros(2.38)
                .setyMetros(4.12)
                .setzMetros(0.8));
        return vlAux;

    }


    public static Position calculaPosicao(){

        Collections.sort(LocusData.locusBeacons);

        if (LocusData.locusBeacons.size() < 3){
            return new Position(0,0);
        }

        double x1 = LocusData.locusBeacons.get(0).getxMetros();
        double y1 = LocusData.locusBeacons.get(0).getyMetros();
        double r1 = ajustaDistancia(LocusData.locusBeacons.get(0));

        double x2 = LocusData.locusBeacons.get(1).getxMetros();
        double y2 = LocusData.locusBeacons.get(1).getyMetros();
        double r2 = ajustaDistancia(LocusData.locusBeacons.get(1));

        double x3 = LocusData.locusBeacons.get(2).getxMetros() ;
        double y3 = LocusData.locusBeacons.get(2).getyMetros();
        double r3 = ajustaDistancia(LocusData.locusBeacons.get(2));

        Position ponto1 = retornaPontoIntersect(x1, y1, r1, x2, y2, r2, x3, y3);
        Position ponto2 = retornaPontoIntersect(x2, y2, r2, x3, y3, r3, x1, y1);
        Position ponto3 = retornaPontoIntersect(x3, y3, r3, x1, y1, r1, x2, y2);

        Position pontoBaricentro = baricentro(ponto1.getxMetros(),
                                              ponto1.getyMetros(),
                                              ponto2.getxMetros(),
                                              ponto2.getyMetros(),
                                              ponto3.getxMetros(),
                                              ponto3.getyMetros());

        return pontoBaricentro;

    }

    private static double ajustaDistancia(LocusBeacon paBeacon){
        return paBeacon.getDistance();
    }

    private static Position baricentro(double x1, double y1, double x2, double y2, double x3, double y3){

        double x = (x1 + x2 + x3) / 3;
        double y = (y1 + y2 + y3) / 3;

        Position ponto = new Position();
        ponto.setxMetros(x);
        ponto.setyMetros(y);
        return ponto;

    }

    private static double dist2Pontos(double x1, double y1, double x2, double y2){

        double dist = (Math.pow(x2 - x1, 2)) + (Math.pow(y2 - y1, 2));
        dist = Math.sqrt(dist);

        return dist;

    }

    private static Position retornaPontoIntersect(double x1, double y1, double r1, double x2, double y2, double r2, double x3, double y3){

        //Fórmula matemática que calcula os pontos de intersecção de 2 círculos.
        double  a = (r1 * r1) - (r2 * r2) - (x1 * x1) - (y1 * y1) + (x2 * x2) + (y2 * x2);
        double  b = (-2 * x1) + (2 * x2);
        double  c = (-2 * y1) + (2 * y2); //desde que y1 != y2

        double  d = 1 + (b * b / c * c);
        double  e = (-2 * a * b / c * c) - 2 * x1 + (2 * y1 * b / c);
        double  f = ((a * a) / (c * c)) - (2 * y1 * a / c) + x1 * x1 + y1 * y1 - r1 * r1;

        double  delta = e * e - (4 * d * f);
        double  i1x = (-e + Math.sqrt(delta)) / (2 * d);
        double  i2x = (-e - Math.sqrt(delta)) / (2 * d);

        double  i1y = (a - (i1x * b)) / c;
        double  i2y = (a-(i2x*b)) / c;


        double  i1dist = dist2Pontos(i1x, i1y, x3, y3); //Calcula a distância da intersecção 1 até beacon 3
        double  i2dist = dist2Pontos(i2x, i2y, x3, y3); //Calcula a distância da intersecção 2 até beacon 3

        //Retorna o x e y da intersecção com a menor distância até beacon 3.
        //IMPORTANTE: Presupoem-se que nunca i1 e i2 vai dar igual, devido a utilização dos raios de 3 pontos
        Position ponto = new Position();
        if (i1dist < i2dist){
            ponto.setxMetros(i1x);
            ponto.setyMetros(i1y);
            return ponto;
        }
        else {
            ponto.setxMetros(i2x);
            ponto.setyMetros(i2y);
            return ponto;
        }
    }
}
