package com.vis.merna.travelxapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Travel implements Parcelable {

    private String id;
    private String name;
    private String startPoint;
    private String endPoint;
    private String startLong;
    private String endLong;
    private String startLat;
    private String endLat;
    private String status;
    private Long date;
    private ArrayList<String> notes;

    public Travel() {
    }

    public Travel(String id, String name, String startPoint, String endPoint, String startLong,
                  String endLong, String startLat, String endLat, String status, Long date,
                  ArrayList<String> notes) {
        this.id = id;
        this.name = name;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startLong = startLong;
        this.endLong = endLong;
        this.startLat = startLat;
        this.endLat = endLat;
        this.status = status;
        this.date = date;
        this.notes = notes;
    }

    public Travel(String name, String startPoint, String endPoint, String startLong,
                  String endLong, String startLat, String endLat, String status, Long date) {
        this.name = name;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startLong = startLong;
        this.endLong = endLong;
        this.startLat = startLat;
        this.endLat = endLat;
        this.status = status;
        this.date = date;
    }

    protected Travel(Parcel in) {
        id = in.readString();
        name = in.readString();
        startPoint = in.readString();
        endPoint = in.readString();
        startLong = in.readString();
        endLong = in.readString();
        startLat = in.readString();
        endLat = in.readString();
        status = in.readString();
        if (in.readByte() == 0) {
            date = null;
        } else {
            date = in.readLong();
        }
        notes = in.createStringArrayList();
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getStartLong() {
        return startLong;
    }

    public void setStartLong(String startLong) {
        this.startLong = startLong;
    }

    public String getEndLong() {
        return endLong;
    }

    public void setEndLong(String endLong) {
        this.endLong = endLong;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(startPoint);
        parcel.writeString(endPoint);
        parcel.writeString(startLong);
        parcel.writeString(endLong);
        parcel.writeString(startLat);
        parcel.writeString(endLat);
        parcel.writeString(status);
        if (date == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(date);
        }
        parcel.writeStringList(notes);
    }
}
