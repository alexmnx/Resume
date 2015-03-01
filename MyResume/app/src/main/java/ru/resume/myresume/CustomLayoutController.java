package ru.resume.myresume;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Александр on 20.01.2015.
 */
public class CustomLayoutController extends View {
    Intent iData;                    //Интент data
    Intent iAim;                     //Интент aim
    Intent iExperience;              //Интент experience
    Intent iKnowledge;               //Интент knowledge

    ThreadAnimationMain TAM;         //поток  расчета  анимации

    Geometry g;                      //Объект расщета координат векторов
    float c;                         //Коэффициент разрешения экрана
    int alpha_transition=0;          //альфа переходной анимации

    /////////Координаты касаний/////////////////////////////////////////////////////////////////////
    float xTouch, yTouch;
    /////////ПЕРЕМЕННЫЕ ГЕОМЕТРИИ////////////////////////////////////////////////////////////////////

    float xC,yC;                     //координаты начальной точки
    //геометрия точки адрес
    float x_address,y_address;       //координаты точки адрес
    float line_address = 0;          //длина  до  точки адрес
    float angle_address = 0;         //относительный угол поворота
    //геометрия точки data
    float x_data, y_data;            //координаты точки data
    float line_data = 0;             //длина до точки data
    float angle_data = 310;          //относительный угол поворота
    //геометрия точки knowledge
    float x_knowledge, y_knowledge;  //координаты точки knowledge
    float line_knowledge = 0;        //длина до точки knowledge
    float angle_knowledge = 100;     //относительный угол поворота
    //геометрия точки experience
    float x_experience, y_experience;//координаты точки experience
    float line_experience = 0;       //длина до точки experience
    float angle_experience = 170;    //относительный угол поворота
    //геометрия точки aim
    float x_aim,y_aim;               //координаты до точки aim
    float line_aim = 0;              //длина до точки aim
    float angle_aim = 230;           //относительный угол поворота
    //Радиус большого правого круга
    float radius_great_circle;
    //геометрия малой башни справа от вышки
    float x_tower, y_tower;          //координаты конечной точки
    float line_tower = 0;          //высота башни     250
    //геометрия небоскреб
    float x_skyscraper, y_skyscraper;//координаты конечной точки
    float line_skyscraper = 0;     //Высота небосреба   310
    //геометрия дома справа от небоскреба
    float x_house_r,y_house_r;      //координаты конечной точки
    float line_house_r = 0;         //Высота дома      200
    //геометрия дома слева от небоскреба
    float x_house_l, y_house_l;     //кординаты конечной точки
    float line_house_l = 0;         //Высота дома    230
    //геометрия нижний дом
    float x_house_b, y_house_b;     //координаты конечной точки
    float line_house_b = 0;         //Высота дома       190
    //Геометрия дом вышки
    float x_house, y_house;         //координаты конечной точки
    float line_house = 0;           //высота дома           210
    //геометрия вышки
    float x_tower_1,y_tower_1;      //координаты конечной точки 1-го сектора
    float line_tower_1 = 0;         //высота первого сектора     230
    float x_tower_2,y_tower_2;      //координаты конечной точки 2-го сектора
    float line_tower_2 = 0;          //высота второго сектора     30
    float x_tower_3,y_tower_3;      //координаты конечной точки 3-го сектора
    float line_tower_3 = 0;         //высота третьего сектора    50
    float x_tower_4,y_tower_4;      //координаты конечной точки 4-го сектора
    float line_tower_4 = 0;         //высота четвертого сектора  30
    float x_tower_5,y_tower_5;      //координаты конечной точки 5-го сектора
    float line_tower_5 = 0;         //высота пятого сектора      30
    ///////////////////////////////////////////////////////////////////////////////////////////////

    int alpha_aim = 0;              //Прозрачность текста
    int alpha_data = 0;             //Прозрачность текста
    int alpha_experience = 0;       //Прозрачность текста
    int alpha_knowledge = 0;        //Прозрачность текста
    Paint p_blac;                   //Кисть черная
    Paint p_white;                  //Кисть белая
    float a,b;                      //Длины прямоугольника описывающего центральные дуги
    Typeface TF;                    //Шрифт
    int PLAY_LAVEL = 1;             //Уровень прорисовки
    int TRANSITION_ANIMATION = 0;   //Анимация перехода + управеление переходом



    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CustomLayoutController(Context context, AttributeSet set){
        super(context);
        //Объект  geometri
        g = new Geometry();
        //Коэфициент  экрана
        c = CoefficientScreen.getC();
        a = 150;
        b = 150;
        //Кисть черная
        p_blac = new Paint();
        //Кисть белая
        p_white = new Paint();
        //Шрифт
        TF = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        //Поток  расчета анимации
        TAM = new ThreadAnimationMain(context, this,Constants.PLAY_CONTROLLER);
        ThreadStop.stop = true;                                  //Разрешение на запуск потока
        TAM.start();

        //Интенты  управления  переходом
        iData = new Intent(context, MyDataActivity.class);
        iData.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        iAim = new Intent(context, MyAimActivity.class);
        iAim.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        iExperience = new Intent(context, MyExperienceActivity.class);
        iExperience.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        iKnowledge = new Intent(context, MyKnowledgeActivity.class);
        iKnowledge.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        xC = getWidth();
        yC = getHeight();
        
        if(x_tower == 0){
            //Прячем  баг  отрисовки  при  загрузке
            xC += 1000;
            yC -= 1000;
        }

        canvas.drawARGB(255, 255, 255, 255);             //Цвет канвы
        p_blac.setAntiAlias(true);                       //Включаем сглаживание
        p_white.setAntiAlias(true);                      //Включаем сглаживание
        p_blac.setStyle(Paint.Style.STROKE);             //Стиль фигуры - контур
        p_white.setARGB(255,255,255,255);                //Кисть цвет белый
        p_blac.setARGB(255, 0, 0, 0);                    //Кисть чвет черный


        /////ПРОРИСОВКА ТЕКСТА/////////////////////////////////
        p_blac.setStrokeWidth(1);
        p_blac.setStyle(Paint.Style.FILL);
        p_blac.setTextSize(50*c);
        p_blac.setTextAlign(Paint.Align.RIGHT);
        p_blac.setTypeface(TF);

        p_blac.setARGB(alpha_aim, 0, 0, 0);
        canvas.drawText( "my.Aim",x_aim, y_aim, p_blac);

        p_blac.setARGB(alpha_data, 0, 0, 0);
        canvas.drawText( "my.Data", x_data, y_data, p_blac);

        p_blac.setARGB(alpha_experience, 0, 0, 0);
        canvas.drawText( "my.Experience", x_experience, y_experience, p_blac);

        p_blac.setARGB(alpha_knowledge, 0, 0, 0);
        canvas.drawText( "my.Knowledge", x_knowledge, y_knowledge, p_blac);
        //////////////////////////////////////////////////////
        ///////ПРОРИСОВКА ГОРОДА//////////////////////////////
        p_blac.setARGB(255,0,0,0);
        //Рисуем небоскреб
        p_blac.setStrokeWidth(80*c);
        canvas.drawLine(xC, yC, x_skyscraper, y_skyscraper, p_blac);
        canvas.drawCircle(x_skyscraper,y_skyscraper,30*c, p_blac);
        p_blac.setStrokeWidth(60*c);
        canvas.drawLine(xC, yC,g.getCordX(xC,230,line_skyscraper+25),
                    g.getCordY(yC,230,line_skyscraper+25), p_blac);
        p_blac.setStrokeWidth(2*c);
        canvas.drawLine(xC, yC,g.getCordX(xC, 230, line_skyscraper+50),
                    g.getCordY(yC, 230, line_skyscraper+50), p_blac);
        //Рисуем башню справа от вышки
        p_blac.setStrokeWidth(30*c);
        canvas.drawLine(xC, yC, x_tower, y_tower, p_blac);
        canvas.drawCircle(x_tower, y_tower,12*c, p_blac);
        p_blac.setStrokeWidth(1*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 250, line_tower+30),
                    g.getCordY(yC, 250, line_tower+30), p_blac);
        //Рисуем дом справа от небосреба
        p_white.setStrokeWidth(84*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 240, line_house_r+2),
                    g.getCordY(yC,240,line_house_r+2), p_white);
        p_blac.setStrokeWidth(80*c);
        canvas.drawLine(xC, yC, x_house_r, y_house_r, p_blac);
        //Рисуем дом слева от небоскреба
        p_white.setStrokeWidth(94*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 200, line_house_l+2),
                    g.getCordY(yC, 200, line_house_l+2), p_white);
        p_blac.setStrokeWidth(90*c);
        canvas.drawLine(xC, yC, x_house_l, y_house_l, p_blac);
        p_blac.setStrokeWidth(80*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 200, line_house_l+15),
                    g.getCordY(yC, 200, line_house_l+15), p_blac);
        p_blac.setStrokeWidth(60*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 200, line_house_l+30),
                    g.getCordY(yC, 200, line_house_l+30), p_blac);
        p_blac.setStrokeWidth(2*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 200, line_house_l+60),
                    g.getCordY(yC, 200, line_house_l+60), p_blac);
        //Рисуем  нижний дом
        p_white.setStrokeWidth(94*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 190, line_house_b+2),
                    g.getCordY(yC, 190, line_house_b+2), p_white);
        p_white.setStrokeWidth(74*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 190, line_house_b+17),
                    g.getCordY(yC, 190, line_house_b+17), p_white);
        p_blac.setStrokeWidth(70*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 190, line_house_b+15),
                    g.getCordY(yC, 190, line_house_b+15), p_blac);
        p_blac.setStrokeWidth(90*c);
        canvas.drawLine(xC, yC, x_house_b, y_house_b, p_blac);

        //Рисуеи здание радиовышки
        p_white.setStrokeWidth(64*c);
        canvas.drawLine(xC, yC, g.getCordX(xC, 265, line_house+2*c),
                    g.getCordY(yC, 265, line_house+2*c), p_white);
        p_blac.setStrokeWidth(60*c);
        canvas.drawLine(xC, yC, x_house, y_house, p_blac);
        //Рисуем радиовышку
        p_white.setStrokeWidth(14*c);
        canvas.drawLine(xC, yC, x_tower_1, y_tower_1, p_white);
        p_blac.setStrokeWidth(12*c);
        canvas.drawLine(xC, yC, x_tower_1, y_tower_1, p_blac);
        canvas.drawCircle(x_tower_1, y_tower_1,10*c, p_blac);
        p_blac.setStrokeWidth(20*c);
        canvas.drawLine(x_tower_1, y_tower_1, x_tower_2, y_tower_2, p_blac);
        canvas.drawCircle(x_tower_2, y_tower_2,10*c, p_blac);
        p_blac.setStrokeWidth(10*c);
        canvas.drawLine(x_tower_2, y_tower_2, x_tower_3, y_tower_3, p_blac);
        canvas.drawCircle(x_tower_3, y_tower_3,6*c, p_blac);
        p_blac.setStrokeWidth(7*c);
        canvas.drawLine(x_tower_3, y_tower_3, x_tower_4, y_tower_4, p_blac);
        canvas.drawCircle(x_tower_4, y_tower_4,5*c, p_blac);
        p_blac.setStrokeWidth(3*c);
        canvas.drawLine(x_tower_4, y_tower_4, x_tower_5, y_tower_5, p_blac);
        canvas.drawCircle(x_tower_5, y_tower_5,3*c, p_blac);

        //Рисуем линии
        p_blac.setStrokeWidth(1);
        canvas.drawLine(x_tower_5, y_tower_5, x_address, y_address, p_blac);
        canvas.drawCircle(x_address, y_address,7*c, p_blac);
        canvas.drawLine(x_address, y_address, x_aim, y_aim, p_blac);
        canvas.drawCircle(x_aim, y_aim,4*c, p_blac);
        canvas.drawLine(x_address, y_address, x_data, y_data, p_blac);
        canvas.drawCircle(x_data, y_data,4*c, p_blac);
        canvas.drawLine(x_address, y_address, x_knowledge, y_knowledge, p_blac);
        canvas.drawCircle(x_knowledge, y_knowledge,4*c, p_blac);
        canvas.drawLine(x_address, y_address, x_experience, y_experience, p_blac);
        canvas.drawCircle(x_experience, y_experience,4*c, p_blac);

        //Рисуем круг
        canvas.drawCircle(getWidth(), getHeight(), radius_great_circle*c, p_blac);
        p_white.setStyle(Paint.Style.STROKE);
        p_white.setStrokeWidth(2*c);
        canvas.drawCircle(getWidth(), getHeight(), radius_great_circle*c, p_white);
        p_white.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth(), getHeight(),110*c, p_white);
        canvas.drawARGB(alpha_transition, 255, 255, 255);    //Переходной слой

    }
    //Обрабатываем  касание. В  событии  ACTION_DOWN  проверяем  совпадение  касания  с  одним из  полей
    //кнопок  в  случае  совпадения  присваеваем  переменной TRANSITION_ANIMATION  соответствующее  значение
    //далее  в  паралельном потоке  происходит  переключение  на  другое  активити
    @Override
    public boolean onTouchEvent(MotionEvent event){
        xTouch = event.getX();
        yTouch = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(xTouch>(x_data-180*c)&&xTouch<x_data&&yTouch>(y_data-50*c)&&yTouch<y_data){
                    alpha_data = 100;
                    TRANSITION_ANIMATION = 1;
                }
                if(xTouch>(x_aim-165*c)&&xTouch<x_aim&&yTouch>(y_aim-50*c)&&yTouch<y_aim){
                    alpha_aim = 100;
                    TRANSITION_ANIMATION = 2;
                }
                if(xTouch>(x_experience-310*c)&&xTouch<x_experience&&yTouch>(y_experience-50*c)&&yTouch<y_experience){
                    alpha_experience = 100;
                    TRANSITION_ANIMATION = 3;
                }
                if(xTouch>(x_knowledge-310*c)&&xTouch<x_knowledge&&yTouch>(y_knowledge-50*c)&&yTouch<y_knowledge){
                    alpha_knowledge = 100;
                    TRANSITION_ANIMATION = 4;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                alpha_data = 255;
                alpha_aim = 255;
                alpha_experience = 255;
                alpha_knowledge = 255;
                break;
        }
        return true;
    }
}
