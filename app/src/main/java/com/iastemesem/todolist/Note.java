package com.iastemesem.todolist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class Note {


    private String title;
    private String body;

    public  enum stato{
        COMPLETE,
        DELETE,
        ARCHIVED,
        RUNNING
    }

    public Note( String title, String body) {
        this.title = title;
        this.body = body;

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
