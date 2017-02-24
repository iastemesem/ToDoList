package com.iastemesem.todolist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class Note {

    private int id;
    private String title;
    private String body;
    private short special;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public short getIsSpecial() {
        return special;
    }

    public void setIsSpecial(short special) {
        this.special = special;
    }

    public  enum stato{
        COMPLETE,
        DELETE,
        ARCHIVED,
        RUNNING
    }
    public Note(){

    }

    public Note( String title, String body, short special) {
        this.title = title;
        this.body = body;
        this.special = (short) special;
    }





    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
