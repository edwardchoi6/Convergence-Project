package com.example.activitybased;

public class EventItem {

    String content;
    String year;
    String month;
    String date;
    String title;
    String category;
    String startTime;
    String finishTime;

    public EventItem() {}

    public EventItem(String content) {
        this.content = content;
    }

    public EventItem(String content, String year, String month, String date, String title, String category, String startTime, String finishTime) {
        this.content = content;
        this.year = year;
        this.month = month;
        this.date = date;
        this.title = title;
        this.category = category;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
