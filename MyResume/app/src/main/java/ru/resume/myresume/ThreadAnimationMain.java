package ru.resume.myresume;


import android.content.Context;

/**
 * Created by Александр on 21.01.2015.
 */
public class ThreadAnimationMain extends Thread implements Runnable {
    CustomLayoutController CLC;
    CustomLayoutData CLD;
    CustomLayoutAim CLA;
    CustomLayoutExperience CLE;
    CustomLayoutKnowledge CLK;
    Calculations CT;

    Context con;

    boolean FLOW_CONTROL = true;       //Управление потоком
    int i=0;                           //Общая интерация
    int s=2;                           //Скорость
    int THREAD_ACTIVITY;

    ThreadAnimationMain(Context context, CustomLayoutController customLayoutController, int play){
        con = context;
        CLC = customLayoutController;
        CT = new Calculations(CLC, Constants.PLAY_CONTROLLER);
        THREAD_ACTIVITY = play;
    }
    ThreadAnimationMain(CustomLayoutData customLayoutData, int play){
        CLD = customLayoutData;
        CT = new Calculations(CLD, Constants.PLAY_DATA);
        THREAD_ACTIVITY = play;
    }
    ThreadAnimationMain(CustomLayoutAim customLayoutAim, int play){
        CLA = customLayoutAim;
        CT = new Calculations(CLA, Constants.PLAY_AIM);
        THREAD_ACTIVITY = play;
    }
    ThreadAnimationMain(CustomLayoutExperience customLayoutExperience, int play){
        CLE = customLayoutExperience;
        CT = new Calculations(CLE, Constants.PLAY_EXPERIENCE);
        THREAD_ACTIVITY = play;
    }
    ThreadAnimationMain(CustomLayoutKnowledge customLayoutKnowledge, int play){
        CLK = customLayoutKnowledge;
        CT = new Calculations(CLK, Constants.PLAY_KNOWLEDGE);
        THREAD_ACTIVITY = play;
    }

    @Override
    public void run() {
        while (FLOW_CONTROL){
            i++;                                          //Интерация  в  потоке
            CT.calculation();                             //Расчет координат
            ThreadControl(ThreadStop.stop);               //Завершение потока в случае onBackPress
            switch (THREAD_ACTIVITY){
                //Анимация главного  окна
                case Constants.PLAY_CONTROLLER:
                    ///////СТАРТОВАЯ АНИМАЦИЯ//////////////////////////////////////////
                    //Анимация города
                    if (i <= 150){
                        CLC.radius_great_circle = i;
                    }
                    if (i >= 30 && i<=220){
                        CLC.line_house_b = ++CLC.line_house_b;
                    }
                    if (i >= 60 && i<=260){
                        CLC.line_house_r = ++CLC.line_house_r;
                    }
                    if (i >= 90 && i<=300){
                        CLC.line_house = ++CLC.line_house;
                    }
                    if (i >= 120 && i<=370){
                        CLC.line_tower = ++CLC.line_tower;
                    }
                    if (i >= 150 && i<=380){
                        CLC.line_house_l = ++CLC.line_house_l;
                    }
                    if (i >= 200 && i<=510){
                        CLC.line_skyscraper = ++CLC.line_skyscraper;
                    }
                    if (i >= 450 && i<=680){
                        CLC.line_tower_1 = ++CLC.line_tower_1;
                    }
                    if (i >= 450 && i<=480){
                        CLC.line_tower_2 = ++CLC.line_tower_2;
                    }
                    if (i >= 680 && i<=730){
                        CLC.line_tower_3 = ++CLC.line_tower_3;
                    }
                    if (i >= 730 && i<=760){
                        CLC.line_tower_4 = ++CLC.line_tower_4;
                    }
                    if (i >= 760 && i<=790){
                        CLC.line_tower_5 = ++CLC.line_tower_5;
                    }
                    //Анимация линий
                    if(i>790&&i<1090){
                        s = 1;
                        CLC.angle_address = 170;
                        CLC.line_address = CLC.line_address+1;
                    }
                    if(i>1090&&i<=1340){
                        CLC.line_data = ++CLC.line_data;
                    }
                    if(i>1090&&i<=1240){
                        CLC.line_aim = ++CLC.line_aim;
                    }
                    if(i>1090&&i<=1290){
                        CLC.line_experience = ++CLC.line_experience;
                    }
                    if(i>1090&&i<=1290){
                        CLC.line_knowledge = ++CLC.line_knowledge;
                    }
                    //Анимация текста
                    if(i>1300&&i<=1555){
                        CLC.alpha_aim = ++CLC.alpha_aim;
                        CLC.alpha_data = ++CLC.alpha_data;
                        CLC.alpha_experience = ++CLC.alpha_experience;
                        CLC.alpha_knowledge = ++CLC.alpha_knowledge;
                    }
                    //Переходная анимация
                    //Переход к my.Data
                    if(CLC.TRANSITION_ANIMATION == 1){
                        if(CLC.alpha_transition<=254){
                            CLC.alpha_transition=CLC.alpha_transition+1;
                            if(CLC.alpha_transition == 255){
                                ThreadControl(false);
                                con.startActivity(CLC.iData);
                            }
                        }
                    }
                    //Переход к my.Aim
                    if(CLC.TRANSITION_ANIMATION == 2){
                        if(CLC.alpha_transition<=254){
                            CLC.alpha_transition=CLC.alpha_transition+1;
                            if(CLC.alpha_transition == 255){
                                ThreadControl(false);
                                con.startActivity(CLC.iAim);
                            }
                        }
                    }
                    //Переход к my.Experience
                    if(CLC.TRANSITION_ANIMATION == 3){
                        if(CLC.alpha_transition<=254){
                            CLC.alpha_transition=CLC.alpha_transition+1;
                            if(CLC.alpha_transition == 255){
                                ThreadControl(false);
                                con.startActivity(CLC.iExperience);
                            }
                        }
                    }
                    //Переход к my.Knowledge
                    if(CLC.TRANSITION_ANIMATION == 4){
                        if(CLC.alpha_transition<=254){
                            CLC.alpha_transition=CLC.alpha_transition+1;
                            if(CLC.alpha_transition == 255){
                                ThreadControl(false);
                                con.startActivity(CLC.iKnowledge);
                            }
                        }
                    }
                    //Обновление главного  потока
                    if(CLC.alpha_transition != 255 && CLC.x_tower != 0){
                        CLC.postInvalidate();
                    }
                    break;
                //Анимация  окна my.Data
                case Constants.PLAY_DATA:
                    //Переходная анимация альфа
                    if(i>=500 && i<755){
                        CLD.alpha_transition = --CLD.alpha_transition;
                        CLD.alpha_p_arc = 0;
                    }
                    //Анимация  дуги
                    if(i>755&&i<=1115){
                        CLD.alpha_p_arc = 255;
                        s = 1;
                        CLD.r_circle = 5;
                        CLD.arc_angle = ++CLD.arc_angle;
                    }
                    //Анимация  смещения  дуги  и  изображения
                    if(i>755&&i<=1055){
                        CLD.line_arc = ++CLD.line_arc;
                    }
                    //Анимация  линии 1
                    if(i>1055&&i<=1155){
                        CLD.line_1 = ++CLD.line_1;
                    }
                    //Анимация линии 2
                    if(i>1205&&i<=1405){
                        CLD.line_2 = ++CLD.line_2;
                    }
                    //Анимация линии 3
                    if(i>1255&&i<=1330){
                        CLD.line_3 = ++CLD.line_3;
                    }
                    //Анимация линии 4
                    if(i>1305&&i<=1355){
                        CLD.line_4 = ++CLD.line_4;
                    }
                    //Анимация линии 5
                    if(i>1355&&i<=1430){
                        CLD.line_5 = ++CLD.line_5;
                    }
                    //Анимация линии 6
                    if(i>1405&&i<=1605){
                        CLD.line_6 = ++CLD.line_6;
                    }
                    //Анимация текста1
                    if(i>1405&&i<=1660){
                        CLD.alpha_text1 = ++CLD.alpha_text1;
                    }
                    //Анимация текста2
                    if(i>1330&&i<=1585){
                        CLD.alpha_text2 = ++CLD.alpha_text2;
                    }
                    //Анимация текста3
                    if(i>1355&&i<=1610){
                        CLD.alpha_text3 = ++CLD.alpha_text3;
                    }
                    //Анимация текста4
                    if(i>1430&&i<=1685){
                        CLD.alpha_text4 = ++CLD.alpha_text4;
                    }
                    //Анимация текста5
                    if(i>1605&&i<=1860){
                        CLD.alpha_text5 = ++CLD.alpha_text5;
                    }
                    //Обновление главного  потока
                    CLD.postInvalidate();
                    break;
                //Анимация  окна my.Aim
                case Constants.PLAY_AIM:
                    //Переходная анимация альфа
                    if(i>=300 && i<555){
                        CLA.alpha_transition = --CLA.alpha_transition;
                    }
                    //Анимация  дуги aim
                    if(i>555&&i<=915){
                        s=1;
                        CLA.angle_aim = ++CLA.angle_aim;
                    }
                    //Анимация  смещения изображения + aim
                    if(i>555&&i<=855){
                        CLA.line_aim = ++CLA.line_aim;
                    }
                    //Анимация линии 1
                    if(i>645&&i<=695){
                        CLA.line_1 = ++CLA.line_1;
                    }
                    //Анимация линии 2
                    if(i>735&&i<=785){
                        CLA.line_2 = ++CLA.line_2;
                    }
                    //Анимация линии 3
                    if(i>825&&i<=875){
                        CLA.line_3 = ++CLA.line_3;
                    }
                    //Анимация линии 4
                    if(i>915&&i<=965){
                        CLA.line_4 = ++CLA.line_4;
                    }
                    //Анимация дуги mini
                    if(i>965&&i<=1045){
                        s = 2;
                        CLA.alpha_point = 255;
                        CLA.angle_mini = ++CLA.angle_mini;
                    }
                    //Анимация  прибавления радиуса точки 3
                    if(i>1045&&i<=1052){
                        CLA.radius_p3 = ++CLA.radius_p3;
                    }
                    //Анимация  линии 5
                    if(i>1052&&i<=1102){
                        CLA.radius_p4 = 5;
                        CLA.radius_p5 = 5;
                        CLA.line_5 = ++CLA.line_5;
                    }
                    //Анимация  линии 6
                    if(i>1102&&i<=1667){
                        s = 1;
                        CLA.line_6 = ++CLA.line_6;
                    }
                    //Анимация теста
                    if(i>1667&&i<1922){
                        CLA.alpha_text = ++CLA.alpha_text;
                    }
                    //Обновление главного  потока
                    CLA.postInvalidate();
                    break;
                //Анимация  окна my.Experience
                case Constants.PLAY_EXPERIENCE:
                    s = 1;
                    //Переходная анимация альфа
                    if(i>=300 && i<555){
                        CLE.alpha_transition = --CLE.alpha_transition;
                    }
                    //Анимация  дуги experience
                    if(i>555&&i<=915){
                        CLE.p_point_group_1.setARGB(255,0,0,0);
                        CLE.angle_experience = ++CLE.angle_experience;
                    }
                    //Анимация  смещения изображения + experience
                    if(i>555&&i<=755){
                        CLE.line_experience = ++CLE.line_experience;
                    }
                    //Анимация  линии 1
                    if(i>915&&i<=1015){
                        CLE.p_point_group_2.setARGB(255,0,0,0);
                        CLE.line_1 = ++CLE.line_1;
                    }
                    //Анимация  дуги  mini
                    if(i>1015&&i<=1135){
                        CLE.angle_mini_start = --CLE.angle_mini_start;
                        CLE.angle_mini_end = CLE.angle_mini_end + 2;
                    }
                    //Анимация  текста  hobby
                    if(i>1135&&i<=1390){
                        CLE.alpha_text_hobby = ++CLE.alpha_text_hobby;
                    }
                    //Анимация точки 2 и 4 согласно  дуге mini
                    if(i>1015&&i<=1135){
                        CLE.angle_point_2 = ++CLE.angle_point_2;
                        CLE.angle_point_4 = --CLE.angle_point_4;
                    }
                    //Анимация  линии 2 и 3
                    if(i>1135&&i<=1235){
                        CLE.line_2 = CLE.line_2 + 1;
                        CLE.line_3 = CLE.line_3 + 1;
                    }
                    //Анимация  текста  app
                    if(i>1235&&i<=1490){
                        CLE.alpha_text_app = ++CLE.alpha_text_app;
                    }
                    //Обновление главного  потока
                    CLE.postInvalidate();
                    break;
                //Анимация  окна my.Knowledge
                case Constants.PLAY_KNOWLEDGE:
                    //Переходная анимация альфа
                    if(i>=300 && i<555){
                        CLK.alpha_transition= --CLK.alpha_transition;
                    }
                    //Анимация  дуги knowledge
                    if(i>555&&i<=915){
                        CLK.p_point.setARGB(255,0,0,0);
                        CLK.angle_knowledge = ++CLK.angle_knowledge;
                        CLK.angle_point_arc = ++CLK.angle_point_arc;
                    }
                    //Анимация основных линий
                    //Анимация линии 1
                    if(i>915&&i<=965){
                        CLK.line_1 = ++CLK.line_1;
                    }
                    //Анимация линии 2
                    if(i>965&&i<=1065){
                        CLK.line_2 = ++CLK.line_2;
                    }
                    //Анимация линии 3
                    if(i>1015&&i<=1115){
                        CLK.line_3 = ++CLK.line_3;
                    }
                    //Анимация линии mini
                    if(i>1115&&i<=1140){
                        CLK.line_m = ++CLK.line_m;
                        CLK.p_point_scale_1.setARGB(255,0,0,0);
                        CLK.line_point_s_1 = ++CLK.line_point_s_1;    //+Точка  на  дуге  scale 1
                    }
                    //Анимация  дуги scale
                    if(i>1140&&i<=1280){
                        s=4;
                        CLK.angle_scale = ++CLK.angle_scale;
                        CLK.angle_point_s_1 = ++CLK.angle_point_s_1;  //Точка  на  дуге  scale 1
                    }
                    //Анимация  точек  scale
                    //Анимация  точки 2 scale
                    if(i>1140&&i<=1157){
                        if(i==1157)CLK.p_point_scale_2.setARGB(255,0,0,0);
                        CLK.angle_point_s_2 = ++CLK.angle_point_s_2;
                    }
                    //Анимация  точки 3 scale
                    if(i>1140&&i<=1192){
                        if(i==1192)CLK.p_point_scale_3.setARGB(255,0,0,0);
                        CLK.angle_point_s_3 = ++CLK.angle_point_s_3;
                    }
                    //Анимация  точки 4 scale
                    if(i>1140&&i<=1227){
                        if(i==1227)CLK.p_point_scale_4.setARGB(255,0,0,0);
                        CLK.angle_point_s_4 = ++CLK.angle_point_s_4;
                    }
                    //Анимация  точки 5 scale
                    if(i>1140&&i<=1262){
                        if(i==1262)CLK.p_point_scale_5.setARGB(255,0,0,0);
                        CLK.angle_point_s_5 = ++CLK.angle_point_s_5;
                    }
                    //Анимация  линий  scale
                    //Анимация линии  scale 1
                    if(i>1157&&i<=1207){
                        CLK.line_scale1 = ++CLK.line_scale1;
                    }
                    //Анимация  линии  scale 2
                    if(i>1192&&i<=1242){
                        CLK.line_scale2 = ++CLK.line_scale2;
                    }
                    //Анимация  линии  scale 3
                    if(i>1227&&i<=1277){
                        CLK.line_scale3 = ++CLK.line_scale3;
                    }
                    //Анимация  линии  scale 4
                    if(i>1262&&i<=1312){
                        CLK.line_scale4 = ++CLK.line_scale4;
                    }
                    //Анимация текста
                    //Анимация текста Java SE
                    if(i>1065&&i<=1320){
                        CLK.alpha_text_java = ++CLK.alpha_text_java;
                    }
                    //Анимация текста Android SDK
                    if(i>1115&&i<=1370){
                        CLK.alpha_text_android = ++CLK.alpha_text_android;
                    }
                    //Анимация текста Junior
                    if(i>1207&&i<=1462){
                        CLK.alpha_text_jun = ++CLK.alpha_text_jun;
                    }
                    //Анимация текста Middle
                    if(i>1242&&i<=1497){
                        CLK.alpha_text_mid = ++CLK.alpha_text_mid;
                    }
                    //Анимация текста Senior
                    if(i>1277&&i<=1532){
                        CLK.alpha_text_sen = ++CLK.alpha_text_sen;
                    }
                    //Анимация текста Guru
                    if(i>1312&&i<=1567){
                        CLK.alpha_text_gur = ++CLK.alpha_text_gur;
                    }
                    //Анимация дуги level
                    if(i>1568&&i<=1598){
                        s=10;
                        CLK.angle_level = ++CLK.angle_level;
                    }
                    if(i>1598&&i<=1605){
                        s=30;
                        CLK.angle_level = --CLK.angle_level;
                    }
                    if(i>1605&&i<=1612){
                        s=50;
                        CLK.angle_level = ++CLK.angle_level;
                        if(i==1612)i=1598;
                    }
                    //Обновление главного  потока
                    CLK.postInvalidate();
                    break;
            }

            try {
                this.sleep(s);
            }catch (Exception e){

            }
        }
    }
    //Завершение  потока
    public void ThreadControl(boolean bool){
        FLOW_CONTROL = bool;
    }
}
