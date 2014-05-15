package com.stratazima.testapp.home;
/**
 * Created by Esau on 5/14/2014.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.stratazima.testapp.weather.DateCreate;
import com.stratazima.testapp.weather.WeatherDetail;
import com.stratazima.testapp.weather.WeatherParse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ListActivity {
    public JSONArray mainObj;
    List<String> themList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate data
        mainObj = DateCreate.Week.getDateObject();
        WeatherParse letsParse = new WeatherParse();
        mainObj = letsParse.getDateObject(mainObj);

        ArrayList<String> tempArray = new ArrayList<String>();
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
        setListAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Get data for object
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(MainActivity.this, WeatherDetail.class);

        JSONObject tempObj = null;
        try {
            tempObj = mainObj.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.putExtra("mainObject", tempObj.toString());
        MainActivity.this.startActivity(intent);
    }
}
