package com.example.jacob.chef.Worker;

public class Day {
    public String date;
    public String cin;
    public Integer ndj ;
    public Integer pdj ;
    public Integer hd ;
    public Integer hf ;
    public Integer dej ;
    public String cmtr ;

    public Day() {
    }

    public Day(String date, String cin, Integer ndj, Integer pdj, Integer hd, Integer hf, Integer dej, String cmtr) {
        this.date = date;
        this.cin = cin;
        this.ndj = ndj;
        this.pdj = pdj;
        this.hd = hd;
        this.hf = hf;
        this.dej = dej;
        this.cmtr = cmtr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Integer getNdj() {
        return ndj;
    }

    public void setNdj(Integer ndj) {
        this.ndj = ndj;
    }

    public Integer getPdj() {
        return pdj;
    }

    public void setPdj(Integer pdj) {
        this.pdj = pdj;
    }

    public Integer getHd() {
        return hd;
    }

    public void setHd(Integer hd) {
        this.hd = hd;
    }

    public Integer getHf() {
        return hf;
    }

    public void setHf(Integer hf) {
        this.hf = hf;
    }

    public Integer getDej() {
        return dej;
    }

    public void setDej(Integer dej) {
        this.dej = dej;
    }

    public String getCmtr() {
        return cmtr;
    }

    public void setCmtr(String cmtr) {
        this.cmtr = cmtr;
    }
}
