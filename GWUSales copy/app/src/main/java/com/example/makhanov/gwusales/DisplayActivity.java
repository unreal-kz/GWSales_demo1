package com.example.makhanov.gwusales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    }

    /*udacity version
    String[] forecastArray = {
            "Today-Sunny-88/63",
            "Tomorrow-Cloudy-88/63",
            "Weds-Sunny-88/63",
            "Thurs-Sunny-88/63",
            "Fri-Sunny-88/63"
    };

    List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));*/

    ArrayList<String> myList = new ArrayList<>();

    public ArrayList<String> getMyList() {
        myList.add("Today-Sunny-88/63");
        myList.add("Tomorrow-Cloudy-88/63");
        myList.add("Weds-Sunny-88/63");
        myList.add("Thurs-Sunny-88/63");
        myList.add("Fri-Sunny-88/63");
        myList.add("Sun-Foggy-88/63");
        myList.add("Sat-Sunny-88/63");
        return myList;
    }

    public void setMyList(ArrayList<String> myList) {
        this.myList = myList;
    }
}
