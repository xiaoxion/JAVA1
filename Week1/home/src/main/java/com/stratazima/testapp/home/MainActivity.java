package com.stratazima.testapp.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.*;


public class MainActivity extends Activity {

    // Variable Declarations
    String daString;
    ArrayList<String> daStringArray = new ArrayList<String>();
    TextView name;
    TextView age;
    TextView desc1;
    TextView desc2;
    TextView desc3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.go_button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                name = (TextView) findViewById(R.id.name_textView);
                age = (TextView) findViewById(R.id.age_textView);
                desc1 = (TextView) findViewById(R.id.descript_text1);
                desc2 = (TextView) findViewById(R.id.descript_text2);
                desc3 = (TextView) findViewById(R.id.descript_text3);

                if (name.getText().toString().equals("")) {
                    name.setError("Enter Name");
                }
                if (age.getText().toString().equals("")) {
                    age.setError("Enter Age");
                }
                if (desc1.getText().toString().equals("")) {
                    desc1.setError("Enter Description");
                }
                if (desc2.getText().toString().equals("")) {
                    desc2.setError("Enter Description");
                }
                if (desc3.getText().toString().equals("")) {
                    desc3.setError("Enter Description");
                }

                if (!name.getText().toString().equals("") && !age.getText().toString().equals("") && !desc1.getText().toString().equals("") && !desc2.getText().toString().equals("") && !desc3.getText().toString().equals("")) {
                    daStringArray.add(desc1.getText().toString());
                    daStringArray.add(desc2.getText().toString());
                    daStringArray.add(desc3.getText().toString());
                    compileTextView();
                }
            }
        });
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

    public void compileTextView() {
        Switch gender = (Switch) findViewById(R.id.gender_switch);

        if (gender.isChecked()) {
            daString = "Hey, my name is " + name.getText().toString() + " and I am " + age.getText().toString() + " years old. I am a " + desc1.getText().toString() + ", " + desc2.getText().toString() + ", and " + desc3.getText().toString() + " woman who would like your company." ;
        } else {
            daString = "Hello, my name is " + name.getText().toString() + " and I am " + age.getText().toString() + " years old. I am a " + desc1.getText().toString() + ", " + desc2.getText().toString() + ", and " + desc3.getText().toString() + " man who thinks you are amazing." ;
        }

        TextView finalText = (TextView) findViewById(R.id.final_textView);
        finalText.setText(daString);
    };
}
