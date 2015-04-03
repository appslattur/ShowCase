package com.special;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initAnimations();
    }


    public void initAnimations(){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(3000);
        anim.setRepeatCount(10);
        anim.setRepeatMode(Animation.REVERSE);
        //
        findViewById(R.id.splashView).startAnimation(anim);

        CountDownTimer waitForFinish = new CountDownTimer(2000, 10) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(i);
                SplashActivity.this.finish();
            }
        }.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
