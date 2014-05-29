package com.stratazima.testapp.home;
/**
 * Created by Esau on 5/14/2014.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.stratazima.testapp.weather.WeatherParse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {
    public JSONArray mainObj;
    List<String> themList;
    String Tag = "Main Activity";
    String location;
    boolean isConnected;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.daSpinner);

        // Check Data Connection
        isConnected = isNetworkOnline();
        if (!isConnected) {
            onNoNetworkDialog("Need Network Connection/Error Reading Network");
        }

        onGetLocation();
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                status= true;
            }
        }

        return status;
    }

    public void onNoNetworkDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(Tag, "Accepted");
                        finish();
                    }
                })
                .setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(Tag, "Continued");
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alertBuilder =  builder.create();
        alertBuilder.show();
    }

    public void onWeatherPopUp(JSONObject dayObj) {

        String dateS = null;
        String highS = null;
        String lowS = null;
        String dayS = null;
        String nightS = null;

        try {
            dateS = dayObj.getString("DayOfWeek");
            highS = dayObj.getString("tempHigh");
            lowS = dayObj.getString("tempLow");
            dayS = dayObj.getString("todayDay");
            nightS = dayObj.getString("todayNight");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Dialog alertBuilder = new Dialog(this);
        alertBuilder.setTitle(dateS);
        alertBuilder.setContentView(R.layout.item_dialog);

        // Set dialog data
        TextView high = (TextView) alertBuilder.findViewById(R.id.textViewHigh);
        TextView low = (TextView) alertBuilder.findViewById(R.id.textViewLow);
        TextView day = (TextView) alertBuilder.findViewById(R.id.textViewDay);
        TextView night = (TextView) alertBuilder.findViewById(R.id.textViewNight);

        high.setText(highS);
        low.setText(lowS);
        day.setText(dayS);
        night.setText(nightS);

        Button acceptButton = (Button) alertBuilder.findViewById(R.id.buttonOK);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertBuilder.dismiss();
            }
        });

        try {
            alertBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCreateList() {
        // List Adapter
        ArrayList < String > tempArray = new ArrayList<String>();
        for(int i = 0; i < mainObj.length(); i++)
            try {
                JSONObject tempObject = mainObj.getJSONObject(i);
                String tempString = tempObject.getString("DayOfWeek");
                tempArray.add(tempString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        String[] superTempArray = new String[tempArray.size()];
        superTempArray = tempArray.toArray(superTempArray);
        themList = Arrays.asList(superTempArray);

        // Create List Adapter
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, themList);
        ListView daListView = (ListView) findViewById(R.id.list);
        daListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject tempObj = null;
                try {
                    tempObj = mainObj.getJSONObject(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onWeatherPopUp(tempObj);
            }
        });
        daListView.setAdapter(listAdapter);
    }

    private void onCreateSpinner () {
        String[] listItems = getResources().getStringArray(R.array.spinner_array);

        // Spinner Adapter
        ArrayAdapter<String> arraySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listItems);
        arraySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner daSpinner = (Spinner) findViewById(R.id.spinner);
        daSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    mainObj = DateCreate.TODAY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);
                } else if (position == 1) {
                    mainObj = DateCreate.TWO_DAY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);
                } else if (position == 2) {
                    mainObj = DateCreate.FIVE_DAY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);
                } else if (position == 3) {
                    mainObj = DateCreate.WEEKLY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);
                } else if (position == 4) {
                    mainObj = DateCreate.TEN_DAY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);
                }
                onCreateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        daSpinner.setAdapter(arraySpinnerAdapter);
    }

    public void onGetLocation () {

        Button button = (Button) findViewById(R.id.go);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                final EditText temporaryCity = (EditText) findViewById(R.id.cityText);
                final EditText temporaryState = (EditText) findViewById(R.id.stateText);
                String tempCity = temporaryCity.getText().toString();
                String tempState = temporaryState.getText().toString();

                if (tempCity.equals("")) {
                    temporaryCity.setError("Enter City");
                    temporaryCity.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            temporaryCity.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                }

                if (tempState.equals("")) {
                    temporaryState.setError("Enter State");
                    temporaryState.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            temporaryState.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                }
                if (!tempCity.equals("") && !tempState.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);

                    tempCity = tempCity.trim();
                    tempCity = tempCity.replace(" ", "_");

                    location = tempState + "/" + tempCity + ".json";

                    // Instantiate data
                    mainObj = DateCreate.TODAY.getDateObject();
                    WeatherParse letsParse = new WeatherParse();
                    mainObj = letsParse.getDateObject(mainObj, isConnected, location);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            progressBar.setVisibility(View.INVISIBLE);
                            onCreateSpinner();
                            onCreateList();
                        }
                    }, 2000);
                }
                }
            });
    }
}
