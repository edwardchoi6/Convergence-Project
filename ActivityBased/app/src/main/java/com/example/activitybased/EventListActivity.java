package com.example.activitybased;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity { //뒤로가기는 manifest에서 구현되었다. android:parentActivityName

    TextView tvYearEventList;
    TextView tvDateEventList;
    ListView lvEventList;

    ArrayList<EventItem> eventItems;
    EventAdapter eventAdapter;

    String currentYear;
    String currentMonth;
    String currentDate;

    CalendarAdapter calendarAdapter;

    Button btEventCreation;

    FirebaseDatabase database;
    DatabaseReference databaseReference; //table을 참조하기 위한 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        tvYearEventList = (TextView) findViewById(R.id.tvYearEventList);
        tvDateEventList = (TextView) findViewById(R.id.tvDateEventList);
        lvEventList = (ListView) findViewById(R.id.lvEventList);
        btEventCreation = (Button) findViewById(R.id.btEventCreation);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("event");

        eventItems = new ArrayList<EventItem>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) { //database에서 data를 받아오는 역할
                eventItems.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) { //하나하나의 값들을 snapshot이라는 이름으로 가져오겠다
                    EventItem eventItem = snapshot.getValue(EventItem.class);
                    if((eventItem.getYear() == currentYear) && (eventItem.getMonth() == currentMonth)
                            && (eventItem.getDate() == currentDate)) {
                        eventItems.add(eventItem);
                    }
                    //eventAdapter.notifyDataSetChanged(); //adapter에 데이터들이 변경되었음을 알려주는 역할 (여기에 안 넣는 이유는 반복문때마다 해야 되는게 불필요해서 + 지금까지 eventAdapter는 null값이다 정의를 밑에서 했으니깐)
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { //data를 가져오는 과정에서 에러가 발생하면 호출됨

            }
        });

        //init(); //초기화 함수

        eventAdapter = new EventAdapter(this, eventItems);
        lvEventList.setAdapter(eventAdapter); //ListView에 내장된 method

        calendarAdapter = new CalendarAdapter(this);
        lvEventList.setAdapter(eventAdapter);

        Intent intent = getIntent();
        Bundle fromMainBundle = intent.getBundleExtra("eventListBundle");
        currentYear = fromMainBundle.getString("year");
        currentMonth = fromMainBundle.getString("month");
        currentDate = fromMainBundle.getString("date");

        setHeaderText(); //year랑 dateview라고 써 있는 거에 실제로 값이 들어가야 되니깐

        //toEventInfoBundle과 toEventCreateBundle은 같은 내용이다.
        final Bundle toEventInfoBundle = new Bundle();
        toEventInfoBundle.putString("year", currentYear);
        toEventInfoBundle.putString("month", currentMonth);
        toEventInfoBundle.putString("date", currentDate);

        final Bundle toEventCreateBundle = new Bundle();
        toEventCreateBundle.putString("year", currentYear);
        toEventCreateBundle.putString("month", currentMonth);
        toEventCreateBundle.putString("date", currentDate);

        lvEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventListActivity.this, EventInfoActivity.class);
                intent.putExtra("EventInfoBundle", toEventInfoBundle);
                startActivity(intent);
            }
        });

        btEventCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventListActivity.this, EventCreateActivity.class);
                intent.putExtra("EventCreateBundle", toEventCreateBundle);
                startActivity(intent);
            }
        });

    }

    //onCreate될 때 필요한 것들 (View와 class 변수들을 mapping)
    /*
    public void init() {
        eventItems = new ArrayList<EventItem>();
        eventItems.add(new EventItem("동아리 모임"));
        eventItems.add(new EventItem("수학 과제"));
        eventItems.add(new EventItem("퀴즈"));
    }
*/
    public void setHeaderText() {

        tvYearEventList.setText(currentYear);
        tvYearEventList.setText(Integer.parseInt(currentMonth) + 1 + "월 " + currentDate + "일"); //여기서는 달이 바뀔 일이 없으므로 그냥
    }
}
