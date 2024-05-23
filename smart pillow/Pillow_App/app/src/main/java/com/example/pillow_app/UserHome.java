package com.example.pillow_app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class UserHome extends AppCompatActivity {
    Button b1;
    Intent i1,i2,i3;
    TextView tv,tv1;
    String code="";
    Vibrator vibrator;
    MediaPlayer mediaPlayer;
    EditText et;
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        b1=findViewById(R.id.b1);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);

        tv.setText("Smart Pillow App");
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    check_status();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask()
        {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try
                        {

                          check_status();

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 15000); //execute in every 60s*


    }

    public  void check_status() throws IOException, JSONException {
        URL url = new URL(Global.url+ "check_status");
        JSONObject jsn = new JSONObject();




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        String response = null;
        response = HttpConnection.getResponse(url, jsn);
        Log.d("response",response);

        if(response.endsWith("null"))
        {
            response=response.substring(0,response.length()-4);
        }

          if(!response.equals("")) {
              String a[]=response.split("#");

              String m="Temperature "+a[0]+"\nPulse Rate "+a[1]+"\nSPO2 "+a[2];

              m=m+"\nHealth Status "+a[3];
              m=m+"\n"+a[4];
              tv1.setText(m);
              if(a[3].equals("UNSAFE"))
              {
                  if (Build.VERSION.SDK_INT >= 26) {
                      vibrator.vibrate(VibrationEffect.createOneShot(4000, VibrationEffect.DEFAULT_AMPLITUDE));
                  } else {
                      vibrator.vibrate(4000);
                  }
                  mediaPlayer = MediaPlayer.create(UserHome.this, R.raw.alarm);
                  mediaPlayer.start();

              }


          }





    }


}