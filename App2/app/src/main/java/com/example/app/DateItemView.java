package com.example.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DateItemView extends RelativeLayout {

    TextView tvDate;

    public DateItemView(Context context)
    {
        super(context);
        init(context);
    }

    public DateItemView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
    }

    //DateItem을 xml로 작성된 내용대로 구현하는 것 (inflate)
    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_date, this, true); //item_date를 해당 뷰에 붙이기 위해서 만듦

        tvDate = (TextView) findViewById(R.id.tvDate);
    }

    public void setDate(int date, boolean isToday) {
        if (date != 0) {
            tvDate.setText(String.valueOf(date));
            //오늘의 날짜 표시
            if (isToday == true) {
                tvDate.setTextColor(getResources().getColor(R.color.colorAccent)); //getResources: 등록한 색들 사용
                tvDate.setTypeface(null, Typeface.BOLD_ITALIC);
            } else {
                tvDate.setTextColor(getResources().getColor(R.color.black));
                tvDate.setTypeface(null, Typeface.NORMAL);
            }
        } else {
            tvDate.setText("");
        }
    }

}
