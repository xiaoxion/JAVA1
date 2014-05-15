package com.stratazima.testapp.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.stratazima.testapp.home.R;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Esau on 5/15/2014.
 */
public class WeatherDetail extends Activity {

    private JSONObject dayObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try {
            dayObj = new JSONObject(getIntent().getStringExtra("mainObject"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        // Set date
        TextView date = (TextView) findViewById(R.id.textViewDate);
        date.setText(dateS);

        TextView high = (TextView) findViewById(R.id.textViewHigh);
        high.setText(highS);

        TextView low = (TextView) findViewById(R.id.textViewLow);
        low.setText(lowS);

        TextView day = (TextView) findViewById(R.id.textViewDay);
        day.setText(dayS);

        TextView night = (TextView) findViewById(R.id.textViewNight);
        night.setText(nightS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
