package com.soccerbetstatisticpredictions.nanter1986.soccerbetstatisticpredictions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by user on 15/9/2017.
 */

public class SoccerMatch {
    String homeName;
    String awayName;
    Double homeValue;
    Double awayValue;
    Double homeMinimumOdds;
    Double awayDrawMinimumOdds;

    public SoccerMatch(String homeName, String awayName, Double homeValue, Double awayValue) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeValue = homeValue;
        this.awayValue = awayValue;

        Double helperHomePercentage=0.00064*(homeValue-awayValue)+0.4472;
        homeMinimumOdds=1.05/helperHomePercentage;
        awayDrawMinimumOdds=1.05/(1-helperHomePercentage);
    }

    public String printSelf(){
        String printOut=homeName+"<"+round(homeMinimumOdds,2)+">---"+awayName+"<"+round(awayDrawMinimumOdds,2)+">\n";
        return printOut;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
