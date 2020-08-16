package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CalendarFragment calendarFragment;
    private FoodFragment foodFragment;
    private TimeTableFragment timeTableFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        calendarFragment = new CalendarFragment();
        foodFragment = new FoodFragment();
        timeTableFragment = new TimeTableFragment();

        //제일 처음 띄울 view를 세팅
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, calendarFragment).commitAllowingStateLoss();

        //아이콘을 선택했을 때 원하는 fragment가 띄워질 수 있도록 listener 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calendarFragment:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, calendarFragment)
                                .commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.foodFragment:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, foodFragment)
                                .commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.timeTableFragment:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, timeTableFragment)
                                .commitAllowingStateLoss();
                        return true;
                    }

                    default:
                        return false;
                }
            }
        });

    }
}