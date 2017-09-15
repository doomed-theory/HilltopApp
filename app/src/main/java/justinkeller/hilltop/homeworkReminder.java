package justinkeller.hilltop;
import android.app.Activity;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import java.util.Calendar;
import android.content.ContentValues;
import android.provider.CalendarContract.Events;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.*;
import java.util.Date;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.content.pm.*;
import android.util.Log;

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
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CALENDAR)) {
                Log.d("true",   "granted");
            } else {}
        }
        sb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean bool=ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR)!=0;
                if(bool) {
               addReminder(period.getText().toString(),hwcontent.getText().toString()); }
            }
        });



        /* to do: add assignment textbox, add assignment textbox input to calendar along with homeTime */
    }
    protected void addReminder(String period, String cont) {
        int year= Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        int hour=Integer.parseInt(myRemTime.split(":")[0]);
        int minute=Integer.parseInt(myRemTime.split(":")[1]);
        Calendar begin=Calendar.getInstance();
        begin.set(year,month,day,hour,minute);
        Calendar end=Calendar.getInstance();
        end.set(year,month,day,hour,minute+30);
        long smillis=begin.getTimeInMillis();
        long emillis=end.getTimeInMillis();
        ContentValues event=new ContentValues();
        event.put(Events.CALENDAR_ID,1);
        event.put(Events.TITLE,period);
        String evtdesc="Homework for ".concat(period).concat(" ").concat("do: ").concat(cont);
        event.put(Events.DESCRIPTION,evtdesc);
        event.put(Events.DTSTART,smillis);
        event.put(Events.DTEND,emillis);
        event.put("eventStatus",1);
        event.put("eventVisibility",3);
        event.put("transparency",0);
        event.put(Events.HAS_ALARM,1);
Uri uri=getContentResolver().insert(Uri.parse("content://com.android.calendar/events"),event);
        long id=Long.parseLong(uri.getLastPathSegment());
        ContentValues newval=new ContentValues();
        newval.put("event_id",id);
        newval.put("minutes",1);
        newval.put("method",1);
        Uri ruri=getContentResolver().insert(Uri.parse("content://com.android.calendar/reminders"),newval);


    }

}
