package com.newware.tdpserilingampally;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        webView = findViewById(R.id.wvWeb);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.tdpserilingampally.com");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            final View view = layoutInflater.inflate(R.layout.sample, null);
            builder.setView(view);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            }).setCancelable(false).setMessage(R.string.exit);
            AlertDialog dialog = builder.show();
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.alert);
            TextView messageView = dialog.findViewById(android.R.id.message);
            messageView.setGravity(Gravity.CENTER);
        }
    }


    public void exit(View view)
    {
        MainActivity.super.onBackPressed();
    }
}
