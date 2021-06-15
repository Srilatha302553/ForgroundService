package com.example.forgroundservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    int count = 0;

    private MyIntentService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;

    /*Handler handler;*/


    private Intent serviceIntent;

    private boolean mStopLoop;

    @BindView(R.id.startButton)
    TextView startButton;
    @BindView(R.id.stopButton)
    TextView stopButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Foreground Service");
        setSupportActionBar(toolbar);
        Log.i(getString(R.string.service_demo_tag), "MainActivity thread id: " + Thread.currentThread().getId());

        serviceIntent = new Intent(getApplicationContext(), MyIntentService.class);

        intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

    }

    // start Service
    @OnClick(R.id.startButton)
    public void setStartButton() {
        mStopLoop = true;
        ContextCompat.startForegroundService(this, serviceIntent);
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

    }

    // stop Service
    @OnClick(R.id.stopButton)
    public void setstopButton() {

            stopService(serviceIntent);
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();

    }



}
