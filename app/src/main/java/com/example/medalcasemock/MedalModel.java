package com.example.medalcasemock;

import static com.example.medalcasemock.Constants.*;

public class MedalModel {

    private int medalId;
    private MedalType medalType;
    private int medalIcon;
    private int medalTitle;
    private String medalRecord;

    public int getMedalId() {
        return medalId;
    }

    public void setMedalId(int id) {
        this.medalId = id;
    }

    public MedalType getMedalType() {
        return medalType;
    }

    public void setMedalType(MedalType type) {
        this.medalType = type;
    }

    public int getMedalIcon() {
        return medalIcon;
    }

    public void setMedalIcon(int icon) {
        this.medalIcon = icon;
    }

    public int getMedalTitle() {
        return medalTitle;
    }

    public void setMedalTitle(int title) {
        this.medalTitle = title;
    }

    public String getMedalRecord() {
        return medalRecord;
    }

    public void setMedalRecord(String record) {
        this.medalRecord = record;
    }

}
