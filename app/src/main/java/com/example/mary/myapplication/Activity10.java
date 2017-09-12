package com.example.mary.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Mary on 22.03.2017.
 */

public class Activity10 extends Activity {

    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_10);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar today = Calendar.getInstance();
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        String date = year + "/" + (monthOfYear+1) + "/" +dayOfMonth;
                        Toast.makeText(getApplicationContext(), date , Toast.LENGTH_SHORT).show();
//                       mInfoTextView.setText("Год: " + year + "\n" + "Месяц: "
//                               + (monthOfYear + 1) + "\n" + "День: " + dayOfMonth);
                        Intent intent = new Intent(Activity10.this, Activity9.class);
                        intent.putExtra("date", date);
                          startActivity(intent);
                    }
                });
    }
}
