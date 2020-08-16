package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //달력에 들어가는 아이템들 정의 -> 어댑터를 정의 -> getView method 정의

    ImageView ivPreviousMonth, ivNextMonth;
    TextView tvYear, tvDateMain;
    GridView gvCalendar;

    CalendarAdapter caAdapter;
    String currentYear;
    String currentMonth;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Activity가 생성되었을 때 뭘 할거냐
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPreviousMonth = (ImageView) findViewById(R.id.ivPreviousMonth);
        ivNextMonth = (ImageView) findViewById(R.id.ivNextMonth);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvDateMain = (TextView) findViewById(R.id.tvDateMain);
        gvCalendar = (GridView) findViewById(R.id.gvCalendar);

        caAdapter = new CalendarAdapter(this);
        gvCalendar.setAdapter(caAdapter);

        setHeaderText();

        //Android Bundle is used to pass data between activities. The values that are to be passed are mapped to String keys which are later used in the next activity to retrieve the values.
        final Bundle toEventListBundle = new Bundle(); //When sending, it shouldn't be reinstateable
        //bundle에 무엇을 packaging할 지를 정해야 함
        toEventListBundle.putString("year", currentYear);
        toEventListBundle.putString("month", currentMonth);
        toEventListBundle.putString("date", currentDate);

        //gridview를 클릭했을 때의 이벤트를 처리
        gvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 해당하는 이벤트가 있는지 서버에 -> DB -> 서버 -> client
                 * API call
                 */
                Intent intent = new Intent(MainActivity.this, EventListActivity.class);
                intent.putExtra("eventListBundle", toEventListBundle);
                startActivity(intent);
            }
        });

        ivPreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caAdapter.setPreviousMonth();
                caAdapter.notifyDataSetChanged(); //사용자가 보는 view에서 화면을 바꾸기 위해
                setHeaderText();
            }
        });

        ivNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caAdapter.setNextMonth();
                caAdapter.notifyDataSetChanged(); //동일 화면에서 어댑터 내용이 바뀌었기 때문에
                setHeaderText();

        });


    }

    public void setHeaderText() {
        currentYear = caAdapter.getCurrentYear();
        currentMonth = caAdapter.getCurrentMonth();
        currentDate = caAdapter.getCurrentDate();

        tvYear.setText(currentYear + "년");
        tvDateMain.setText((Integer.parseInt(currentMonth) + 1) + "월");
    }

}
