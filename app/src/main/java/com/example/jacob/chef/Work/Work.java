package com.example.jacob.chef.Work;

public class Work {
    int id ;
    String label , place , contact ;
    int duration , worker , mdo , divers ;

    public Work(int id, String label, String place, String contact, int duration, int worker, int mdo, int divers) {
        this.id = id;
        this.label = label;
        this.place = place;
        this.contact = contact;
        this.duration = duration;
        this.worker = worker;
        this.mdo = mdo;
        this.divers = divers;
    }

    public Work() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public int getMdo() {
        return mdo;
    }

    public void setMdo(int mdo) {
        this.mdo = mdo;
    }

    public int getDivers() {
        return divers;
    }

    public void setDivers(int divers) {
        this.divers = divers;
    }
}
