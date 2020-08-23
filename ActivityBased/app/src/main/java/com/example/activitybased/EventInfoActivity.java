package com.example.activitybased;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventInfoActivity extends AppCompatActivity {

    TextView tvYearEventInfo;
    TextView tvDateEventInfo;
    TextView tvCategoryEventInfo;
    TextView tvTitleEventInfo;
    TextView tvPeriodEventInfo;
    TextView tvContentEventInfo;

    Button btConfirmEventInfo;

    FirebaseDatabase database;
    DatabaseReference databaseReference; //table을 참조하기 위한 참조변수

    String currentYear;
    String currentMonth;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        tvYearEventInfo = (TextView) findViewById(R.id.tvYearEventInfo);
        tvDateEventInfo = (TextView) findViewById(R.id.tvDateEventInfo);
        tvCategoryEventInfo = (TextView) findViewById(R.id.tvCategoryEventInfo);
        tvTitleEventInfo = (TextView) findViewById(R.id.tvTitleEventInfo);
        tvPeriodEventInfo = (TextView) findViewById(R.id.tvPeriodEventInfo);
        tvContentEventInfo = (TextView) findViewById(R.id.tvContentEventInfo);
        btConfirmEventInfo = (Button) findViewById(R.id.btConfirmEventInfo);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("event");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent intent = getIntent();
        Bundle fromEventListBundle = intent.getBundleExtra("EventInfoBundle");
        currentYear = fromEventListBundle.getString("year");
        currentMonth = fromEventListBundle.getString("month");
        currentDate = fromEventListBundle.getString("date");

        setHeaderText();

        btConfirmEventInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); //어플리케이션은 살아있지만 이 액티비티가 죽느냐
            }
        });

        //API call (Category, Title, Period, Content)
        init();

    }

    public void init() {
        //API call 발생한다면

        tvCategoryEventInfo.setText("수학");
        tvTitleEventInfo.setText("수학 퀴즈");
        tvPeriodEventInfo.setText("18시 00분 ~ 21시 00분");
        tvContentEventInfo.setText("수퀴 화이팅!!");
    }

    public void setHeaderText() {
        tvYearEventInfo.setText(currentYear);
        tvDateEventInfo.setText((Integer.parseInt(currentMonth) + 1) + "월 " + currentDate + "일");
    }

}
