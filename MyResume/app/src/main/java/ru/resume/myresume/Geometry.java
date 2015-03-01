package ru.resume.myresume;

/**
 * Created by Александр on 23.01.2015.
 */
public class Geometry {
    float x;
    float y;
    //Метод  возврощающий координату X конечной точки вектора
    //по  координате X начальной точки вектора, углу в 2D
    //плоскости и длине вектора
    protected float getCordX(float xC, float rad, float radius){
        return x = (float) (xC  + (Math.cos(-rad * Math.PI/180) * (radius*CoefficientScreen.getC())));
    }
    //Метод  возврощающий координату Y конечной точки вектора
    //по  координате Y начальной точки вектора, углу в 2D
    //плоскости и длине вектора
    protected float getCordY(float yC, float rad, float radius){
        return y = (float) (yC  + (Math.sin(rad * Math.PI/180) * (radius*CoefficientScreen.getC())));
    }
}
