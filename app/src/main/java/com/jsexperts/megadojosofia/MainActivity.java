package com.jsexperts.megadojosofia;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements MainFragment.OnLearnButtonPressed {
    private static final String TAG = MainActivity.class.getName();

    private MainFragment mainFragment;
    private WebviewFragment webviewFragment;

    private boolean goBack = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                             WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mainFragment = new MainFragment();
        webviewFragment = new WebviewFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mainFragment, "MAIN")
                //.add(R.id.fragment_container, webviewFragment, "WEBVIEW")
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .show(mainFragment)
                .commit();

    //    hideFloatBar();
    }

    @Override
    public void onResume(){
        super.onResume();
        hideFloatBar();
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    void hideFloatBar() {
        if (!isEmulator()) {
            // Disable Sanbot floatbar
            Context ctx = getApplicationContext();
            ContentValues contentValues = new ContentValues();
            contentValues.put("className", MainActivity.class.getName());
            contentValues.put("isHide", 1);
            ctx.getContentResolver().update(
                    Uri.parse("content://com.qihancloud.floatbar/t_setting"),
                    contentValues, "key=?", new String[]{"hide_floatbar"}
                    );
        }
    }


    @Override
    public void onBackPressed() {
        // Disable back button
    }


    public void exitApp(View view) {
        if (goBack) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .hide(webviewFragment)
                    .show(mainFragment)
                    .commit();
            goBack = false;
            webviewFragment = new WebviewFragment();
            ImageButton imageButton = (ImageButton) view;
            imageButton.setImageResource(R.drawable.exit);

        }else {
            ExitFragment f = new ExitFragment();
            f.show(getFragmentManager(), "Exit");
        }
    }

    @Override
    public void onButtonPressed() {
        // We are starting the web browser fragment
        // need to change the button for the exit to arrow

        ImageButton ib = (ImageButton) findViewById(R.id.closeButton);
        ib.setImageResource(R.drawable.back);
        goBack = true;

        if(!webviewFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.fragment_container, webviewFragment, "WEBVIEW")
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .hide(mainFragment)
                    .show(webviewFragment)
                    .commit();
        }
    }
}
