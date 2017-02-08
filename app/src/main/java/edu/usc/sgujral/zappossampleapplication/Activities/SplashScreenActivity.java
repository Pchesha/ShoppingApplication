package edu.usc.sgujral.zappossampleapplication.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.usc.sgujral.zappossampleapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_splash_screen);

       // new Handler().postDelayed(new Runnable() {

//            @Override
//            public void run() {

                Intent i = new Intent(SplashScreenActivity.this,MainActivityFirst.class);
                startActivity(i);
                finish();
//            }
//        }, SPLASH_TIME_OUT);
    }
}
