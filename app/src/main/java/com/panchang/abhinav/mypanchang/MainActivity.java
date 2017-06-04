package com.panchang.abhinav.mypanchang;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnDatePicker, btnTimePicker,btnPanchang;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Panchang panchang;

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            mYear = year;
                            mMonth = monthOfYear + 1;
                            mDay = dayOfMonth;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnPanchang) {
            double hour = mHour + (double)mMinute/60;
            TimeZone mTimeZone = TimeZone.getDefault();
            int mGMTOffset = mTimeZone.getRawOffset();
            double zHour = (TimeUnit.MINUTES.convert(mGMTOffset, TimeUnit.MILLISECONDS))/60;
            Panchanga  panchanga = panchang.calculatePanchanga(mDay,mMonth,mYear,hour,zHour);
            TextView textView =(TextView) findViewById(R.id.address1);
            textView.setText(panchanga.toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        panchang = new Panchang();
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnPanchang =(Button)findViewById(R.id.btn_find_panchang);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnPanchang.setOnClickListener(this);
   }
}
