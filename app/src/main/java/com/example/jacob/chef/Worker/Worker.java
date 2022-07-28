package com.example.jacob.chef.Worker;

import android.os.Parcel;
import android.os.Parcelable;

public class Worker implements Parcelable {
    public String cin;
    public String name;
    public String phone;
    public Integer ndj ;
    public Integer pdj ;
    public Integer total ;
    public Integer paied ;

    protected Worker(Parcel in) {
        cin = in.readString();
        name = in.readString();
        phone = in.readString();
        ndj = in.readInt();
        pdj = in.readInt();
        total = in.readInt();
        paied = in.readInt();
    }

    public Worker() {
    }

    public Worker(String cin, String name, String phone, Integer ndj, Integer pdj, Integer total, Integer paied) {
        this.cin = cin;
        this.name = name;
        this.phone = phone;
        this.ndj = ndj;
        this.pdj = pdj;
        this.total = total;
        this.paied = paied;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPaied() {
        return paied;
    }

    public void setPaied(Integer paied) {
        this.paied = paied;
    }

    public static Creator<Worker> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
