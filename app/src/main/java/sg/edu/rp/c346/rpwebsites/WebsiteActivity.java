package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebsiteActivity extends AppCompatActivity {

    WebView wvWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        wvWeb = findViewById(R.id.webView);
        wvWeb.setWebViewClient(new WebViewClient());
        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        wvWeb.getSettings().setBuiltInZoomControls(true);
        wvWeb.getSettings().setDisplayZoomControls(false);
        wvWeb.loadUrl(url);
    }
}
