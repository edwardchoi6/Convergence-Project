package com.example.app;

import android.content.Context;
import android.util.EventLog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter{

    Context mContext = null; //Context: 우리는 가만히 있는데 activity는 현재 내가 어떤 상태인지에 대한 상태를 알려주는 애
    ArrayList<EventItem> eventItems;

    public EventAdapter(Context mContext, ArrayList<EventItem> eventItems) {
        super();
        this.mContext = mContext;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int position) {
        return eventItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //adapter가 관리하는 데이터를 어떻게 가져올 지를 결정하는 메소드이다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventItemView lvEvent = null;

        if (convertView == null) {
            lvEvent = new EventItemView(mContext);
        } else {
            lvEvent = (EventItemView) convertView;
        }

        lvEvent.setEvent(eventItems.get(position).getContent());

        return lvEvent;
    }
}
