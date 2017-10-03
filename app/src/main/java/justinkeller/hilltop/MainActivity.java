package justinkeller.hilltop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String p1b = "08:30:00";
        String p1e = "09:10:00";
        String p2b = "09:10:00";
        String p2e = "09:50:00";
        String pcb = "09:50:00";
        String pce = "10:10:00";
        String p3b = "10:10:00";
        String p3e = "10:50:00";
        String p4b = "10:50:00";
        String p4e = "11:30:00";
        String pmb = "11:30:00";
        String pme = "12:40:00";
        String p5b = "12:40:00";
        String p5e = "13:20:00";
        String p6b = "13:20:00";
        String p6e = "14:00:00";
        String p7b = "14:00:00";
        String p7e = "14:40:00";
        String p8b = "14:40:00";
        String p8e = "15:20:00";
        TextView cp = findViewById(R.id.currper);
        try {
            Date d1b = new SimpleDateFormat("HH:mm:ss").parse(p1b);
            Date d1e = new SimpleDateFormat("HH:mm:ss").parse(p1e);
            Date d2b = new SimpleDateFormat("HH:mm:ss").parse(p2b);
            Date d2e = new SimpleDateFormat("HH:mm:ss").parse(p2e);
            Date dcb = new SimpleDateFormat("HH:mm:ss").parse(pcb);
            Date dce = new SimpleDateFormat("HH:mm:ss").parse(pce);
            Date d3b = new SimpleDateFormat("HH:mm:ss").parse(p3b);
            Date d3e = new SimpleDateFormat("HH:mm:ss").parse(p3e);
            Date d4b = new SimpleDateFormat("HH:mm:ss").parse(p4b);
            Date d4e = new SimpleDateFormat("HH:mm:ss").parse(p4e);
            Date dmb = new SimpleDateFormat("HH:mm:ss").parse(pmb);
            Date dme = new SimpleDateFormat("HH:mm:ss").parse(pme);
            Date d5b = new SimpleDateFormat("HH:mm:ss").parse(p5b);
            Date d5e = new SimpleDateFormat("HH:mm:ss").parse(p5e);
            Date d6b = new SimpleDateFormat("HH:mm:ss").parse(p6b);
            Date d6e = new SimpleDateFormat("HH:mm:ss").parse(p6e);
            Date d7b = new SimpleDateFormat("HH:mm:ss").parse(p7b);
            Date d7e = new SimpleDateFormat("HH:mm:ss").parse(p7e);
            Date d8b = new SimpleDateFormat("HH:mm:ss").parse(p8b);
            Date d8e = new SimpleDateFormat("HH:mm:ss").parse(p8e);
            Calendar c1b = Calendar.getInstance();
            Calendar c1e = Calendar.getInstance();
            Calendar c2b = Calendar.getInstance();
            Calendar c2e = Calendar.getInstance();
            Calendar ccb = Calendar.getInstance();
            Calendar cce = Calendar.getInstance();
            Calendar c3b = Calendar.getInstance();
            Calendar c3e = Calendar.getInstance();
            Calendar c4b = Calendar.getInstance();
            Calendar c4e = Calendar.getInstance();
            Calendar cmb = Calendar.getInstance();
            Calendar cme = Calendar.getInstance();
            Calendar c5b = Calendar.getInstance();
            Calendar c5e = Calendar.getInstance();
            Calendar c6b = Calendar.getInstance();
            Calendar c6e = Calendar.getInstance();
            Calendar c7b = Calendar.getInstance();
            Calendar c7e = Calendar.getInstance();
            Calendar c8b = Calendar.getInstance();
            Calendar c8e = Calendar.getInstance();
            c1b.setTime(d1b);
            c1e.setTime(d1e);
            c2b.setTime(d2b);
            c2e.setTime(d2e);
            ccb.setTime(dcb);
            cce.setTime(dce);
            c3b.setTime(d3b);
            c3e.setTime(d3e);
            c4b.setTime(d4b);
            c4e.setTime(d4e);
            cmb.setTime(dmb);
            cme.setTime(dme);
            c5b.setTime(d5b);
            c5e.setTime(d5e);
            c6b.setTime(d6b);
            c6e.setTime(d6e);
            c7b.setTime(d7b);
            c7e.setTime(d7e);
            c8b.setTime(d8b);
            c8e.setTime(d8e);
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
            String form = fmt.format(new Date());
            Date currdate = new SimpleDateFormat("HH:mm:ss").parse(form);
            Calendar setCal = Calendar.getInstance();
            setCal.setTime(currdate);
            Date x = setCal.getTime();
            Log.i("CURRTIME", form);
            Log.i("begin time area", "what time is it");
            //TODO: UPDATE TIME ON RESUME
            if (x.after(c1b.getTime()) && x.before(c1e.getTime())) {
                cp.setText("First Period");
            } else if (x.after(c2b.getTime()) && x.before(c2e.getTime())) {
                cp.setText("Second Period");
                Log.i("TIME", "second period ");
            } else if (x.after(ccb.getTime()) && x.before(cce.getTime())) {
                cp.setText("Community Meeting");
            } else if (x.after(c3b.getTime()) && x.before(c3e.getTime())) {
                cp.setText("Third Period");
            } else if (x.after(c4b.getTime()) && x.before(c4e.getTime())) {
                cp.setText("Fourth Period");
            } else if (x.after(cmb.getTime()) && x.before(cme.getTime())) {
                cp.setText("Mentor Period");
            } else if (x.after(c5b.getTime()) && x.before(c5e.getTime())) {
                cp.setText("Fifth Period");
            } else if (x.after(c6b.getTime()) && x.before(c6e.getTime())) {
                cp.setText("Sixth Period");
            } else if (x.after(c7b.getTime()) && x.before(c7e.getTime())) {
                cp.setText("Seventh Period");
            } else if (x.after(c8b.getTime()) && x.before(c8e.getTime())) {
                cp.setText("Eighth Period");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Example of a call to a native method
        final Button portalact = findViewById(R.id.portol);
        portalact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent portalint = new Intent(getApplicationContext(), PortalAct.class);
                startActivity(portalint);

            }
        });
        final Button htinsta = findViewById(R.id.HTInsta);
        htinsta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/hilltopprepschool");
                Intent ig = new Intent(Intent.ACTION_VIEW, uri);
                ig.setPackage("com.instagram.android");
                try {
                    startActivity(ig);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/hilltopprepschool")));
                }
            }
        });
        final Button calbutton = findViewById(R.id.Calendar);
        calbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://hilltopprep.org/calendar")));
            }
        });


        final Button settingsact = findViewById(R.id.settings);
        settingsact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent settingint = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingint);

            }
        });
        final Button schedact = findViewById(R.id.schedule);
        schedact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent schedint = new Intent(getApplicationContext(), periodTimes.class);
                startActivity(schedint);
            }

        });
        final Button hwbutton = findViewById(R.id.hwremind);
        hwbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hwintent = new Intent(getApplicationContext(), homeworkReminder.class);
                startActivity(hwintent);
            }

        });


        final Button websiteintent = findViewById(R.id.websiteintent);
        websiteintent.setText("visit website");
        websiteintent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://hilltopprep.org"));
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.gc();
    }
}
