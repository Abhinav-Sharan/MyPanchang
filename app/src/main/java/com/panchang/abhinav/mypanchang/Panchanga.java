package com.panchang.abhinav.mypanchang;

/**
 * Created by Abhinav on 28/05/2017.
 */

public  class Panchanga{
    public  String dtoday;
    public  String dyoga;
    public  String dnakshatra;
    public  String dtithi;
    public  String dkarana;
    public  String dpaksha;
    public  String drashi;

    @Override
    public String toString(){
        return  "\n Date: " + dtoday  +
                "\n Yoga:" + dyoga +
                "\n Nakshastra:" + dnakshatra +
                "\n Tithi:" + dtithi +
                "\n Karana:" + dkarana +
                "\n Paksha:" + dpaksha +
                "\n Rashi:" + drashi;
    }
}
