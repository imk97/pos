package com.aieitconsultant.pointofsalesmobile.mainActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aieitconsultant.pointofsalesmobile.R;
import com.aieitconsultant.pointofsalesmobile.global.webappinterface.WebAppInterface;
import com.aieitconsultant.pointofsalesmobile.global.webviewclient.MyWebViewClient;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    WebView myWebView;
    private MainActivityFunc mainActivityFuncs = new MainActivityFunc();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        storeCredentials(this);
        swipeDownRefreshLayout();

        try {
            initRemoteWeb();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        vibration();
//        getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.CONFIRM);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check whether the key event is the Back button and if there's history.
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it isn't the Back button or there's no web page history, bubble up to
        // the default system behavior. Probably exit the activity.
        return super.onKeyDown(keyCode, event);
    }

    private void initRemoteWeb() throws FileNotFoundException {
        myWebView = (WebView) findViewById(R.id.webview);
        //    https://pijau.xyz/nizar/login/login.php?hash=ugsdasgFUhgfUGhgyfSDgfxgfDgfgffhFhXGDdgfdJkhfcghxfjHGGFXDXfsdgFH
        String path = "/nizar/login/login.php?hash=" + mainActivityFuncs.getFile(this);
        myWebView.loadUrl(mainActivityFuncs.ownURL() + path);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.setWebViewClient(mainActivityFuncs.shouldOverrideURL());
    }

    private void storeCredentials(Context context) {
        mainActivityFuncs.storeFile(context);
    }

    // Swipe Down for Refresh layout
    private void swipeDownRefreshLayout() {
        // Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when
        // the user performs a swipe-to-refresh gesture.
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i("OnRefresh", "onRefresh called from SwipeRefreshLayout");

            // This method performs the actual data-refresh operation and calls
            // setRefreshing(false) when it finishes.
            // myUpdateOperation();
            myWebView.loadUrl(myWebView.getUrl());
            swipeRefreshLayout.setRefreshing(false);

        });

    }

    private void vibration() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                        VibrationEffect.createOneShot(
                                50, // milliseconds
                                VibrationEffect.DEFAULT_AMPLITUDE
                        )
                );
            }
        }
    }


}