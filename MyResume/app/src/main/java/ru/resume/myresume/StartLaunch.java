package ru.resume.myresume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


public class StartLaunch extends Activity {
    Thread thread;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_launch);
        intent = new Intent(this, RadialControllerMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Typeface CF = Typeface.createFromAsset(this.getAssets(), "fonts/exo.ttf");
        TextView start_text = ((TextView)findViewById(R.id.start_text));
        start_text.setText("my.RESUME");
        start_text.setTypeface(CF);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    StartA();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
    public void StartA(){
        this.startActivity(intent);
    }
}
