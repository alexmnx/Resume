package ru.resume.myresume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Александр on 01.02.2015.
 */
public class MyAimActivity extends Activity {
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_aim_activity);
        intent = new Intent(this, RadialControllerMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }
    @Override
    public void onBackPressed() {
        finish();
        ThreadStop.stop = false;                   //Разрешение на остановку  потока  анимации
        startActivity(intent);
    }
}
