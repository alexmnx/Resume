package ru.resume.myresume;

/**
 * Created by Александр on 20.01.2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class RadialControllerMain extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        CoefficientScreen.setHeight(metrics.heightPixels);
        setContentView(R.layout.radial_controller_main);
    }
    @Override
    public void onBackPressed() {
        finish();
        ThreadStop.stop = false;
    }
    @Override
    protected void onPause(){
        super.onPause();
        ThreadStop.stop = false;
        finish();
    }
}
