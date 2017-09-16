package justinkeller.hilltop;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.util.Log;


public class periodTimes extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.period_activity);
        Log.i("period","in period times");
    }
}
