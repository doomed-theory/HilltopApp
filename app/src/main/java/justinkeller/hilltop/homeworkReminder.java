package justinkeller.hilltop;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class homeworkReminder extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_reminder);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //final String myreminderTime=prefs.getString("homeTime", "17:00:00");  //error???

        /* to do: add assignment textbox, add assignment textbox input to calendar along with homeTime */
    }

}
