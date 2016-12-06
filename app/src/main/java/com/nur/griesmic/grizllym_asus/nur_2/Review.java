package com.nur.griesmic.grizllym_asus.nur_2;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GrizllyM-ASUS on 07.11.2016.
 */

public class Review {
    private int id;
    private String text;
    private int rating;
    private Date date;

    public Review(int id, String text, int rating, Date date){
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.date = date;
    }

    public Review(){
        this.id = 0;
        this.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur magna erat, ornare in ligula fringilla, semper elementum dolor.";
        this.rating = 3;
        this.date = new Date();
    }

    public String getText(){ return this.text; }
    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.date);
    }

    public int getRating() {
        return this.rating;
    }
}
