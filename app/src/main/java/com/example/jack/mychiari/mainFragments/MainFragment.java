package com.example.jack.mychiari.mainFragments;

import android.content.Context;
import java.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.mychiari.R;

public class MainFragment extends Fragment {

    //Widgets
    ImageButton settings;
    Button report;
    TextView greetingText;
    TextView reportText;
    ImageView badge;

    //User info
    String user;
    int numReport;
    int level;
    int timeOfDay;
    int progress;

    Main main;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //Set up settings button
        settings = (ImageButton) v.findViewById(R.id.mainSettingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.settings();
            }
        });

        //Set up texts
        greetingText = (TextView) v.findViewById(R.id.mainGreetingText);
        reportText = (TextView) v.findViewById(R.id.mainReportText);

        //Get time of day
        getTimeOfDay();

        //Get user data
        user = main.getUser();
        numReport = main.getNumReports();

        //Set texts
        setGreetingText();
        setReportText();

        return v;
    }

    //Sets greeting text
    //TODO Implement actual logic
    private void setGreetingText(){
        String beginning = "Hey"; //default
        if (timeOfDay == 0){
            beginning = "Good morning ";
        } else if (timeOfDay == 1){
            beginning = "Good afternoon ";
        } else if (timeOfDay == 2){
            beginning = "Good evening ";
        }

        String text = beginning + user + "!";

        greetingText.setText(text);
    }

    //Set report number text
    private void setReportText(){
        reportText.setText("Symptom reports completed: " + numReport); //TODO use string resource without crashing?
    }

    //Gets time of day
    private void getTimeOfDay(){
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            timeOfDay = 0;
        } else if ( hour < 17){
            timeOfDay = 1;
        } else if (hour < 21){
            timeOfDay = 2;
        } else {
            timeOfDay = 3;
        }
    }

    //interface to communicate with mainActivity
    public interface Main{
        void settings(); //Goes to settings
        void report(); //Goes to Report Activity
        String getUser(); //Gets user's name
        int getNumReports();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Makes sure activity has implemented NewPage interface
        try {
            main = (Main) context;
        } catch (ClassCastException e) {
            Log.e("IMPLEMENT", "Activity must implement NewPage");
        }
    }
}
