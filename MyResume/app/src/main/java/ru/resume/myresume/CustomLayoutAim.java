package ru.resume.myresume;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Александр on 01.02.2015.
 */
public class CustomLayoutAim extends View {
    Paint p_img;                     //Кисть  изображения
    Paint p_aim;                     //Кисть  aim
    Paint p_point;                   //Кисть точек
    int alpha_point = 0;             //Альфа  точек
    int alpha_text = 0;              //Альфа  текста
    int alpha_transition = 255;      //Альфа  переходного  слоя
    Bitmap droid;                    //пиктограмма  андроид
    Geometry g;                      //Объект расщета координат векторов
    Typeface TF;                     //Шрифт
    ///////////////Переменные  геометрии////////////////////////////////////////////////////////////
    float c;                         //Коэффициент разрешения экрана
    RectF rect;                      //Прямоугольник  описывающий  изображение
    RectF rect_arc;                  //Прямоугольник  описывающий  дугу  изображения
    RectF rect_arc_mini;             //Прямоугольник  описывающий  малую  дугу  изображения
    float xC,yC;                     //координаты  центральной точки
    //Линии (прямые)
    float x_line1_1, y_line1_1, x_line1_2, y_line1_2;          //Координаты линии 1
    float line_1 = 0;                                          //Длина
    float x_line2_1, y_line2_1, x_line2_2, y_line2_2;          //Координаты линии 2
    float line_2 = 0;                                          //Длина
    float x_line3_1, y_line3_1, x_line3_2, y_line3_2;          //Координаты линии 3
    float line_3 = 0;                                          //Длина
    float x_line4_1, y_line4_1, x_line4_2, y_line4_2;          //Координаты линии 4
    float line_4 = 0;                                          //Длина
    float x_line5_1, y_line5_1, x_line5_2, y_line5_2;          //Координаты линии 5
    float line_5 = 0;                                          //Длина
    float x_line6, y_line6;                                    //Координаты линии 6
    float line_6 = 0;                                          //Длина
    //Точки
    float x_point_1, y_point_1;                                //Координаты точки 1
    float x_point_2, y_point_2;                                //Координаты точки 2
    float x_point_3, y_point_3;                                //Координаты точки 3
    float radius_p3 = 0;                                       //Радиус  точки 3
    float radius_p4 = 0;                                       //Радиус  точки 4
    float radius_p5 = 0;                                       //Радиус  точки 5
    /*Точки 4 и 5 привязываются к конечным координатам линий 5 и 6 соответственно*/

    //Дуги
    //Дуга aim
    float angle_aim = 0;                                       //Угол  дуги
    float line_aim = 0;                                        //Длина  смещения  изображения и дуги
    //Дуга mini
    float angle_mini = 0;                                      //Угол  дуги

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public CustomLayoutAim(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = CoefficientScreen.getC();                //Получаем  коэфицент экрана
        TF = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        g = new Geometry();
        //Rect описывающий bitmap
        rect = new RectF(100*c,100*c,250*c,250*c);
        //Прямоугольник  описывающий  дугу  изображения
        rect_arc = new RectF(80*c,80*c,270*c,270*c);
        //Прямоугольник  описывающий  малую  дугу  изображения
        rect_arc_mini = new RectF(70*c,70*c,280*c,280*c);
        //кисть  изображения
        p_img = new Paint();
        p_img.setAntiAlias(true);
        //кисть  aim
        p_aim = new Paint();
        p_aim.setAntiAlias(true);
        p_aim.setARGB(255,0,0,0);
        p_aim.setStyle(Paint.Style.STROKE);
        //Кисть точек
        p_point = new Paint();
        p_point.setAntiAlias(true);
        p_point.setStyle(Paint.Style.FILL);
        p_point.setTextSize(40 * c);
        p_point.setTypeface(TF);
        //Bitmap droid
        droid = BitmapFactory.decodeResource(context.getResources(), R.drawable.android_black);
        //Поток анимации
        ThreadAnimationMain TAM = new ThreadAnimationMain(this,Constants.PLAY_AIM);
        ThreadStop.stop = true;                                  //Разрешение на запуск потока
        TAM.start();
    }
    @Override
    protected void onDraw(Canvas canvas){
        xC = getWidth()/2 - line_aim*c;
        yC = getHeight()/2;
        canvas.drawARGB(255,255,255,255);

        //Рисунок
        rect.offsetTo(xC - 75 * c, yC - 75 * c);
        canvas.drawBitmap(droid, null, rect, p_img);
        //Дуга
        rect_arc.offsetTo(xC - 95 * c, yC - 95 * c);
        canvas.drawArc(rect_arc,0,angle_aim,false,p_aim);
        //Малая дуга
        rect_arc_mini.offsetTo(xC - 105 * c, yC - 105 * c);
        canvas.drawArc(rect_arc_mini,275,angle_mini,false,p_aim);
        //Линии
        canvas.drawLine(x_line1_1,y_line1_1,x_line1_2,y_line1_2,p_aim);   //1
        canvas.drawLine(x_line2_1,y_line2_1,x_line2_2,y_line2_2,p_aim);   //2
        canvas.drawLine(x_line3_1,y_line3_1,x_line3_2,y_line3_2,p_aim);   //3
        canvas.drawLine(x_line4_1,y_line4_1,x_line4_2,y_line4_2,p_aim);   //4
        canvas.drawLine(x_line5_1,y_line5_1,x_line5_2,y_line5_2,p_aim);   //5
        canvas.drawLine(x_line5_2,y_line5_2,x_line6,y_line6,p_aim);       //6
        //Точки
        p_point.setARGB(alpha_point,0,0,0);
        canvas.drawCircle(x_point_1, y_point_1, 5*c, p_point);              //1
        canvas.drawCircle(x_point_2, y_point_2, 5*c, p_point);              //2
        canvas.drawCircle(x_point_3, y_point_3, radius_p3*c, p_point);      //3
        canvas.drawCircle(x_line5_2, y_line5_2, radius_p4*c, p_point);      //4
        canvas.drawCircle(x_line6, y_line6, radius_p5*c, p_point);          //5
        //Текст
        p_point.setARGB(alpha_text,0,0,0);
        canvas.drawText("Android Application Development", x_line5_2, y_line5_2-7*c, p_point);



        //Переходной  слой
        canvas.drawARGB(alpha_transition, 255, 255, 255);
    }

}
