package org.zkoss.zkdatahandlerdemo.bean;

import java.io.Serializable;
import java.util.Date;

public class SchedulerEvent implements Serializable{
    private String id;
    private Date start_date, end_date;
    private String subject, text;

    public SchedulerEvent(String id, Date start_date, Date end_date, String subject, String text) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.subject = subject;
        this.text = text;
    }

    public SchedulerEvent(String id, Date start_date, Date end_date, String text) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.text = text;
    }

    public SchedulerEvent(Date start_date, Date end_date, String subject, String text) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.subject = subject;
        this.text = text;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
