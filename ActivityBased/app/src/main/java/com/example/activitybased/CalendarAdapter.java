package com.example.activitybased;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends BaseAdapter { //item은 형태가 정해진 게 아니라서 adapter가 필요해용

    //DateItem이라는 객체를 써야되니깐 그 변수를 만들어 준 것. 근데 DateItem이 하나가 아니니깐 Array로
    DateItem[] items;
    Context mContext; //m: member
    Calendar mCalendar; //현재 일자 계산

    int currentYear;
    int currentMonth;
    int currentDate;
    int firstDay;
    int lastDay;
    int todayYear;
    int todayMonth;
    int todayDate;

    public CalendarAdapter(Context mContext) { //Context: DateItem을 관리하려면 현재 Activity flow 상에서 정보를 받아와야 되는데 그걸 하는거
        super();
        this.mContext = mContext;
        init(); //Calendar를 만들어 쓰려는 경우 초기에 값들을 어떻게 하라
    }

    public void init() {
        items = new DateItem[7*6]; //신기하다 (캘린더 내 필요한 총 일수)
        mCalendar= Calendar.getInstance();

        Date date = new Date();
        mCalendar.setTime(date);//현재 시간 설정
        todayYear = mCalendar.get(Calendar.YEAR);
        todayMonth = mCalendar.get(Calendar.MONTH);
        todayDate = mCalendar.get(Calendar.DATE);


        //1일이 무슨 요일에 시작하는 지에 대한 method
        recalculate();
        //시작하는 요일에 따라서 실제 date를 set하는 method
        renameDate();
    }

    public void recalculate() {
        currentYear = mCalendar.get(Calendar.YEAR);
        currentMonth = mCalendar.get(Calendar.MONTH);
        currentDate = mCalendar.get(Calendar.DATE);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1); //1일이 무슨 요일인지 set

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek); //현재 주의 몇번째 열인지를 인자로
        lastDay = getLastDay(); //30일인지 31일인지 28인지 29일인지

    }

    public void renameDate() {
        for (int i=0; i<42; i++) {
            int dayNum = (i+1) - firstDay;
            if (dayNum < 1 || dayNum > lastDay) {
                dayNum = 0;
            }
            items[i] = new DateItem(dayNum);
        }
    }

    public int getFirstDay(int dayOfWeek) {
        int result = 0;
        if (dayOfWeek == Calendar.SUNDAY) {result = 0;}
        else if (dayOfWeek == Calendar.MONDAY) {result = 1;}
        else if (dayOfWeek == Calendar.TUESDAY) {result = 2;}
        else if (dayOfWeek == Calendar.WEDNESDAY) {result = 3;}
        else if (dayOfWeek == Calendar.THURSDAY) {result = 4;}
        else if (dayOfWeek == Calendar.FRIDAY) {result = 5;}
        else if (dayOfWeek == Calendar.SATURDAY) {result = 6;}

        return result;
    }

    public int getLastDay () {
        switch (currentMonth) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                if (((currentYear%4==0) && (currentYear%100!=0)) || (currentYear%400==0)){
                    return 29;
                }
                else {
                    return 28;
                }
        }
    }

    public void setPreviousMonth() { //ovenapp에서 뒤로가기 버튼을 정의했었다.
        mCalendar.add(Calendar.MONTH, -1);
        recalculate();
        renameDate();
    }

    public void setNextMonth() { //ovenapp에서 다음 달로 가는 버튼을 정의했었다.
        mCalendar.add(Calendar.MONTH, 1);
        recalculate();
        renameDate();
    }

    //current들을 가져오는 method
    public String getCurrentYear() { return Integer.toString(currentYear); }
    public String getCurrentMonth() { return Integer.toString(currentMonth); }
    public String getCurrentDate() { return Integer.toString(currentDate); }

    @Override
    public int getCount() { //item 개수가 몇 개인지 반환하는 메소드
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //어떻게 보여줄 지 결정
        DateItemView gvView = null;
        boolean isToday = false;

        //view란? view가 모여 activity를 이룸
        if (convertView == null) {
            gvView = new DateItemView(mContext);
        } else {
            gvView = (DateItemView) convertView;
        }

        //오늘의 날짜 표시
        if (todayDate == items[position].getDate() && todayMonth == currentMonth && todayYear == currentYear) {
            isToday = true;
        }

        gvView.setDate(items[position].getDate(), isToday);
        return gvView;
    }

}
