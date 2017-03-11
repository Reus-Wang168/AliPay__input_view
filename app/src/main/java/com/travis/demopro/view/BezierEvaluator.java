package com.travis.demopro.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import java.lang.reflect.Type;

/**
 * Created by Tenney on 2017/3/7.
 * QQ 1021534072
 * email wjky11111@163.com
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF point1, point2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.point1 = pointF1;
        this.point2 = pointF2;
    }
    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        // t百分比， 0~1
        PointF point = new PointF();
        point.x = startValue.x * (1 - t) * (1 - t) * (1 - t) //
                + 3 * point1.x * t * (1 - t) * (1 - t)//
                + 3 * point2.x * t * t * (1 - t)//
                + endValue.x * t * t * t;//

        point.y = startValue.y * (1 - t) * (1 - t) * (1 - t) //
                + 3 * point1.y * t * (1 - t) * (1 - t)//
                + 3 * point2.y * t * t * (1 - t)//
                +endValue.y * t * t * t;//
        // 套用上面的公式把点返回
        return point;
    }
}






