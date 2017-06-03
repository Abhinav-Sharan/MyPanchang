package com.panchang.abhinav.mypanchang;

/**
 * Created by Abhinav on 28/05/2017.
 */

public  class Panchanga{
    private String dtoday;
    private  String dyoga;
    private  String dnakshatra;
    private  String dtithi;
    private  String dkarana;
    private  String dpaksha;
    private  String drashi;


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

    public void setDtoday(String dtoday) {
        this.dtoday = dtoday;
    }

    public void setDyoga(String dyoga) {
        this.dyoga = dyoga;
    }

    public void setDnakshatra(String dnakshatra) {
        this.dnakshatra = dnakshatra;
    }

    public void setDtithi(String dtithi) {
        this.dtithi = dtithi;
    }

    public void setDkarana(String dkarana) {
        this.dkarana = dkarana;
    }

    public void setDpaksha(String dpaksha) {
        this.dpaksha = dpaksha;
    }

    public void setDrashi(String drashi) {
        this.drashi = drashi;
    }
}
