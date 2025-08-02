package com.example.station.entity;

/**
 * 各市年度工单数量的实体
 */
public class WorkSheetNum {
    private int cityId;
    private int year;
    private int month;
    private int worksheet_quantity;

    public WorkSheetNum() {
    }

    public WorkSheetNum(int cityId, int year, int month, int worksheet_quantity) {
        this.cityId = cityId;
        this.year = year;
        this.month = month;
        this.worksheet_quantity = worksheet_quantity;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWorksheet_quantity() {
        return worksheet_quantity;
    }

    public void setWorksheet_quantity(int worksheet_quantity) {
        this.worksheet_quantity = worksheet_quantity;
    }

    @Override
    public String toString() {
        return "WorkSheetNum{" +
                "cityId=" + cityId +
                ", year=" + year +
                ", month=" + month +
                ", worksheet_quantity=" + worksheet_quantity +
                '}';
    }
}
