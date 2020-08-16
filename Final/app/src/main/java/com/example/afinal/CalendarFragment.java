package com.example.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarFragment extends Fragment {

    private FragmentCalendarListener listener;
    private TextView tvCalendar;

    public interface FragmentCalendarListener {
        void onInputCalendarSent(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        tvCalendar = view.findViewById(R.id.temp_calendar);


        return view;
    }
}