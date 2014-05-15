package com.stratazima.testapp.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Esau on 5/14/2014.
 */

public class WeatherParse {
    public JSONArray getDateObject (JSONArray mainObject) {
        JSONObject day1 = null;
        JSONObject day2 = null;
        JSONObject day3 = null;
        JSONObject day4 = null;
        JSONObject day5 = null;
        JSONObject day6 = null;
        JSONObject day7 = null;
        try {
            day1 = mainObject.getJSONObject(0);
            day2 = mainObject.getJSONObject(1);
            day3 = mainObject.getJSONObject(2);
            day4 = mainObject.getJSONObject(3);
            day5 = mainObject.getJSONObject(4);
            day6 = mainObject.getJSONObject(5);
            day7 = mainObject.getJSONObject(6);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            // Day 1
            day1.put("todayDay", "Partly Cloudy");
            day1.put("todayNight", "Chance of ThunderStorms");
            day1.put("tempHigh", "91");
            day1.put("tempLow", "78");
            // Day 2
            day2.put("todayDay", "Sunny");
            day2.put("todayNight", "Sunny");
            day2.put("tempHigh", "89");
            day2.put("tempLow", "77");
            // Day 3
            day3.put("todayDay", "Partly Cloudy");
            day3.put("todayNight", "Sunny");
            day3.put("tempHigh", "92");
            day3.put("tempLow", "75");
            // Day 4
            day4.put("todayDay", "ThunderStorms");
            day4.put("todayNight", "Chance of ThunderStorms");
            day4.put("tempHigh", "87");
            day4.put("tempLow", "74");
            // Day 5
            day5.put("todayDay", "Mostly Cloudy");
            day5.put("todayNight", "Chance of ThunderStorms");
            day5.put("tempHigh", "85");
            day5.put("tempLow", "74");
            // Day 6
            day6.put("todayDay", "Sunny");
            day6.put("todayNight", "ThunderStorms");
            day6.put("tempHigh", "90");
            day6.put("tempLow", "72");
            // Day 7
            day7.put("todayDay", "Mostly Cloudy");
            day7.put("todayNight", "ThunderStorms");
            day7.put("tempHigh", "93");
            day7.put("tempLow", "80");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mainObject;
    }
}
