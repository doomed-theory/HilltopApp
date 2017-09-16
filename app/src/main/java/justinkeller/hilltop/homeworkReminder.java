package justinkeller.hilltop;
import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import java.util.Calendar;
import android.content.ContentValues;
import android.provider.CalendarContract.Events;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.*;
import java.util.Date;
import java.util.TimeZone;
import android.support.annotation.NonNull;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.content.pm.*;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.CalendarContract.*;
import android.provider.CalendarContract;
import android.accounts.*;
import android.content.ContentResolver;
import android.content.Intent;
public class homeworkReminder extends Activity {
String myRemTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_reminder);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        myRemTime=prefs.getString("homeTime","17:00:00");
        Button sb=findViewById(R.id.hwsub);
        final EditText period=findViewById(R.id.period);
        final EditText hwcontent=findViewById(R.id.hwcontent);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 1);
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 1);

        }
        sb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    try {
                        Log.w("Period: ",period.getText().toString());
                        Log.w("Content: ",hwcontent.getText().toString());
               addReminder(period.getText().toString(),hwcontent.getText().toString());}
                catch (java.lang.SecurityException e) {
                        Log.e("hilltop.calendar","no Calendar access");
                    AlertDialog.Builder builder = new AlertDialog.Builder(homeworkReminder.this).setTitle("Error: no calendar access").setMessage("You have not granted access for this application to access the calendar").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}}).setIcon(android.R.drawable.ic_dialog_alert);
                    builder.create().show();

                    }
            }
        });




    }
    protected void addReminder(@NonNull String period, String cont) {
        long calID = 1;
        int year= Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        int hour=Integer.parseInt(myRemTime.split(":")[0]);
        int minute=Integer.parseInt(myRemTime.split(":")[1]);
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
        values.put(Events.CALENDAR_ID, calID);
        values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
        long eventID=0;
        try {
            Uri uri = cr.insert(Events.CONTENT_URI, values);
            eventID = Long.parseLong(uri.getLastPathSegment());
        }
        catch (java.lang.SecurityException e) {
            e.printStackTrace(); //should never get here; crash app if it does
        }

        ContentResolver cr2 = getContentResolver();
        ContentValues values2 = new ContentValues();
        values2.put(Reminders.MINUTES, 5);
        values2.put(Reminders.EVENT_ID, eventID);
        values2.put(Reminders.METHOD, Reminders.METHOD_ALERT);
        try {
            Uri uri = cr2.insert(Reminders.CONTENT_URI, values2);
        }
        catch (java.lang.SecurityException e) {
            Log.e("hill top error: ",e.getMessage());
            e.printStackTrace();
        }


    }

}
