package com.stratazima.testapp.weather;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Esau on 5/14/2014.
 */

public class WeatherParse {
    String url;
    JSONObject tempObj;
    JSONArray normalArray;
    JSONArray shortArray;

    public JSONArray getDateObject (JSONArray mainObject, boolean isConnected, String stateCity) {
        int numOfDays = mainObject.length();
        JSONObject day1 = null;
        JSONObject day2 = null;
        JSONObject day3 = null;
        JSONObject day4 = null;
        JSONObject day5 = null;
        JSONObject day6 = null;
        JSONObject day7 = null;
        JSONObject day8 = null;
        JSONObject day9 = null;
        JSONObject day10 = null;

        if (isConnected) {
            url = "http://api.wunderground.com/api/259cb66ccd82d5b8/forecast10day/q/" + stateCity;
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                            InputStream is = null;
                            String result = "";

                            // HTTP
                            try {
                                HttpClient httpclient = new DefaultHttpClient();
                                HttpGet httpGet = new HttpGet(url);
                                HttpResponse response = httpclient.execute(httpGet);
                                HttpEntity entity = response.getEntity();
                                is = entity.getContent();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            // Read response to string
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    sb.append(line + "\n");
                                }
                                is.close();
                                result = sb.toString();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            // Convert string to object
                            try {
                                tempObj = new JSONObject(result);
                            } catch(JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                        e.printStackTrace();
                    }}});

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String simpError = null;
        try {
            simpError = tempObj.getJSONObject("response").getJSONObject("error").getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (simpError != null) {
            isConnected = false;
        } else {
            try {
                tempObj = tempObj.getJSONObject("forecast");

                JSONObject temporaryObject = tempObj.getJSONObject("txt_forecast");
                normalArray = temporaryObject.getJSONArray("forecastday");

                temporaryObject = tempObj.getJSONObject("simpleforecast");
                shortArray = temporaryObject.getJSONArray("forecastday");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (numOfDays == 1) {
            try {
                day1 = mainObject.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                if (!isConnected) {
                    // Day 1
                    day1.put("todayDay", "Partly Cloudy");
                    day1.put("todayNight", "Chance of ThunderStorms");
                    day1.put("tempHigh", "91");
                    day1.put("tempLow", "78");
                } else {
                    // Day 1
                    day1.put("todayDay", normalArray.getJSONObject(0).getString("fcttext"));
                    day1.put("todayNight", normalArray.getJSONObject(1).getString("fcttext"));
                    day1.put("tempHigh", shortArray.getJSONObject(0).getJSONObject("high").getString("fahrenheit"));
                    day1.put("tempLow", shortArray.getJSONObject(0).getJSONObject("low").getString("fahrenheit"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
        if (numOfDays == 2) {
            try {
                day1 = mainObject.getJSONObject(0);
                day2 = mainObject.getJSONObject(1);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                if (!isConnected) {
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
                } else {
                    // Day 1
                    day1.put("todayDay", normalArray.getJSONObject(0).getString("fcttext"));
                    day1.put("todayNight", normalArray.getJSONObject(1).getString("fcttext"));
                    day1.put("tempHigh", shortArray.getJSONObject(0).getJSONObject("high").getString("fahrenheit"));
                    day1.put("tempLow", shortArray.getJSONObject(0).getJSONObject("low").getString("fahrenheit"));
                    // Day 2
                    day2.put("todayDay", normalArray.getJSONObject(2).getString("fcttext"));
                    day2.put("todayNight", normalArray.getJSONObject(3).getString("fcttext"));
                    day2.put("tempHigh", shortArray.getJSONObject(1).getJSONObject("high").getString("fahrenheit"));
                    day2.put("tempLow", shortArray.getJSONObject(1).getJSONObject("low").getString("fahrenheit"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
        if (numOfDays == 5) {
            try {
                day1 = mainObject.getJSONObject(0);
                day2 = mainObject.getJSONObject(1);
                day3 = mainObject.getJSONObject(2);
                day4 = mainObject.getJSONObject(3);
                day5 = mainObject.getJSONObject(4);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                if (!isConnected) {
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
                } else {
                    // Day 1
                    day1.put("todayDay", normalArray.getJSONObject(0).getString("fcttext"));
                    day1.put("todayNight", normalArray.getJSONObject(1).getString("fcttext"));
                    day1.put("tempHigh", shortArray.getJSONObject(0).getJSONObject("high").getString("fahrenheit"));
                    day1.put("tempLow", shortArray.getJSONObject(0).getJSONObject("low").getString("fahrenheit"));
                    // Day 2
                    day2.put("todayDay", normalArray.getJSONObject(2).getString("fcttext"));
                    day2.put("todayNight", normalArray.getJSONObject(3).getString("fcttext"));
                    day2.put("tempHigh", shortArray.getJSONObject(1).getJSONObject("high").getString("fahrenheit"));
                    day2.put("tempLow", shortArray.getJSONObject(1).getJSONObject("low").getString("fahrenheit"));
                    // Day 3
                    day3.put("todayDay", normalArray.getJSONObject(4).getString("fcttext"));
                    day3.put("todayNight", normalArray.getJSONObject(5).getString("fcttext"));
                    day3.put("tempHigh", shortArray.getJSONObject(2).getJSONObject("high").getString("fahrenheit"));
                    day3.put("tempLow", shortArray.getJSONObject(2).getJSONObject("low").getString("fahrenheit"));
                    // Day 4
                    day4.put("todayDay", normalArray.getJSONObject(6).getString("fcttext"));
                    day4.put("todayNight", normalArray.getJSONObject(7).getString("fcttext"));
                    day4.put("tempHigh", shortArray.getJSONObject(3).getJSONObject("high").getString("fahrenheit"));
                    day4.put("tempLow", shortArray.getJSONObject(3).getJSONObject("low").getString("fahrenheit"));
                    // Day 5
                    day5.put("todayDay", normalArray.getJSONObject(8).getString("fcttext"));
                    day5.put("todayNight", normalArray.getJSONObject(9).getString("fcttext"));
                    day5.put("tempHigh", shortArray.getJSONObject(4).getJSONObject("high").getString("fahrenheit"));
                    day5.put("tempLow", shortArray.getJSONObject(4).getJSONObject("low").getString("fahrenheit"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
        if (numOfDays == 7) {
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
                if (!isConnected) {
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
                } else {
                    // Day 1
                    day1.put("todayDay", normalArray.getJSONObject(0).getString("fcttext"));
                    day1.put("todayNight", normalArray.getJSONObject(1).getString("fcttext"));
                    day1.put("tempHigh", shortArray.getJSONObject(0).getJSONObject("high").getString("fahrenheit"));
                    day1.put("tempLow", shortArray.getJSONObject(0).getJSONObject("low").getString("fahrenheit"));
                    // Day 2
                    day2.put("todayDay", normalArray.getJSONObject(2).getString("fcttext"));
                    day2.put("todayNight", normalArray.getJSONObject(3).getString("fcttext"));
                    day2.put("tempHigh", shortArray.getJSONObject(1).getJSONObject("high").getString("fahrenheit"));
                    day2.put("tempLow", shortArray.getJSONObject(1).getJSONObject("low").getString("fahrenheit"));
                    // Day 3
                    day3.put("todayDay", normalArray.getJSONObject(4).getString("fcttext"));
                    day3.put("todayNight", normalArray.getJSONObject(5).getString("fcttext"));
                    day3.put("tempHigh", shortArray.getJSONObject(2).getJSONObject("high").getString("fahrenheit"));
                    day3.put("tempLow", shortArray.getJSONObject(2).getJSONObject("low").getString("fahrenheit"));
                    // Day 4
                    day4.put("todayDay", normalArray.getJSONObject(6).getString("fcttext"));
                    day4.put("todayNight", normalArray.getJSONObject(7).getString("fcttext"));
                    day4.put("tempHigh", shortArray.getJSONObject(3).getJSONObject("high").getString("fahrenheit"));
                    day4.put("tempLow", shortArray.getJSONObject(3).getJSONObject("low").getString("fahrenheit"));
                    // Day 5
                    day5.put("todayDay", normalArray.getJSONObject(8).getString("fcttext"));
                    day5.put("todayNight", normalArray.getJSONObject(9).getString("fcttext"));
                    day5.put("tempHigh", shortArray.getJSONObject(4).getJSONObject("high").getString("fahrenheit"));
                    day5.put("tempLow", shortArray.getJSONObject(4).getJSONObject("low").getString("fahrenheit"));
                    // Day 6
                    day6.put("todayDay", normalArray.getJSONObject(10).getString("fcttext"));
                    day6.put("todayNight", normalArray.getJSONObject(11).getString("fcttext"));
                    day6.put("tempHigh", shortArray.getJSONObject(5).getJSONObject("high").getString("fahrenheit"));
                    day6.put("tempLow", shortArray.getJSONObject(5).getJSONObject("low").getString("fahrenheit"));
                    // Day 7
                    day7.put("todayDay", normalArray.getJSONObject(12).getString("fcttext"));
                    day7.put("todayNight", normalArray.getJSONObject(13).getString("fcttext"));
                    day7.put("tempHigh", shortArray.getJSONObject(6).getJSONObject("high").getString("fahrenheit"));
                    day7.put("tempLow", shortArray.getJSONObject(6).getJSONObject("low").getString("fahrenheit"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
        if (numOfDays == 10) {
            try {
                day1 = mainObject.getJSONObject(0);
                day2 = mainObject.getJSONObject(1);
                day3 = mainObject.getJSONObject(2);
                day4 = mainObject.getJSONObject(3);
                day5 = mainObject.getJSONObject(4);
                day6 = mainObject.getJSONObject(5);
                day7 = mainObject.getJSONObject(6);
                day8 = mainObject.getJSONObject(7);
                day9 = mainObject.getJSONObject(8);
                day10 = mainObject.getJSONObject(9);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                if (!isConnected) {
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
                    // Day 8
                    day8.put("todayDay", "Mostly Cloudy");
                    day8.put("todayNight", "ThunderStorms");
                    day8.put("tempHigh", "93");
                    day8.put("tempLow", "80");
                    // Day 9
                    day9.put("todayDay", "Mostly Cloudy");
                    day9.put("todayNight", "ThunderStorms");
                    day9.put("tempHigh", "93");
                    day9.put("tempLow", "80");
                    // Day 10
                    day10.put("todayDay", "Mostly Cloudy");
                    day10.put("todayNight", "ThunderStorms");
                    day10.put("tempHigh", "93");
                    day10.put("tempLow", "80");
                } else {
                    // Day 1
                    day1.put("todayDay", normalArray.getJSONObject(0).getString("fcttext"));
                    day1.put("todayNight", normalArray.getJSONObject(1).getString("fcttext"));
                    day1.put("tempHigh", shortArray.getJSONObject(0).getJSONObject("high").getString("fahrenheit"));
                    day1.put("tempLow", shortArray.getJSONObject(0).getJSONObject("low").getString("fahrenheit"));
                    // Day 2
                    day2.put("todayDay", normalArray.getJSONObject(2).getString("fcttext"));
                    day2.put("todayNight", normalArray.getJSONObject(3).getString("fcttext"));
                    day2.put("tempHigh", shortArray.getJSONObject(1).getJSONObject("high").getString("fahrenheit"));
                    day2.put("tempLow", shortArray.getJSONObject(1).getJSONObject("low").getString("fahrenheit"));
                    // Day 3
                    day3.put("todayDay", normalArray.getJSONObject(4).getString("fcttext"));
                    day3.put("todayNight", normalArray.getJSONObject(5).getString("fcttext"));
                    day3.put("tempHigh", shortArray.getJSONObject(2).getJSONObject("high").getString("fahrenheit"));
                    day3.put("tempLow", shortArray.getJSONObject(2).getJSONObject("low").getString("fahrenheit"));
                    // Day 4
                    day4.put("todayDay", normalArray.getJSONObject(6).getString("fcttext"));
                    day4.put("todayNight", normalArray.getJSONObject(7).getString("fcttext"));
                    day4.put("tempHigh", shortArray.getJSONObject(3).getJSONObject("high").getString("fahrenheit"));
                    day4.put("tempLow", shortArray.getJSONObject(3).getJSONObject("low").getString("fahrenheit"));
                    // Day 5
                    day5.put("todayDay", normalArray.getJSONObject(8).getString("fcttext"));
                    day5.put("todayNight", normalArray.getJSONObject(9).getString("fcttext"));
                    day5.put("tempHigh", shortArray.getJSONObject(4).getJSONObject("high").getString("fahrenheit"));
                    day5.put("tempLow", shortArray.getJSONObject(4).getJSONObject("low").getString("fahrenheit"));
                    // Day 6
                    day6.put("todayDay", normalArray.getJSONObject(10).getString("fcttext"));
                    day6.put("todayNight", normalArray.getJSONObject(11).getString("fcttext"));
                    day6.put("tempHigh", shortArray.getJSONObject(5).getJSONObject("high").getString("fahrenheit"));
                    day6.put("tempLow", shortArray.getJSONObject(5).getJSONObject("low").getString("fahrenheit"));
                    // Day 7
                    day7.put("todayDay", normalArray.getJSONObject(12).getString("fcttext"));
                    day7.put("todayNight", normalArray.getJSONObject(13).getString("fcttext"));
                    day7.put("tempHigh", shortArray.getJSONObject(6).getJSONObject("high").getString("fahrenheit"));
                    day7.put("tempLow", shortArray.getJSONObject(6).getJSONObject("low").getString("fahrenheit"));
                    // Day 8
                    day8.put("todayDay", normalArray.getJSONObject(14).getString("fcttext"));
                    day8.put("todayNight", normalArray.getJSONObject(15).getString("fcttext"));
                    day8.put("tempHigh", shortArray.getJSONObject(7).getJSONObject("high").getString("fahrenheit"));
                    day8.put("tempLow", shortArray.getJSONObject(7).getJSONObject("low").getString("fahrenheit"));
                    // Day 9
                    day9.put("todayDay", normalArray.getJSONObject(16).getString("fcttext"));
                    day9.put("todayNight", normalArray.getJSONObject(17).getString("fcttext"));
                    day9.put("tempHigh", shortArray.getJSONObject(8).getJSONObject("high").getString("fahrenheit"));
                    day9.put("tempLow", shortArray.getJSONObject(8).getJSONObject("low").getString("fahrenheit"));
                    // Day 10
                    day10.put("todayDay", normalArray.getJSONObject(18).getString("fcttext"));
                    day10.put("todayNight", normalArray.getJSONObject(19).getString("fcttext"));
                    day10.put("tempHigh", shortArray.getJSONObject(9).getJSONObject("high").getString("fahrenheit"));
                    day10.put("tempLow", shortArray.getJSONObject(9).getJSONObject("low").getString("fahrenheit"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mainObject;
    }
}
