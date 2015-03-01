package ru.resume.myresume;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Александр on 01.02.2015.
 */
public class CustomLayoutData extends View {
    Paint p;                         //Кисть  изображения
    Paint p_arc;                     //Кисть  дуг  описывающих  изображение
    Paint p_text;                    //Кисть теста

    Geometry g;                      //Объект расщета координат векторов
    Typeface TF;                     //Шрифт

    //Альфа текста
    //Альфа 1
    int alpha_text1 = 0;
    //Альфа 2
    int alpha_text2 = 0;
    //Альфа 3
    int alpha_text3 = 0;
    //Альфа 4
    int alpha_text4 = 0;
    //Альфа 5
    int alpha_text5 = 0;

    ///////////////Пременные  геометрии////////////////////////////////////////////////////////////
    float c;                         //Коэффициент разрешения экрана
    RectF rect;                      //Прямоугольник описывающий битмап
    RectF rect_arc;                  //Прямоугольник  описывающий  дугу  изображения
    RectF rect_arc_mini;             //Прямоугольник  описывающий  малую  дугу  изображения
    Bitmap data;                     //Bitmap
    int alpha_transition = 255;      //альфа переходной анимации
    int alpha_p_arc = 255;           //альфа  кисти p_arc
    float xC,yC;                     //координаты  центральной точки
    float arc_angle = 0;             //Угол  поворота  дуги  изображения
    float line_arc = 0;              //Длина  смещения  изображения и дуги
    //Точка  на  дуге
    float x_arc_circle,y_arc_circle; //Координаты  точки  малаго  круга  на  конце дуги
    float r_circle = 0;              //Радиус  точки
    //Линия  1 от  точки  на дуге
    float x_line_1, y_line_1;        //Координаты второй точки
    float line_1 = 0;                //Длина  линии
    //Линия 2
    float x_line_2, y_line_2;        //Координаты второй точки
    float line_2 = 0;                //Длина  линии
    //Линия 3
    float x_line_3, y_line_3;        //Координаты второй точки
    float line_3 = 0;                //Длина  линии
    //Линия 4
    float x_line_4, y_line_4;        //Координаты второй точки
    float line_4 = 0;                //Длина  линии
    //Линия 5
    float x_line_5, y_line_5;        //Координаты второй точки
    float line_5 = 0;                //Длина  линии
    //Линия 6
    float x_line_6, y_line_6;        //Координаты второй точки
    float line_6 = 0;                //Длина  линии
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CustomLayoutData(Context context, AttributeSet attrs) {
        super(context, attrs);
        g = new Geometry();
        c = CoefficientScreen.getC();     //Получаем  коэфицент экрана
        TF = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        //Кисти
        p = new Paint();
        p_arc = new Paint();
        //Кисть текст
        p_text = new Paint();
        p_text.setTextSize(40 * c);
        p_text.setAntiAlias(true);
        p_text.setTypeface(TF);
        //Bitmap
        data = BitmapFactory.decodeResource(context.getResources(), R.drawable.man);
        //Rect описывающий bitmap
        rect = new RectF(100*c,100*c,250*c,250*c);
        //Прямоугольник  описывающий  дугу  изображения
        rect_arc = new RectF(80*c,80*c,270*c,270*c);
        //Прямоугольник  описывающий  малую  дугу  изображения
        ThreadAnimationMain TAM = new ThreadAnimationMain(this,Constants.PLAY_DATA);
        ThreadStop.stop = true;                                  //Разрешение на запуск потока
        TAM.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        xC = getWidth()/2;
        yC = getHeight()/2;

        canvas.drawARGB(255, 255, 255, 255);                      //Цвет канвы
        p.setAntiAlias(true);                                     //Включаем сглаживание
        //Кисть  дуги  описывающих  изображение
        p_arc.setAntiAlias(true);
        p_arc.setARGB(alpha_p_arc,0,0,0);
        p_arc.setStrokeWidth(1);
        p_arc.setStyle(Paint.Style.STROKE);

        //Изображение
        rect.offsetTo(xC-75*c-line_arc*c,yC-75*c);
        canvas.drawBitmap(data, null, rect, p);                    //Битмап
        //Дуга
        rect_arc.offsetTo(xC - 95 * c - line_arc * c, yC - 95 * c);
        canvas.drawArc(rect_arc, 0, arc_angle, false, p_arc);
        p_arc.setStyle(Paint.Style.FILL);
        p_arc.setStrokeWidth(2*c);
        canvas.drawCircle(x_arc_circle, y_arc_circle, r_circle*c, p_arc);
        //Линии
        //линия 1
        p_arc.setStrokeWidth(1);
        canvas.drawLine(x_arc_circle, y_arc_circle, x_line_1, y_line_1,p_arc);
        canvas.drawCircle(x_line_1, y_line_1, (r_circle+2)*c, p_arc);
        //линия 2
        canvas.drawLine(x_line_1, y_line_1, x_line_2, y_line_2, p_arc);
        canvas.drawCircle(x_line_2, y_line_2, r_circle*c, p_arc);
        //линия 3
        canvas.drawLine(x_line_1, y_line_1, x_line_3, y_line_3, p_arc);
        canvas.drawCircle(x_line_3, y_line_3, r_circle*c, p_arc);
        //линия 4
        canvas.drawLine(x_line_1, y_line_1, x_line_4, y_line_4, p_arc);
        canvas.drawCircle(x_line_4, y_line_4, r_circle*c, p_arc);
        //линия 5
        canvas.drawLine(x_line_1, y_line_1, x_line_5, y_line_5, p_arc);
        canvas.drawCircle(x_line_5, y_line_5, r_circle*c, p_arc);
        //линия 6
        canvas.drawLine(x_line_1, y_line_1, x_line_6, y_line_6, p_arc);
        canvas.drawCircle(x_line_6, y_line_6, r_circle*c, p_arc);
        //Текст
        //текст 1
        p_text.setARGB(alpha_text1,0,0,0);
        canvas.drawText("setName(“Alexsandr”)",x_line_2 + 5*c, y_line_2 - 2*c, p_text);
        //текст 2
        p_text.setARGB(alpha_text2,0,0,0);
        canvas.drawText("setLastName(“Khodanovich”)",x_line_3 + 5*c, y_line_3 + 10*c, p_text);
        //текст 3
        p_text.setARGB(alpha_text3,0,0,0);
        canvas.drawText("setPatronymic(“Vasilyevich”)",x_line_4 + 5*c, y_line_4 + 10*c, p_text);
        //текст 4
        p_text.setARGB(alpha_text4,0,0,0);
        canvas.drawText("setFullYears(“26”)",x_line_5 + 5*c, y_line_5 + 10*c, p_text);
        //текст 5
        p_text.setARGB(alpha_text5,0,0,0);
        canvas.drawText("setContactPhone(“+7-952-144-98-51”)",x_line_6 + 5*c, y_line_6 + 20*c, p_text);





        //Переходной слой
        canvas.drawARGB(alpha_transition, 255, 255, 255);
    }

}
