package com.panchang.abhinav.mypanchang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.*;

public class Panchang {

    static String[] month={"January","February","March","April","May","June",
            "July","August","September","October","November","December"};

    static String[] rashi={"Mesha","Vrishabha","Mithuna","Karka","Simha","Kanya","Tula",
            "Vrischika","Dhanu","Makara","Kumbha","Meena"};

    static String[] day={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    static String[] tithi={"Prathame","Dwithiya","Thrithiya","Chathurthi","Panchami",
            "Shrashti","Saptami","Ashtami","Navami","Dashami","Ekadashi",
            "Dwadashi","Thrayodashi","Chaturdashi","Poornima","Prathame",
            "Dwithiya","Thrithiya","Chathurthi","Panchami","Shrashti",
            "Saptami","Ashtami","Navami","Dashami","Ekadashi","Dwadashi",
            "Thrayodashi","Chaturdashi","Amavasya"};

    static String[] karan={"Bava","Balava","Kaulava","Taitula","Garija","Vanija",
            "Visti","Sakuni","Chatuspada","Naga","Kimstughna"};

    static String[] yoga={"Vishkambha","Prithi","Ayushman","Saubhagya","Shobhana",
            "Atiganda","Sukarman","Dhrithi","Shoola","Ganda","Vridhi",
            "Dhruva","Vyaghata","Harshana","Vajra","Siddhi","Vyatipata",
            "Variyan","Parigha","Shiva","Siddha","Sadhya","Shubha","Shukla",
            "Bramha","Indra","Vaidhruthi"};

    static String[] nakshatra={"Ashwini","Bharani","Krittika","Rohini","Mrigashira","Ardhra",
            "Punarvasu","Pushya","Ashlesa","Magha","Poorva Phalguni","Uttara Phalguni",
            "Hasta","Chitra","Swathi","Vishaka","Anuradha","Jyeshta","Mula",
            "Poorva Ashada","Uttara Ashada","Sravana","Dhanishta","Shatabisha",
            "Poorva Bhadra","Uttara Bhadra","Revathi"};

    private static final Double D2R  = (Math.PI/180.0);
    private static final Double R2D = (180.0/Math.PI);
    static double Ls, Lm, Ms, Mm;
    static Double REV(double d)
    {
        return ((d)-floor((d)/360.0)*360.0);

    }
    //Calculate Aynamsa
    static double getAyanamsa(double d)
    {
        double t, o, l, ayan;
        t = (d+36523.5)/36525;
        o = 259.183275-1934.142008333206*t+0.0020777778*t*t;
        l = 279.696678+36000.76892*t+0.0003025*t*t;
        ayan = 17.23*sin((o)*D2R)+1.27*sin((l*2)*D2R)-(5025.64+1.11*t)*t;
        //Based on Lahiri
        ayan = (ayan-80861.27)/3600.0;
        return ayan;
    }

    //Longitude of Sun
    static double getSunLong(double d)
    {
        double w, a, e, M, E, x, y, r, v, tmp;

        w = 282.9404+4.70935e-5*d;
        a = 1.000000;
        e = 0.016709-1.151e-9*d;
        M = REV(356.0470+0.9856002585*d);
        Ms = M;
        Ls = w+M;

        tmp = M*D2R;
        E = M+R2D*e*sin(tmp)*(1+e*cos(tmp));

        tmp = E*D2R;
        x = cos(tmp)-e;
        y = sin(tmp)*sqrt(1-e*e);

        r = sqrt(x*x + y*y);
        v = REV(R2D*atan2(y,x));

        return REV(v+w);
    }

    //Longitude of Moon
    static double getMoonLong(double d)
    {
        double N, i, w, a, e, M, E, Et, x, y, r, v, xec, yec, zec, D, F, tmp, tmp1, tmp2, lon;

        N = 125.1228-0.0529538083*d;
        i = 5.1454;
        w = REV(318.0634+0.1643573223*d);
        a = 60.2666;
        e = 0.054900;
        M = REV(115.3654+13.0649929509*d);
        Mm = M;
        Lm = N+w+M;

        //Calculate Eccentricity anamoly
        tmp = M*D2R;
        E = M+R2D*e*sin(tmp)*(1+e*cos(tmp));

        tmp = E*D2R;
        Et = E-(E-R2D*e*sin(tmp)-M)/(1-e*cos(tmp));

        do {
            E = Et;
            tmp = E*D2R;
            Et = E-(E-R2D*e*sin(tmp)-M)/(1-e*cos(tmp));
        } while(E-Et>0.005);

        tmp = E*D2R;
        x = a*(cos(tmp)-e);
        y = a*sqrt(1-e*e)*sin(tmp);

        r = sqrt(x*x + y*y);
        v = REV(R2D*atan2(y,x));

        tmp = D2R*N;
        tmp1 = D2R*(v+w);
        tmp2 = D2R*i;
        xec = r*(cos(tmp)*cos(tmp1)-sin(tmp)*sin(tmp1)*cos(tmp2));
        yec = r*(sin(tmp)*cos(tmp1)+cos(tmp)*sin(tmp1)*cos(tmp2));
        zec = r*sin(tmp1)*sin(tmp2);

        //Do some corrections
        D = Lm - Ls;
        F = Lm - N;

        lon = R2D*atan2(yec,xec);

        lon+= -1.274*sin((Mm-2*D)*D2R);
        lon+= +0.658*sin((2*D)*D2R);
        lon+= -0.186*sin((Ms)*D2R);
        lon+= -0.059*sin((2*Mm-2*D)*D2R);
        lon+= -0.057*sin((Mm-2*D+Ms)*D2R);
        lon+= +0.053*sin((Mm+2*D)*D2R);
        lon+= +0.046*sin((2*D-Ms)*D2R);
        lon+= +0.041*sin((Mm-Ms)*D2R);
        lon+= -0.035*sin((D)*D2R);
        lon+= -0.031*sin((Mm+Ms)*D2R);
        lon+= -0.015*sin((2*F-2*D)*D2R);
        lon+= +0.011*sin((Mm-4*D)*D2R);

        return REV(lon);
    }
    //Calculate Panchanga
    public Panchanga calculatePanchanga(int dd, int mm, int yy, double hr, double zhr){
        Panchanga p = new Panchanga();
        double d, ayanamsa, slon, mlon, tmlon, tslon;
        int n;
        String inputDateStr = String.format("%s/%s/%s", dd, mm, yy);
        Date inputDate = null;
        try {
            inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        p.setDtoday(dayOfWeek);


        //Calculate day number since 2000 Jan 0.0 TDT
        d = (367*yy-7*(yy+(mm+9)/12)/4+275*mm/9+dd-730530);

        //Calculate Ayanamsa, moon and sun longitude
        ayanamsa = getAyanamsa(d);
        slon = getSunLong(d+((hr-zhr)/24.0));
        mlon = getMoonLong(d+((hr-zhr)/24.0));

        //Calculate Tithi and Paksha
        tmlon = mlon+((mlon<slon)?360:0);
        tslon = slon;
        n = (int)((tmlon-tslon)/12);
        p.setDtithi(tithi[n]);

        if(n<=14)
        {
            p.setDpaksha("Shukla");
        }
        else
        {
            p.setDpaksha("Krishna");
        }
        //Calculate Nakshatra
        tmlon = REV(mlon+ayanamsa);
        p.setDnakshatra(nakshatra[(int)(tmlon*6/80)]);
        //Calculate Yoga
        tmlon = mlon+ayanamsa;
        tslon = slon+ayanamsa;
        p.setDyoga(yoga[(int)(REV(tmlon+tslon)*6/80)]);
        //Calculate Karana
        tmlon = mlon+((mlon<slon)?360:0);
        tslon = slon;
        n = (int)((tmlon-tslon)/6);
        if(n==0) n=10;
        if(n>=57) n-=50;
        if(n>0 && n<57) n=(n-1)-((n-1)/7*7);

        p.setDkarana(karan[n]);
        //Calculate the rashi in which the moon is present
        tmlon = REV(mlon+ayanamsa);
        p.setDrashi(rashi[(int)(tmlon/30)]);
        return p;
    }
}
