package com.example.krector.wifip2p;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Haptic extends AppCompatActivity implements OnClickListener{

    private String hostaddress;
    private Boolean curved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        hostaddress = myIntent.getStringExtra("hostaddress");
        curved = myIntent.getBooleanExtra("curved",true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_haptic);

        Button left90Button = (Button) findViewById(R.id.haptic_left_90);
        Button left45Button = (Button) findViewById(R.id.haptic_left_45);
        Button connectButton = (Button) findViewById(R.id.haptic_connect);
        Button right45Button = (Button) findViewById(R.id.haptic_right_45);
        Button right90Button = (Button) findViewById(R.id.haptic_right_90);
        Button stopButton = (Button) findViewById(R.id.hapticstop);
        Button hapticstartbutton = (Button) findViewById(R.id.hapticstart);

        left90Button.setOnClickListener(this);
        left45Button.setOnClickListener(this);
        connectButton.setOnClickListener(this);
        right45Button.setOnClickListener(this);
        right90Button.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        hapticstartbutton.setOnClickListener(this);

    }

    public void sendTextMessage(String message) {
        Intent serviceIntent = new Intent(this, TextTransferService.class);
        serviceIntent.setAction(TextTransferService.ACTION_SEND_TEXT);
        serviceIntent.putExtra("message", message);
        serviceIntent.putExtra(TextTransferService.EXTRAS_GROUP_OWNER_ADDRESS,
                hostaddress);
        serviceIntent.putExtra(TextTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
        this.startService(serviceIntent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.haptic_left_90:
                if(curved) {
                    sendTextMessage("haptic_left_90");
                }else{
                    sendTextMessage("haptic_left");
                }
                break;

            case R.id.haptic_left_45:
                if(curved) {
                    sendTextMessage("haptic_left_45");
                }else{
                    sendTextMessage("haptic_left");
                }
                break;

            case R.id.haptic_connect:
                sendTextMessage("ConnectHaptic");
                break;

            case R.id.haptic_right_45:
                if(curved) {
                    sendTextMessage("haptic_right_45");
                }else{
                    sendTextMessage("haptic_right");
                }
                break;

            case R.id.haptic_right_90:
                if(curved) {
                    sendTextMessage("haptic_right_90");
                }else{
                    sendTextMessage("haptic_right");
                }
                break;

            case R.id.hapticstop:
                sendTextMessage("stop");
                break;

            case R.id.hapticstart:
                sendTextMessage("start");
                break;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
