package com.example.jacob.chef.Bills;

public class Fact {
    Integer id ;
    Integer qt ;
    String des ;
    Integer pu , pt ;

    public Fact(Integer id, Integer qt, String des, Integer pu, Integer pt) {
        this.id = id;
        this.qt = qt;
        this.des = des;
        this.pu = pu;
        this.pt = pt;
    }

    public Fact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getPu() {
        return pu;
    }

    public void setPu(Integer pu) {
        this.pu = pu;
    }

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }
}
