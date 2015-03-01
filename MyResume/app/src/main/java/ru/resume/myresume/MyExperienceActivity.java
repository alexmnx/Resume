package ru.resume.myresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Александр on 01.02.2015.
 */
public class MyExperienceActivity extends Activity {
    Intent intent;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.my_experience_activity);
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
