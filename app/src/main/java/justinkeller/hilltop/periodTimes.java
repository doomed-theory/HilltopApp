package justinkeller.hilltop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;


public class periodTimes extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.period_activity);
        RelativeLayout monday=(RelativeLayout)findViewById(R.id.mondayschedule);
    }
}
