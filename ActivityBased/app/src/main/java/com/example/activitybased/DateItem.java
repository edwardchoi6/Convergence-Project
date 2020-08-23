package com.example.activitybased;

public class DateItem {

    int date; //1,2,3,4,5,6,7,--- 일
    //String event;

    public DateItem(int date){
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateItem{" +
                "date=" + date +
                '}';
    }

}
