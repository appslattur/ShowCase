package com.special.appslattur.Tools;

/**
 * Created by Ari on 3.4.2015.
 */
public class Stadsetning {
    private double longitude;
    private double latitude;
    private String shortDesc, longDesc;
    private int id;

    public Stadsetning(int id){
        this.id = id;
    }

    public void setCords(double longitude, double latitude){
       this.latitude = latitude;
       this.longitude = longitude;
   }


    public void setShortDesc(String s){
        this.shortDesc = s;
    }
    public void setLongDesc(String s){
        this.longDesc = s;
    }



}
