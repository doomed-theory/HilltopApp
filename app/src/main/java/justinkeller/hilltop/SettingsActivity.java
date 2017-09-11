package justinkeller.hilltop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceManager;
import android.view.View.OnFocusChangeListener;
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
       final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currentURL.setText(prefs.getString("configURL","http://hilltopprep.org"));
        Button submit=findViewById(R.id.subbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("configURL",currentURL.getText().toString()); // value to store
                editor.commit();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String download = sharedPreferences.getString("configurl","nullstring");
                Toast.makeText(getApplicationContext(),download.toString(),Toast.LENGTH_LONG);
            }
        });


        }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("configURL")) {
            String download = sharedPreferences.getString(key,"nullstring");
            Toast.makeText(this,download,Toast.LENGTH_LONG);
        }
    }
    public void onClick(View v) {
        if (true) {

        }
    }
    }
