package com.stratazima.testapp.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;

/**
 * Created by Esau on 5/15/2014.
 */
public enum DateCreate {Week;
    public JSONArray getDateObject () {
        JSONArray mainObj = new JSONArray();

        // Create JSON Objects for days
        JSONObject day1 = new JSONObject();
        JSONObject day2 = new JSONObject();
        JSONObject day3 = new JSONObject();
        JSONObject day4 = new JSONObject();
        JSONObject day5 = new JSONObject();
        JSONObject day6 = new JSONObject();
        JSONObject day7 = new JSONObject();

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_WEEK);

        try {
            // Day 1
            day1.put("DayOfWeek", dayOfWeek(date));
            // Day 2
            day2.put("DayOfWeek", dayOfWeek(dateCheck(date + 1)));
            // Day 3
            day3.put("DayOfWeek", dayOfWeek(dateCheck(date + 2)));
            // Day 4
            day4.put("DayOfWeek", dayOfWeek(dateCheck(date + 3)));
            // Day 5
            day5.put("DayOfWeek",dayOfWeek(dateCheck(date + 4)));
            // Day 6
            day6.put("DayOfWeek", dayOfWeek(dateCheck(date + 5)));
            // Day 7
            day7.put("DayOfWeek", dayOfWeek(dateCheck(date + 6)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainObj.put(day1);
        mainObj.put(day2);
        mainObj.put(day3);
        mainObj.put(day4);
        mainObj.put(day5);
        mainObj.put(day6);
        mainObj.put(day7);

        return mainObj;
    }

    private int dateCheck (int theDate) {
        if(theDate > 7 ) {
            theDate = theDate - 7;
        }
        return theDate;
    }

    private String dayOfWeek (int intOfDate) {
        String DOW = null;
        switch (intOfDate) {
            case 1:
                DOW = "Sunday";
                break;
            case 2:
                DOW = "Monday";
                break;
            case 3:
                DOW = "Tuesday";
                break;
            case 4:
                DOW = "Wednesday";
                break;
            case 5:
                DOW = "Thursday";
                break;
            case 6:
                DOW = "Friday";
                break;
            case 7:
                DOW = "Saturday";
                break;
        }

        return DOW;
    }
}
