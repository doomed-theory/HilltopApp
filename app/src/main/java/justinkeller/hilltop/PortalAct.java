package justinkeller.hilltop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class PortalAct extends Activity {
    WebView wb;

    //  Activity activity=this.activity; //this works. Not quite sure why
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        wb = findViewById(R.id.webview);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setDisplayZoomControls(false);
        wb.setWebViewClient(new WebViewClient() {
             /* public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                 Toast.makeText(getApplicationContext(), "Webpage error" + description, Toast.LENGTH_SHORT).show();
             } */
        });
        HashMap<String, String> h = new HashMap<>();
        h.put("useragent", "Hill Top Android App");
        wb.loadUrl(getString(R.string.portal), h);


    }

    @Override
    public void onBackPressed() {
        wb.clearHistory();
        wb.destroy();
        super.onBackPressed();
    }
}
