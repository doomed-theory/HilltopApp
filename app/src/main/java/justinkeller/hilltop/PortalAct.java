package justinkeller.hilltop;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import android.webkit.WebViewClient;
import java.util.HashMap;
public class PortalAct extends Activity {
    WebView wb;
    Activity activity=this.activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
         wb= findViewById(R.id.webview);
         wb.getSettings().setJavaScriptEnabled(true);
         wb.getSettings().setBuiltInZoomControls(true);
         wb.getSettings().setDisplayZoomControls(false);
         wb.setWebViewClient(new WebViewClient() {
             public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                 Toast.makeText(activity, "Webpage error" + description, Toast.LENGTH_SHORT).show();
             }
         });
       HashMap<String, String> h=new HashMap<String, String>();
        h.put("useragent","Hill Top Android App");
        wb.loadUrl("https://webapps.pcrsoft.com/clue/Student-Portal-Login/11552?returnUrl=https%3a%2f%2fwebapps.pcrsoft.com%2fclue%2fStudent-Portal-Home%2f11553", h);
        h=null;


    }

    @Override
    public void onBackPressed() {
        wb.clearHistory();
        wb.destroy();
        super.onBackPressed();
    }
}
