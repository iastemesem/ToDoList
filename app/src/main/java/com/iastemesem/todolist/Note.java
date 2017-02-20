package com.iastemesem.todolist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class Note {
    private static  Calendar cal = new GregorianCalendar();
    private static int giorno = cal.get(Calendar.DAY_OF_MONTH);
    private static int mese = cal.get(Calendar.MONTH);
    private static int anno = cal.get(Calendar.YEAR);
    public static final String TODAY_DATE = ""+giorno+"/"+mese+"/"+anno;
    public static final String DEADLNE_DATE = ""+(giorno+7)+"/"+mese+"/"+anno;
    private Date d = new Date();
    private String title;
    private String dataCreazione;
    private String ultimaModifica;
    private String body;
    private String dataScadenza;
    public  enum stato{
        COMPLETE,
        DELETE,
        ARCHIVED,
        RUNNING
    }

    public Note( String title, String body) {
        this.title = title;
        this.body = body;
        this.dataCreazione = TODAY_DATE;
        this.dataScadenza = DEADLNE_DATE;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(String ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
