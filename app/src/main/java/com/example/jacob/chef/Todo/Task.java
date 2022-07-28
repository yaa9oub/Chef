package com.example.jacob.chef.Todo;

public class Task {
        int rang , rev ;
        String label , date , time , details ;


    public Task() {
    }

    public Task(int rang, int rev, String label, String date, String time, String details) {
        this.rang = rang;
        this.rev = rev;
        this.label = label;
        this.date = date;
        this.time = time;
        this.details = details;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
