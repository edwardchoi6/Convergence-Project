package com.example.activitybased;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventCreateActivity extends AppCompatActivity {

    TextView tvYearEventCreate;
    TextView tvDateEventCreate;
    EditText etCategoryEventCreate;
    EditText etTitleEventCreate;
    TimePicker tpStartTimeEventCreate;
    TimePicker tpFinishTimeEventCreate;
    EditText etContentEventCreate;
    Button btCreateEventCreate;

    String currentYear;
    String currentMonth;
    String currentDate;

    String category;
    String title;
    String content;
    int startTime_hour;
    int startTime_min;
    int finishTime_hour;
    int finishTime_min;
    int unifiedStartTime;
    int unifiedFinishTime;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        tvYearEventCreate = (TextView) findViewById(R.id.tvYearEventCreate);
        tvDateEventCreate = (TextView) findViewById(R.id.tvDateEventCreate);
        etCategoryEventCreate = (EditText) findViewById(R.id.etCategoryEventCreate);
        etTitleEventCreate = (EditText) findViewById(R.id.etTitleEventCreate);
        tpStartTimeEventCreate = (TimePicker) findViewById(R.id.tpStartTimeEventCreate);
        tpFinishTimeEventCreate = (TimePicker) findViewById(R.id.tpFinishTimeEventCreate);
        etContentEventCreate = (EditText) findViewById(R.id.etContentEventCreate);
        btCreateEventCreate = (Button) findViewById(R.id.btCreateEventCreate);

        firebaseDatabase = FirebaseDatabase.getInstance();     databaseReference = firebaseDatabase.getReference("event");

        btCreateEventCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Api call
                 * Send event infos to server
                 * Create & Save Events
                 */
                saveEventInfo();
                convertHourtoMin();
                EventItem eventItem = new EventItem(content, currentYear, currentMonth, currentDate, title, category, Integer.toString(unifiedStartTime), Integer.toString(unifiedFinishTime));

                databaseReference.child("event").setValue(eventItem)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EventCreateActivity.this, "Succeed!", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EventCreateActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(EventCreateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tpStartTimeEventCreate.setIs24HourView(true); //12시로 할지, 24시로 할지
        tpFinishTimeEventCreate.setIs24HourView(true);
        tpStartTimeEventCreate.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                startTime_hour = hourOfDay;
                startTime_min = minute;
            }
        });
        tpFinishTimeEventCreate.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                finishTime_hour = hourOfDay;
                finishTime_min = minute;
            }
        });

        Intent intent = getIntent();
        Bundle fromEventListBundle = intent.getBundleExtra("EventCreateBundle");
        currentYear = fromEventListBundle.getString("year");
        currentMonth = fromEventListBundle.getString("month");
        currentDate = fromEventListBundle.getString("date");

        setHeaderText();

        saveEventInfo();
    }

    public void convertHourtoMin() {
        unifiedStartTime = 60*startTime_hour + startTime_min;
        unifiedFinishTime = 60*finishTime_hour + finishTime_min;
    }

    public void setHeaderText() {
        tvYearEventCreate.setText(currentYear);
        tvDateEventCreate.setText((Integer.parseInt(currentMonth)+1) +"월 " +  currentDate);
    }

    //Api
    //사용자가 입력한 값들을 가져오는 역할
    public void saveEventInfo() {
        category = etCategoryEventCreate.getText().toString();
        title = etTitleEventCreate.getText().toString();
        content = etContentEventCreate.getText().toString();
    }
}
