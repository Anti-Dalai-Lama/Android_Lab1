package com.blablaarthur.lab1;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Артур on 24.10.2016.
 */

class Note {
    public String Title;
    public String Description;
    public int Importance; //1 2 3
    public Calendar DateTime;
    public String Image;

    Note(String title, String desc, int imp, Calendar datetime, String image){
        Title = title;
        Description = desc;
        Importance = imp;
        DateTime = datetime;
        Image = image;
    }
}
