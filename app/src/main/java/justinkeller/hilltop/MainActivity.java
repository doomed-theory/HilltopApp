package justinkeller.hilltop;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        final Button portalact = (Button) findViewById(R.id.portol);
        portalact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent portalint = new Intent(getApplicationContext(), PortalAct.class);
                startActivity(portalint);

            }
        });

        final Button settingsact = (Button) findViewById(R.id.settings);
        settingsact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent settingint = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingint);

            }
        });
        final Button schedact=(Button)findViewById(R.id.schedule);
        schedact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent schedint=new Intent(getApplicationContext(),periodTimes.class);
                startActivity(schedint);
            }

        });

        final TextView cp=(TextView)findViewById(R.id.currper);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        String formattedDate = df.format(Calendar.getInstance().getTime());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
