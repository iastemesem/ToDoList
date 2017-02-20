package com.iastemesem.todolist;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class Note {
    private String title;
    private String dataCreazione;
    private String ultimaModifica;
    private String body;
    private String dataScadenza;
    private enum stato{
        COMPLETE,
        DELETE,
        ARCHIVED,
        RUNNING
    }

    public Note(String dataCreazione, String title, String body, String dataScadenza, String ultimaModifica) {
        this.dataCreazione = dataCreazione;
        this.title = title;
        this.body = body;
        this.dataScadenza = dataScadenza;
        this.ultimaModifica = ultimaModifica;
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

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
