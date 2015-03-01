package ru.resume.myresume;


/**
 * Created by Александр on 28.01.2015.
 */
public class CoefficientScreen {
    static  float c;        //Коэффицент от эталона
    static int height;

    protected static float getC(){
        c = (((float)height)/((float)768));
        return c;
    }
    protected static void setHeight(int h){
        height=h;
    }
}
