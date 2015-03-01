package ru.resume.myresume;

/**
 * Created by Александр on 27.01.2015.
 */
public class Calculations {
    CustomLayoutController CLC;
    CustomLayoutData CLD;
    CustomLayoutAim CLA;
    CustomLayoutExperience CLE;
    CustomLayoutKnowledge CLK;
    private int PLAY;
    Calculations(CustomLayoutController customLayoutController, int play){
        CLC = customLayoutController;
        PLAY=play;
    }
    Calculations(CustomLayoutData customLayoutData, int play){
        CLD = customLayoutData;
        PLAY=play;
    }
    Calculations(CustomLayoutAim customLayoutAim, int play){
        CLA = customLayoutAim;
        PLAY=play;
    }
    Calculations(CustomLayoutExperience customLayoutExperience, int play){
        CLE = customLayoutExperience;
        PLAY=play;
    }
    Calculations(CustomLayoutKnowledge customLayoutKnowledge, int play){
        CLK = customLayoutKnowledge;
        PLAY=play;
    }
    protected void calculation(){
        switch (PLAY){
            case Constants.PLAY_CONTROLLER:
                //Расчет  координат  сектора 1 радиовышки
                CLC.x_tower_1 = CLC.g.getCordX(CLC.xC,265,CLC.line_tower_1);
                CLC.y_tower_1 = CLC.g.getCordY(CLC.yC,265,CLC.line_tower_1);
                //Расчет  координат  сектора 2 радиовышки
                CLC.x_tower_2 = CLC.g.getCordX(CLC.x_tower_1,265,CLC.line_tower_2);
                CLC.y_tower_2 = CLC.g.getCordY(CLC.y_tower_1,265,CLC.line_tower_2);
                //Расчет  координат  сектора 3 радиовышки
                CLC.x_tower_3 = CLC.g.getCordX(CLC.x_tower_2,265,CLC.line_tower_3);
                CLC.y_tower_3 = CLC.g.getCordY(CLC.y_tower_2,265,CLC.line_tower_3);
                //Расчет  координат  сектора 4 радиовышки
                CLC.x_tower_4 = CLC.g.getCordX(CLC.x_tower_3,265,CLC.line_tower_4);
                CLC.y_tower_4 = CLC.g.getCordY(CLC.y_tower_3,265,CLC.line_tower_4);
                //Расчет  координат  сектора 5 радиовышки
                CLC.x_tower_5 = CLC.g.getCordX(CLC.x_tower_4,265,CLC.line_tower_5);
                CLC.y_tower_5 = CLC.g.getCordY(CLC.y_tower_4,265,CLC.line_tower_5);
                //Расчет  координат дома вышки
                CLC.x_house = CLC.g.getCordX(CLC.xC,265,CLC.line_house);
                CLC.y_house = CLC.g.getCordY(CLC.yC,265,CLC.line_house);
                //Расчет  координат  небоскреба
                CLC.x_skyscraper = CLC.g.getCordX(CLC.xC,230,CLC.line_skyscraper);
                CLC.y_skyscraper = CLC.g.getCordY(CLC.yC,230,CLC.line_skyscraper);
                //Расчет  координат дома справа от небоскреба
                CLC.x_house_r = CLC.g.getCordX(CLC.xC,240,CLC.line_house_r);
                CLC.y_house_r = CLC.g.getCordY(CLC.yC,240,CLC.line_house_r);
                //Расчет  координат дома слева от небоскреба
                CLC.x_house_l = CLC.g.getCordX(CLC.xC,200,CLC.line_house_l);
                CLC.y_house_l = CLC.g.getCordY(CLC.yC,200,CLC.line_house_l);
                //Расчет  координат малой башни справа от вышки
                CLC.x_tower = CLC.g.getCordX(CLC.xC,250,CLC.line_tower);
                CLC.y_tower = CLC.g.getCordY(CLC.yC,250,CLC.line_tower);
                //Расчет  координат нижнего дом
                CLC.x_house_b = CLC.g.getCordX(CLC.xC,190,CLC.line_house_b);
                CLC.y_house_b = CLC.g.getCordY(CLC.yC,190,CLC.line_house_b);
                //Расчет  координат точки адрес
                CLC.x_address = CLC.g.getCordX(CLC.x_tower_5,CLC.angle_address,CLC.line_address);
                CLC.y_address = CLC.g.getCordY(CLC.y_tower_5,CLC.angle_address,CLC.line_address);
                //Расчет  координат точки data
                CLC.x_data = CLC.g.getCordX(CLC.x_address,CLC.angle_data,CLC.line_data);
                CLC.y_data = CLC.g.getCordY(CLC.y_address,CLC.angle_data,CLC.line_data);
                //Расчет  координат точки knowledge
                CLC.x_knowledge = CLC.g.getCordX(CLC.x_address,CLC.angle_knowledge,CLC.line_knowledge);
                CLC.y_knowledge = CLC.g.getCordY(CLC.y_address,CLC.angle_knowledge,CLC.line_knowledge);
                //Расчет  координат точки experience
                CLC.x_experience = CLC.g.getCordX(CLC.x_address,CLC.angle_experience,CLC.line_experience);
                CLC.y_experience = CLC.g.getCordY(CLC.y_address,CLC.angle_experience,CLC.line_experience);
                //Расчет  координат точки aim
                CLC.x_aim = CLC.g.getCordX(CLC.x_address,CLC.angle_aim,CLC.line_aim);
                CLC.y_aim = CLC.g.getCordY(CLC.y_address,CLC.angle_aim,CLC.line_aim);
                break;
            case Constants.PLAY_DATA:
                //Точка  на  дуге
                CLD.x_arc_circle = (CLD.g.getCordX(CLD.xC, CLD.arc_angle, 95))-CLD.line_arc*CLD.c;
                CLD.y_arc_circle = CLD.g.getCordY(CLD.yC, CLD.arc_angle, 95);
                //Линия 1 от  точки  на  дуге
                CLD.x_line_1 = CLD.g.getCordX(CLD.x_arc_circle,0,CLD.line_1);
                CLD.y_line_1 = CLD.g.getCordY(CLD.y_arc_circle,0,CLD.line_1);
                //Линия 2
                CLD.x_line_2 = CLD.g.getCordX(CLD.x_line_1,230,CLD.line_2);
                CLD.y_line_2 = CLD.g.getCordY(CLD.y_line_1,230,CLD.line_2);
                //Линия 3
                CLD.x_line_3 = CLD.g.getCordX(CLD.x_line_1,280,CLD.line_3);
                CLD.y_line_3 = CLD.g.getCordY(CLD.y_line_1,280,CLD.line_3);
                //Линия 4
                CLD.x_line_4 = CLD.g.getCordX(CLD.x_line_1,0,CLD.line_4);
                CLD.y_line_4 = CLD.g.getCordY(CLD.y_line_1,0,CLD.line_4);
                //Линия 5
                CLD.x_line_5 = CLD.g.getCordX(CLD.x_line_1,80,CLD.line_5);
                CLD.y_line_5 = CLD.g.getCordY(CLD.y_line_1,80,CLD.line_5);
                //Линия 6
                CLD.x_line_6 = CLD.g.getCordX(CLD.x_line_1,130,CLD.line_6);
                CLD.y_line_6 = CLD.g.getCordY(CLD.y_line_1,130,CLD.line_6);
                break;
            case Constants.PLAY_AIM:
                //Линии
                //Линия 1
                CLA.x_line1_1 = CLA.g.getCordX(CLA.xC,0,80);
                CLA.y_line1_1 = CLA.g.getCordY(CLA.yC,0,80);
                CLA.x_line1_2 = CLA.g.getCordX(CLA.x_line1_1,0,CLA.line_1);
                CLA.y_line1_2 = CLA.g.getCordY(CLA.y_line1_1,0,CLA.line_1);
                //Линия 2
                CLA.x_line2_1 = CLA.g.getCordX(CLA.xC,90,80);
                CLA.y_line2_1 = CLA.g.getCordY(CLA.yC,90,80);
                CLA.x_line2_2 = CLA.g.getCordX(CLA.x_line2_1,90,CLA.line_2);
                CLA.y_line2_2 = CLA.g.getCordY(CLA.y_line2_1,90,CLA.line_2);
                //Линия 3
                CLA.x_line3_1 = CLA.g.getCordX(CLA.xC,180,80);
                CLA.y_line3_1 = CLA.g.getCordY(CLA.yC,180,80);
                CLA.x_line3_2 = CLA.g.getCordX(CLA.x_line3_1,180,CLA.line_3);
                CLA.y_line3_2 = CLA.g.getCordY(CLA.y_line3_1,180,CLA.line_3);
                //Линия 4
                CLA.x_line4_1 = CLA.g.getCordX(CLA.xC,270,80);
                CLA.y_line4_1 = CLA.g.getCordY(CLA.yC,270,80);
                CLA.x_line4_2 = CLA.g.getCordX(CLA.x_line4_1,270,CLA.line_4);
                CLA.y_line4_2 = CLA.g.getCordY(CLA.y_line4_1,270,CLA.line_4);
                //Линия 5
                CLA.x_line5_1 = CLA.g.getCordX(CLA.xC,315,105);
                CLA.y_line5_1 = CLA.g.getCordY(CLA.yC,315,105);
                CLA.x_line5_2 = CLA.g.getCordX(CLA.x_line5_1,315,CLA.line_5);
                CLA.y_line5_2 = CLA.g.getCordY(CLA.y_line5_1,315,CLA.line_5);
                //Линия 6
                CLA.x_line6 = CLA.g.getCordX(CLA.x_line5_2,0,CLA.line_6);
                CLA.y_line6 = CLA.g.getCordY(CLA.y_line5_2,0,CLA.line_6);

                //Точки
                //Точка 1
                CLA.x_point_1 = CLA.g.getCordX(CLA.xC,275,105);
                CLA.y_point_1 = CLA.g.getCordY(CLA.yC,275,105);
                //Точка 2
                CLA.x_point_2 = CLA.g.getCordX(CLA.xC,275+CLA.angle_mini,105);
                CLA.y_point_2 = CLA.g.getCordY(CLA.yC,275+CLA.angle_mini,105);
                //Точка 3
                CLA.x_point_3 = CLA.g.getCordX(CLA.xC,315,105);
                CLA.y_point_3 = CLA.g.getCordY(CLA.yC,315,105);

                break;
            case Constants.PLAY_EXPERIENCE:
                //Линии
                //Линия 1
                CLE.x_line1_1 = CLE.g.getCordX(CLE.xC,0,95);
                CLE.y_line1_1 = CLE.g.getCordY(CLE.yC,0,95);
                CLE.x_line1_2 = CLE.g.getCordX(CLE.x_line1_1,0,CLE.line_1);
                CLE.y_line1_2 = CLE.g.getCordY(CLE.y_line1_1,0,CLE.line_1);
                //Точка  hobby
                CLE.x_hobby = CLE.g.getCordX(CLE.x_line1_2,0,CLE.line_hobby);
                CLE.y_hobby = CLE.g.getCordY(CLE.y_line1_2,0,CLE.line_hobby);
                //Линия 2
                CLE.x_line2_1 = CLE.g.getCordX(CLE.x_hobby,300,75);
                CLE.y_line2_1 = CLE.g.getCordY(CLE.y_hobby,300,75);
                CLE.x_line2_2 = CLE.g.getCordX(CLE.x_line2_1,300,CLE.line_2);
                CLE.y_line2_2 = CLE.g.getCordY(CLE.y_line2_1,300,CLE.line_2);
                //Линия 3
                CLE.x_line3_1 = CLE.g.getCordX(CLE.x_hobby,60,75);
                CLE.y_line3_1 = CLE.g.getCordY(CLE.y_hobby,60,75);
                CLE.x_line3_2 = CLE.g.getCordX(CLE.x_line3_1,60,CLE.line_3);
                CLE.y_line3_2 = CLE.g.getCordY(CLE.y_line3_1,60,CLE.line_3);

                //Точки
                //Точка 1
                CLE.x_point_1 = CLE.g.getCordX(CLE.xC,CLE.angle_experience,95);
                CLE.y_point_1 = CLE.g.getCordY(CLE.yC,CLE.angle_experience,95);
                //Точка 2
                CLE.x_point_2 = CLE.g.getCordX(CLE.x_hobby,CLE.angle_point_2, 75);
                CLE.y_point_2 = CLE.g.getCordY(CLE.y_hobby,CLE.angle_point_2, 75);
                //Точка 3
                CLE.x_point_3 = CLE.g.getCordX(CLE.x_hobby,CLE.angle_point_2, CLE.line_2 + 75);
                CLE.y_point_3 = CLE.g.getCordY(CLE.y_hobby,CLE.angle_point_2, CLE.line_2 + 75);
                //Точка 4
                CLE.x_point_4 = CLE.g.getCordX(CLE.x_hobby,CLE.angle_point_4, 75);
                CLE.y_point_4 = CLE.g.getCordY(CLE.y_hobby,CLE.angle_point_4, 75);
                //Точка 3
                CLE.x_point_5 = CLE.g.getCordX(CLE.x_hobby,CLE.angle_point_4, CLE.line_3 + 75);
                CLE.y_point_5 = CLE.g.getCordY(CLE.y_hobby,CLE.angle_point_4, CLE.line_3 + 75);

                break;
            case Constants.PLAY_KNOWLEDGE:
                //Точка  на  дуге изображения
                CLK.x_point_arc = CLK.g.getCordX(CLK.xC,CLK.angle_point_arc,95);
                CLK.y_point_arc = CLK.g.getCordY(CLK.yC,CLK.angle_point_arc,95);

                //Линии
                //Линия 1 основная
                CLK.x_line1_1 = CLK.g.getCordX(CLK.x_point_arc,0,0);
                CLK.y_line1_1 = CLK.g.getCordY(CLK.y_point_arc,0,0);
                CLK.x_line1_2 = CLK.g.getCordX(CLK.x_line1_1,0,CLK.line_1);
                CLK.y_line1_2 = CLK.g.getCordY(CLK.y_line1_1,0,CLK.line_1);
                //Линия 2 основная
                CLK.x_line2_1 = CLK.g.getCordX(CLK.x_line1_2,0,0);
                CLK.y_line2_1 = CLK.g.getCordY(CLK.y_line1_2,0,0);
                CLK.x_line2_2 = CLK.g.getCordX(CLK.x_line2_1,300,CLK.line_2);
                CLK.y_line2_2 = CLK.g.getCordY(CLK.y_line2_1,300,CLK.line_2);
                //Линия 3 основная
                CLK.x_line3_1 = CLK.g.getCordX(CLK.x_line1_2,0,0);
                CLK.y_line3_1 = CLK.g.getCordY(CLK.y_line1_2,0,0);
                CLK.x_line3_2 = CLK.g.getCordX(CLK.x_line3_1,60,CLK.line_3);
                CLK.y_line3_2 = CLK.g.getCordY(CLK.y_line3_1,60,CLK.line_3);
                //Линия mini
                CLK.x_line_m_1 = CLK.g.getCordX(CLK.xC,110,95);
                CLK.y_line_m_1 = CLK.g.getCordY(CLK.yC,110,95);
                CLK.x_line_m_2 = CLK.g.getCordX(CLK.x_line_m_1,110,CLK.line_m);
                CLK.y_line_m_2 = CLK.g.getCordY(CLK.y_line_m_1,110,CLK.line_m);
                //Линия scale 1
                CLK.x_line_s1_1 = CLK.g.getCordX(CLK.xC,127,120);
                CLK.y_line_s1_1 = CLK.g.getCordY(CLK.yC,127,120);
                CLK.x_line_s1_2 = CLK.g.getCordX(CLK.x_line_s1_1,127,CLK.line_scale1);
                CLK.y_line_s1_2 = CLK.g.getCordY(CLK.y_line_s1_1,127,CLK.line_scale1);
                //Линия scale 2
                CLK.x_line_s2_1 = CLK.g.getCordX(CLK.xC,162,120);
                CLK.y_line_s2_1 = CLK.g.getCordY(CLK.yC,162,120);
                CLK.x_line_s2_2 = CLK.g.getCordX(CLK.x_line_s2_1,162,CLK.line_scale2);
                CLK.y_line_s2_2 = CLK.g.getCordY(CLK.y_line_s2_1,162,CLK.line_scale2);
                //Линия scale 3
                CLK.x_line_s3_1 = CLK.g.getCordX(CLK.xC,197,120);
                CLK.y_line_s3_1 = CLK.g.getCordY(CLK.yC,197,120);
                CLK.x_line_s3_2 = CLK.g.getCordX(CLK.x_line_s3_1,197,CLK.line_scale3);
                CLK.y_line_s3_2 = CLK.g.getCordY(CLK.y_line_s3_1,197,CLK.line_scale3);
                //Линия scale 4
                CLK.x_line_s4_1 = CLK.g.getCordX(CLK.xC,232,120);
                CLK.y_line_s4_1 = CLK.g.getCordY(CLK.yC,232,120);
                CLK.x_line_s4_2 = CLK.g.getCordX(CLK.x_line_s4_1,232,CLK.line_scale4);
                CLK.y_line_s4_2 = CLK.g.getCordY(CLK.y_line_s4_1,232,CLK.line_scale4);

                //Точки scale на дуге
                //Точка 1
                CLK.x_point_s_1 = CLK.g.getCordX(CLK.xC,CLK.angle_point_s_1,CLK.line_point_s_1);
                CLK.y_point_s_1 = CLK.g.getCordY(CLK.yC,CLK.angle_point_s_1,CLK.line_point_s_1);
                //Точка 2
                CLK.x_point_s_2 = CLK.g.getCordX(CLK.xC,CLK.angle_point_s_2,CLK.line_point_s_1);
                CLK.y_point_s_2 = CLK.g.getCordY(CLK.yC,CLK.angle_point_s_2,CLK.line_point_s_1);
                //Точка 3
                CLK.x_point_s_3 = CLK.g.getCordX(CLK.xC,CLK.angle_point_s_3,CLK.line_point_s_1);
                CLK.y_point_s_3 = CLK.g.getCordY(CLK.yC,CLK.angle_point_s_3,CLK.line_point_s_1);
                //Точка 4
                CLK.x_point_s_4 = CLK.g.getCordX(CLK.xC,CLK.angle_point_s_4,CLK.line_point_s_1);
                CLK.y_point_s_4 = CLK.g.getCordY(CLK.yC,CLK.angle_point_s_4,CLK.line_point_s_1);
                //Точка 4
                CLK.x_point_s_5 = CLK.g.getCordX(CLK.xC,CLK.angle_point_s_5,CLK.line_point_s_1);
                CLK.y_point_s_5 = CLK.g.getCordY(CLK.yC,CLK.angle_point_s_5,CLK.line_point_s_1);

                break;
        }
    }
}
