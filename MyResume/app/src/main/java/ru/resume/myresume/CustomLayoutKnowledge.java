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
public class CustomLayoutKnowledge extends View {
    Paint p_img;                     //Кисть  изображения
    Paint p_arc;                     //Кисть  дуги изображения и линий
    Paint p_level;                   //Кисть  дуги level
    Paint p_scale;                   //Кисть  дуги scale
    Paint p_text;                    //Кисть  текста
    Paint p_point;                   //Кисть  точек основных
    Paint p_point_scale_1;           //Кисть  точки scale 1
    Paint p_point_scale_2;           //Кисть  точки scale 2
    Paint p_point_scale_3;           //Кисть  точки scale 3
    Paint p_point_scale_4;           //Кисть  точки scale 4
    Paint p_point_scale_5;           //Кисть  точки scale 5
    int alpha_transition = 255;      //Альфа  переходного  слоя
    int alpha_text_android = 0;      //Альфа текста android SDK
    int alpha_text_java = 0;         //Альфа текста Java SE
    int alpha_text_jun = 0;          //Альфа текста Junior
    int alpha_text_mid = 0;          //Альфа текста Middle
    int alpha_text_sen = 0;          //Альфа текста Senior
    int alpha_text_gur = 0;          //Альфа текста Guru
    Bitmap knowledge;                //пиктограмма  knowledge
    Geometry g;                      //Объект расщета координат векторов
    Typeface TF;                     //Шрифт
    ///////////////Пременные  геометрии////////////////////////////////////////////////////////////
    float c;                         //Коэффициент разрешения экрана
    RectF rect;                      //Прямоугольник  описывающий  изображение
    RectF rect_arc;                  //Прямоугольник  описывающий  дугу  изображения
    RectF rect_arc_level;            //Прямоугольник  описывающий  дугу уровень
    RectF rect_arc_scale;            //Прямоугольник  описывающий  шкалу
    float xC,yC;                     //координаты  центральной точки

    //Точки
    //Точка  на  дуге изображения
    float x_point_arc, y_point_arc;                            //Координаты точки
    float angle_point_arc = 0;                                 //Угол  относительно цетральной точки
    //Точка  на  дуге  scale 1
    float x_point_s_1, y_point_s_1;                            //Координаты точки
    float line_point_s_1 = 95;                                 //Длина от центра
    float angle_point_s_1 = 110;                               //Угол относительно центра
    //Точка  на  дуге  scale 2
    float x_point_s_2, y_point_s_2;                            //Координаты точки
    float angle_point_s_2 = 110;                               //Угол относительно центра
    //Точка  на  дуге  scale 3
    float x_point_s_3, y_point_s_3;                            //Координаты точки
    float angle_point_s_3 = 110;                               //Угол относительно центра
    //Точка  на  дуге  scale 4
    float x_point_s_4, y_point_s_4;                            //Координаты точки
    float angle_point_s_4 = 110;                               //Угол относительно центра
    //Точка  на  дуге  scale 5
    float x_point_s_5, y_point_s_5;                            //Координаты точки
    float angle_point_s_5 = 110;                               //Угол относительно центра

    //Линии основные
    float x_line1_1, y_line1_1, x_line1_2, y_line1_2;          //Координаты линии 1
    float line_1 = 0;                                          //Длина
    float x_line2_1, y_line2_1, x_line2_2, y_line2_2;          //Координаты линии 2
    float line_2 = 0;                                          //Длина
    float x_line3_1, y_line3_1, x_line3_2, y_line3_2;          //Координаты линии 3
    float line_3 = 0;                                          //Длина
    //Линии  scale
    float x_line_m_1, y_line_m_1, x_line_m_2, y_line_m_2;      //Координаты линии мини
    float line_m = 0;                                          //Длина  линии мини
    float x_line_s1_1, y_line_s1_1, x_line_s1_2, y_line_s1_2;  //Координаты линии scale 1
    float line_scale1 = 0;                                     //Длина  линии  scale 1
    float x_line_s2_1, y_line_s2_1, x_line_s2_2, y_line_s2_2;  //Координаты линии scale 2
    float line_scale2 = 0;                                     //Длина  линии  scale 2
    float x_line_s3_1, y_line_s3_1, x_line_s3_2, y_line_s3_2;  //Координаты линии scale 3
    float line_scale3 = 0;                                     //Длина  линии  scale 3
    float x_line_s4_1, y_line_s4_1, x_line_s4_2, y_line_s4_2;  //Координаты линии scale 4
    float line_scale4 = 0;                                     //Длина  линии  scale 4

    //Дуга knowledge
    float angle_knowledge = 0;      //Угол  дуги
    //Дуга scale
    float angle_scale = 0;          //Угол  дуги
    //Дуга level
    float angle_level = 0;          //Угол  дуги

    //////////////////////////////////////////////////////////////////////////////////////////////

    public CustomLayoutKnowledge(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = CoefficientScreen.getC();                //Получаем  коэфицент экрана
        TF = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        g = new Geometry();
        //Rect описывающий bitmap
        rect = new RectF(100*c,100*c,250*c,250*c);
        //Прямоугольник  описывающий  дугу  изображения
        rect_arc = new RectF(80*c,80*c,270*c,270*c);
        //Прямоугольник  описывающий  дугу  level
        rect_arc_level = new RectF(67*c,67*c,283*c,283*c);
        //Прямоугольник  описывающий  дугу  scale
        rect_arc_scale = new RectF(55*c,55*c,295*c,295*c);
        //кисть  изображения
        p_img = new Paint();
        p_img.setAntiAlias(true);
        //Кисть  дуги  изображения
        p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setStyle(Paint.Style.STROKE);
        //Кисть  level
        p_level = new Paint();
        p_level.setAntiAlias(true);
        p_level.setStyle(Paint.Style.STROKE);
        p_level.setStrokeWidth(10*c);
        //Кисть  scale
        p_scale = new Paint();
        p_scale.setAntiAlias(true);
        p_scale.setStyle(Paint.Style.STROKE);
        //Кисть  текста
        p_text = new Paint();
        p_text.setAntiAlias(true);
        p_text.setStyle(Paint.Style.FILL);
        p_text.setTextSize(40 * c);
        p_text.setTypeface(TF);
        //Кисть  точек
        p_point = new Paint();
        p_point.setAntiAlias(true);
        p_point.setStyle(Paint.Style.FILL);
        p_point.setARGB(0,0,0,0);
        //Кисть  точки scale 1
        p_point_scale_1 = new Paint();
        p_point_scale_1.setAntiAlias(true);
        p_point_scale_1.setStyle(Paint.Style.FILL);
        p_point_scale_1.setARGB(0,0,0,0);
        //Кисть  точки scale 2
        p_point_scale_2 = new Paint();
        p_point_scale_2.setAntiAlias(true);
        p_point_scale_2.setStyle(Paint.Style.FILL);
        p_point_scale_2.setARGB(0,0,0,0);
        //Кисть  точки scale 3
        p_point_scale_3 = new Paint();
        p_point_scale_3.setAntiAlias(true);
        p_point_scale_3.setStyle(Paint.Style.FILL);
        p_point_scale_3.setARGB(0,0,0,0);
        //Кисть  точки scale 4
        p_point_scale_4 = new Paint();
        p_point_scale_4.setAntiAlias(true);
        p_point_scale_4.setStyle(Paint.Style.FILL);
        p_point_scale_4.setARGB(0,0,0,0);
        //Кисть  точки scale 5
        p_point_scale_5 = new Paint();
        p_point_scale_5.setAntiAlias(true);
        p_point_scale_5.setStyle(Paint.Style.FILL);
        p_point_scale_5.setARGB(0,0,0,0);
        //Bitmap experience
        knowledge = BitmapFactory.decodeResource(context.getResources(), R.drawable.knowledge);
        //Поток анимации
        ThreadAnimationMain TAM = new ThreadAnimationMain(this,Constants.PLAY_KNOWLEDGE);
        ThreadStop.stop = true;                                  //Разрешение на запуск потока
        TAM.start();
    }
    @Override
    protected void onDraw(Canvas canvas){
        xC = getWidth()/2;
        yC = getHeight()/2;
        canvas.drawARGB(255,255,255,255);

        //Рисунок
        rect.offsetTo(xC - 75 * c, yC - 75 * c);
        canvas.drawBitmap(knowledge, null, rect, p_img);
        //Дуга рисунка
        rect_arc.offsetTo(xC - 95 * c, yC - 95 * c);
        canvas.drawArc(rect_arc,0,angle_knowledge,false,p_arc);
        //Дуга level
        rect_arc_level.offsetTo(xC - 108 * c, yC - 108 * c);
        canvas.drawArc(rect_arc_level,110,angle_level,false,p_level);
        //Дуга scale
        rect_arc_scale.offsetTo(xC - 120 * c, yC - 120 * c);
        canvas.drawArc(rect_arc_scale,110,angle_scale,false,p_scale);
        //Линии
        canvas.drawLine(x_line1_1,y_line1_1,x_line1_2,y_line1_2,p_arc);             //1 основная
        canvas.drawLine(x_line2_1,y_line2_1,x_line2_2,y_line2_2,p_arc);             //2 основная
        canvas.drawLine(x_line3_1,y_line3_1,x_line3_2,y_line3_2,p_arc);             //3 основная
        canvas.drawLine(x_line_m_1,y_line_m_1,x_line_m_2,y_line_m_2,p_arc);         //mini
        canvas.drawLine(x_line_s1_1,y_line_s1_1,x_line_s1_2,y_line_s1_2,p_arc);     //1 scale
        canvas.drawLine(x_line_s2_1,y_line_s2_1,x_line_s2_2,y_line_s2_2,p_arc);     //2 scale
        canvas.drawLine(x_line_s3_1,y_line_s3_1,x_line_s3_2,y_line_s3_2,p_arc);     //3 scale
        canvas.drawLine(x_line_s4_1,y_line_s4_1,x_line_s4_2,y_line_s4_2,p_arc);     //4 scale
        //Точки
        canvas.drawCircle(x_point_arc,y_point_arc,5*c,p_point);                       //1 на дуге изображения (основные)
        canvas.drawCircle(x_line1_2,y_line1_2,5*c,p_point);                           //2
        canvas.drawCircle(x_line2_2,y_line2_2,5*c,p_point);                           //3
        canvas.drawCircle(x_line3_2,y_line3_2,5*c,p_point);                           //4

        canvas.drawCircle(x_point_s_1, y_point_s_1, 5*c, p_point_scale_1);            //Точка  на  дуге  scale 1
        canvas.drawCircle(x_point_s_2, y_point_s_2, 5*c, p_point_scale_2);            //Точка  на  дуге  scale 2
        canvas.drawCircle(x_point_s_3, y_point_s_3, 5*c, p_point_scale_3);            //Точка  на  дуге  scale 3
        canvas.drawCircle(x_point_s_4, y_point_s_4, 5*c, p_point_scale_4);            //Точка  на  дуге  scale 4
        canvas.drawCircle(x_point_s_5, y_point_s_5, 5*c, p_point_scale_5);            //Точка  на  дуге  scale 5

        canvas.drawCircle(x_line_s1_2, y_line_s1_2 , 5*c, p_point_scale_2);           //Концевая точка scale 1
        canvas.drawCircle(x_line_s2_2, y_line_s2_2 , 5*c, p_point_scale_3);           //Концевая точка scale 2
        canvas.drawCircle(x_line_s3_2, y_line_s3_2 , 5*c, p_point_scale_4);           //Концевая точка scale 3
        canvas.drawCircle(x_line_s4_2, y_line_s4_2 , 5*c, p_point_scale_5);           //Концевая точка scale 4

        //Текст
        p_text.setTextAlign(Paint.Align.CENTER);
        p_text.setARGB(alpha_text_java,0,0,0);
        canvas.drawText("Java SE", x_line2_2, y_line2_2-7*c, p_text);
        p_text.setARGB(alpha_text_android,0,0,0);
        canvas.drawText("Android SDK", x_line3_2, y_line3_2+35*c, p_text);
        p_text.setTextAlign(Paint.Align.RIGHT);
        p_text.setARGB(alpha_text_jun,0,0,0);
        canvas.drawText("Junior", x_line_s1_2 - 7*c, y_line_s1_2 + 15*c, p_text);
        p_text.setARGB(alpha_text_mid,0,0,0);
        canvas.drawText("Middle", x_line_s2_2 - 7*c, y_line_s2_2 + 15*c, p_text);
        p_text.setARGB(alpha_text_sen,0,0,0);
        canvas.drawText("Senior", x_line_s3_2 - 7*c, y_line_s3_2 + 15*c, p_text);
        p_text.setARGB(alpha_text_gur,0,0,0);
        canvas.drawText("Guru", x_line_s4_2 - 7*c, y_line_s4_2 + 15*c, p_text);

        //Переходной  слой
        canvas.drawARGB(alpha_transition, 255, 255, 255);
    }
}
