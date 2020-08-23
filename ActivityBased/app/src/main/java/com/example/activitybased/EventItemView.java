package com.example.activitybased;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class EventItemView extends LinearLayout {

    TextView tvEvent;

    public EventItemView (Context context) {
        super(context);
        init(context);
    }

    public EventItemView (Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    //EventItem을 xml로 작성된 내용대로 구현하는 것 (inflate)
    public void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_event, this, true); //item_event에 코딩한 대로 this: 어디다가 구현할지, true: 구현해 말지
        //ViewGroup: view를 배치하는 방법을 정의해 놓는 곳 (LinearLayout이나 RelativeLayout 같은 것들

        tvEvent = (TextView) findViewById(R.id.tvEvent);
    }

    public void setEvent (String event) {

        tvEvent.setText(event);

    }

}
