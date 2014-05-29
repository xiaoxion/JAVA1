package com.stratazima.testapp.home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;

/**
 * Created by Esau on 5/15/2014.
 */
public enum DateCreate {TODAY(1), TWO_DAY(2), FIVE_DAY(5), WEEKLY(7), TEN_DAY(10);
    private final int selectedDays;

    DateCreate(int selectedDays) {
        this.selectedDays = selectedDays;
    }

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
        JSONObject day8 = new JSONObject();
        JSONObject day9 = new JSONObject();
        JSONObject day10 = new JSONObject();

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_WEEK);

        if (selectedDays == 1) {
            try {
                day1.put("DayOfWeek", dayOfWeek(date));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainObj.put(day1);
        } else
        if (selectedDays == 2) {
            try {
                day1.put("DayOfWeek", dayOfWeek(date));
                day2.put("DayOfWeek", dayOfWeek(dateCheck(date + 1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainObj.put(day1);
            mainObj.put(day2);
        } else
        if (selectedDays == 5) {
            try {
                day1.put("DayOfWeek", dayOfWeek(date));
                day2.put("DayOfWeek", dayOfWeek(dateCheck(date + 1)));
                day3.put("DayOfWeek", dayOfWeek(dateCheck(date + 2)));
                day4.put("DayOfWeek", dayOfWeek(dateCheck(date + 3)));
                day5.put("DayOfWeek",dayOfWeek(dateCheck(date + 4)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainObj.put(day1);
            mainObj.put(day2);
            mainObj.put(day3);
            mainObj.put(day4);
            mainObj.put(day5);

        } else
        if (selectedDays == 7) {
            try {
                day1.put("DayOfWeek", dayOfWeek(date));
                day2.put("DayOfWeek", dayOfWeek(dateCheck(date + 1)));
                day3.put("DayOfWeek", dayOfWeek(dateCheck(date + 2)));
                day4.put("DayOfWeek", dayOfWeek(dateCheck(date + 3)));
                day5.put("DayOfWeek",dayOfWeek(dateCheck(date + 4)));
                day6.put("DayOfWeek", dayOfWeek(dateCheck(date + 5)));
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
        } else
        if (selectedDays == 10) {
            try {
                day1.put("DayOfWeek", dayOfWeek(date));
                day2.put("DayOfWeek", dayOfWeek(dateCheck(date + 1)));
                day3.put("DayOfWeek", dayOfWeek(dateCheck(date + 2)));
                day4.put("DayOfWeek", dayOfWeek(dateCheck(date + 3)));
                day5.put("DayOfWeek",dayOfWeek(dateCheck(date + 4)));
                day6.put("DayOfWeek", dayOfWeek(dateCheck(date + 5)));
                day7.put("DayOfWeek", dayOfWeek(dateCheck(date + 6)));
                day8.put("DayOfWeek", dayOfWeek(dateCheck(date + 7)));
                day9.put("DayOfWeek", dayOfWeek(dateCheck(date + 8)));
                day10.put("DayOfWeek", dayOfWeek(dateCheck(date + 9)));
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
            mainObj.put(day8);
            mainObj.put(day9);
            mainObj.put(day10);
        }


        return mainObj;
    }

    // Check the date
    private int dateCheck (int theDate) {
        if(theDate > 7 ) {
            theDate = theDate - 7;
        }
        return theDate;
    }

    // Set the date
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