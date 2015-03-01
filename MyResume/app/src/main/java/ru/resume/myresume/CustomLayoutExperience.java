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
public class CustomLayoutExperience extends View {
    Paint p_img;                     //Кисть  изображения
    Paint p_experience;              //Кисть  experience
    Paint p_text;                    //Кисть текста
    Paint p_point_group_1;           //Кисть точек
    Paint p_point_group_2;           //Кисть точек
    int alpha_transition = 255;      //Альфа  переходного  слоя
    int alpha_text_hobby = 0;        //Альфа текста hobby
    int alpha_text_app = 0;          //Альфа текста app
    Bitmap experience;               //пиктограмма  experience
    Geometry g;                      //Объект расщета координат векторов
    Typeface TF;                     //Шрифт
    ///////////////Переменные  геометрии////////////////////////////////////////////////////////////
    float c;                         //Коэффициент разрешения экрана
    RectF rect;                      //Прямоугольник  описывающий  изображение
    RectF rect_arc;                  //Прямоугольник  описывающий  дугу  изображения
    RectF rect_arc_mini;             //Прямоугольник  описывающий  дугу mini

    float xC,yC;                     //координаты  центральной точки

    //Линии (прямые)
    float x_line1_1, y_line1_1, x_line1_2, y_line1_2;          //Координаты линии 1
    float line_1 = 0;                                          //Длина
    float x_line2_1, y_line2_1, x_line2_2, y_line2_2;          //Координаты линии 2
    float line_2 = 0;                                          //Длина
    float x_line3_1, y_line3_1, x_line3_2, y_line3_2;          //Координаты линии 3
    float line_3 = 0;                                          //Длина

    //Точка хобби
    float x_hobby,y_hobby;                                     //Координаты точки
    float line_hobby = 75;                                     //Длиина  от точки x_line1_2;y_line1_2
    //Точка 2
    float x_point_1,y_point_1;                                 //Координаты  точки
    //Точка 2
    float x_point_2,y_point_2;                                 //Координаты  точки
    float angle_point_2 = 180;                                 //Угол  относительно  точки  хобби
    //Точка 3
    float x_point_3,y_point_3;                                 //Координаты  точки
    //Точка 4
    float x_point_4,y_point_4;                                 //Координаты  точки
    float angle_point_4 = 180;                                 //Угол  относительно  точки  хобби
    //Точка 5
    float x_point_5,y_point_5;                                 //Координаты  точки

    //Дуга experience
    float angle_experience = 0;      //Угол  дуги
    float line_experience = 0;       //Длина  смещения  изображения и дуги
    //Дуга мини
    float angle_mini_start = 180;    //Угол  дуги  начало
    float angle_mini_end = 0;        //Угол  дуги  конец
    //////////////////////////////////////////////////////////////////////////////////////////////

    public CustomLayoutExperience(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = CoefficientScreen.getC();                //Получаем  коэфицент экрана
        TF = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        g = new Geometry();
        //Rect описывающий bitmap
        rect = new RectF(100*c,100*c,250*c,250*c);
        //Прямоугольник  описывающий  дугу  изображения
        rect_arc = new RectF(80*c,80*c,270*c,270*c);
        //Прямоугольник  описывающий  дугу  mini
        rect_arc_mini = new RectF(100*c,100*c,250*c,250*c);
        //кисть  изображения
        p_img = new Paint();
        p_img.setAntiAlias(true);
        //кисть experience
        p_experience = new Paint();
        p_experience.setAntiAlias(true);
        p_experience.setARGB(255,0,0,0);
        p_experience.setStyle(Paint.Style.STROKE);
        //кисть  текста
        p_text = new Paint();
        p_text = new Paint();
        p_text.setAntiAlias(true);
        p_text.setStyle(Paint.Style.FILL);
        p_text.setTextAlign(Paint.Align.CENTER);
        p_text.setTextSize(40 * c);
        p_text.setTypeface(TF);
        //Кисть точек 1
        p_point_group_1 = new Paint();
        p_point_group_1.setAntiAlias(true);
        p_point_group_1.setStyle(Paint.Style.FILL);
        p_point_group_1.setARGB(0,0,0,0);
        //Кисть точек 2
        p_point_group_2 = new Paint();
        p_point_group_2.setAntiAlias(true);
        p_point_group_2.setStyle(Paint.Style.FILL);
        p_point_group_2.setARGB(0,0,0,0);
        //Bitmap experience
        experience = BitmapFactory.decodeResource(context.getResources(), R.drawable.experience);
        //Поток анимации
        ThreadAnimationMain TAM = new ThreadAnimationMain(this,Constants.PLAY_EXPERIENCE);
        ThreadStop.stop = true;                                  //Разрешение на запуск потока
        TAM.start();
    }
    @Override
    protected void onDraw(Canvas canvas){
        xC = getWidth()/2 - line_experience*c;
        yC = getHeight()/2;
        canvas.drawARGB(255,255,255,255);

        //Рисунок
        rect.offsetTo(xC - 75 * c, yC - 75 * c);
        canvas.drawBitmap(experience, null, rect, p_img);
        //Дуга
        rect_arc.offsetTo(xC - 95 * c, yC - 95 * c);
        canvas.drawArc(rect_arc,0,angle_experience,false,p_experience);
        //Дуга мини
        rect_arc_mini.offsetTo(x_hobby - 75 * c, y_hobby - 75 * c);
        canvas.drawArc(rect_arc_mini,angle_mini_start,angle_mini_end,false,p_experience);
        //Линии
        canvas.drawLine(x_line1_1,y_line1_1,x_line1_2,y_line1_2,p_experience);   //1
        canvas.drawLine(x_line2_1,y_line2_1,x_line2_2,y_line2_2,p_experience);   //2
        canvas.drawLine(x_line3_1,y_line3_1,x_line3_2,y_line3_2,p_experience);   //3
        //Точки
        canvas.drawCircle(x_point_1, y_point_1,5*c,p_point_group_1);               //1
        canvas.drawCircle(x_point_2, y_point_2,5*c,p_point_group_2);               //2
        canvas.drawCircle(x_point_3, y_point_3,5*c,p_point_group_2);               //3
        canvas.drawCircle(x_point_4, y_point_4,5*c,p_point_group_2);               //4
        canvas.drawCircle(x_point_5, y_point_5,5*c,p_point_group_2);               //5
        canvas.drawCircle(x_line1_2,y_line1_2,5*c,p_point_group_2);                //точка end line 1
        //Текст
        p_text.setARGB(alpha_text_hobby,0,0,0);
        canvas.drawText("hobby", x_hobby, y_hobby+10*c, p_text);
        p_text.setARGB(alpha_text_app,0,0,0);
        canvas.drawText("Java Application", x_line2_2, y_line2_2-7*c, p_text);
        canvas.drawText("Android Application", x_line3_2, y_line3_2+35*c, p_text);

        //Переходной  слой
        canvas.drawARGB(alpha_transition, 255, 255, 255);
    }
}
