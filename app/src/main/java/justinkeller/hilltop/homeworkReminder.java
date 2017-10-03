package justinkeller.hilltop;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class homeworkReminder extends Activity {
    private String myRemTime;
    private MyCalendar[] m_calendars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_reminder);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setMyRemTime(prefs.getString("homeTime", "17:00:00"));
        Button sb = findViewById(R.id.hwsub);
        final EditText period = findViewById(R.id.period);
        final EditText hwcontent = findViewById(R.id.hwcontent);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 1);
        }
        sb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Log.w("Period: ", period.getText().toString());
                    Log.w("Content: ", hwcontent.getText().toString());
                    addReminder(period.getText().toString(), hwcontent.getText().toString());
                } catch (java.lang.SecurityException e) {
                    Log.e("hilltop.calendar", "no Calendar access");
                    AlertDialog.Builder builder = new AlertDialog.Builder(homeworkReminder.this).setTitle("Error: no calendar access").setMessage("You have not granted access for this application to access the calendar").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert);
                    builder.create().show();

                }
            }
        });


    }

    protected void addReminder(@NonNull String period, String cont) {
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        month = month - 1;
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        int hour = Integer.parseInt(getMyRemTime().split(":")[0]);
        int minute = Integer.parseInt(getMyRemTime().split(":")[1]);
        Log.w("HillTop Time year", Integer.toString(year));
        Log.w("HillTop Time month", Integer.toString(month + 1));
        Log.w("HillTop Time day", Integer.toString(day));
        Log.w("HillTop Time hour", Integer.toString(hour));
        Log.w("HillTop Time minute", Integer.toString(minute));
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month, day, hour, minute);
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, month, day, hour, minute);
        long endMillis = endTime.getTimeInMillis();
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(Events.DTSTART, startMillis);
        values.put(Events.DTEND, endMillis);
        values.put(Events.TITLE, period);
        values.put(Events.DESCRIPTION, cont);
        getCalendars();
        values.put(Events.CALENDAR_ID, getM_calendars()[0].id);
        values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
        long eventID = 0;
        try {
            Uri uri = cr.insert(Events.CONTENT_URI, values);
            if (uri != null) {
                eventID = Long.parseLong(uri.getLastPathSegment()); //in that case, fail
            }
        } catch (java.lang.SecurityException e) {
            e.printStackTrace(); //should never get here; crash app if it does
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
            Log.e("error", "null pointer");
        }

        ContentResolver cr2 = getContentResolver();
        ContentValues values2 = new ContentValues();
        values2.put(Reminders.MINUTES, 5);
        values2.put(Reminders.EVENT_ID, eventID);
        values2.put(Reminders.METHOD, Reminders.METHOD_ALERT);
        try {
            cr2.insert(Reminders.CONTENT_URI, values2);
        } catch (java.lang.SecurityException e) {
            Log.e("hill top error: ", e.getMessage());
            e.printStackTrace();
        }


    }

    @SuppressWarnings("deprecation")
    private void getCalendars() {
        String[] l_projection = new String[]{"_id",};
        Uri l_calendars;
        l_calendars = Uri.parse("content://com.android.calendar/calendars");
        Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, null, null, null);    //all calendars
        //Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, "selected=1", null, null);   //active calendars
        if (l_managedCursor.moveToFirst()) {
            setM_calendars(new MyCalendar[l_managedCursor.getCount()]);
            String l_calId;
            int l_cnt = 0;
            int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
            do {
                l_calId = l_managedCursor.getString(l_idCol);
                getM_calendars()[l_cnt] = new MyCalendar(l_calId);
                ++l_cnt;
            } while (l_managedCursor.moveToNext());
        }
    }

    public String getMyRemTime() {
        return myRemTime;
    }

    public void setMyRemTime(String myRemTime) {
        this.myRemTime = myRemTime;
    }

    public MyCalendar[] getM_calendars() {
        return m_calendars;
    }

    public void setM_calendars(MyCalendar[] m_calendars) {
        this.m_calendars = m_calendars;
    }
}

class MyCalendar {
    public String name;
    public String id;

    MyCalendar(String _id) {

        id = _id;
    }

    @Override
    public String toString() {
        return name;
    }
}

