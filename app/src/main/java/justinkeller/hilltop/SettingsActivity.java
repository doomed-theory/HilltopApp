package justinkeller.hilltop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.Map;
import java.util.Set;

public class SettingsActivity extends Activity implements OnSharedPreferenceChangeListener   {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        final EditText currentURL=findViewById(R.id.configurl);
        final EditText homeTime=findViewById(R.id.timeinput);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currentURL.setText(prefs.getString("configURL","http://hilltopprep.org"));
        homeTime.setText(prefs.getString("homeTime","17:00:00"));
        Button submitTime=findViewById(R.id.submittime);
        Button submit=findViewById(R.id.subbutton);
   /*     submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("configURL",currentURL.getText().toString()); // value to store
                editor.commit();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String download = sharedPreferences.getString("configurl","nullstring");
                Toast.makeText(getApplicationContext(),download.toString(),Toast.LENGTH_LONG);
            }
        }); */
        submitTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("homeTime",homeTime.getText().toString()); // value to store
                editor.commit();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String download = sharedPreferences.getString("homeTime","nullstring");
                Toast.makeText(getApplicationContext(),download,Toast.LENGTH_LONG).show();
            }
        });


    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("homeTime")) {
            String homeTime = sharedPreferences.getString(key,"nullstring");
            Toast.makeText(this,homeTime,Toast.LENGTH_LONG).show();
        }
    }
    public void onClick(View v) {
        if (true) {

        }
    }
}