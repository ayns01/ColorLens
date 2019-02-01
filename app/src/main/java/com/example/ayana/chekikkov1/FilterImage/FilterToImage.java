package com.example.ayana.chekikkov1.FilterImage;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.support.annotation.ColorInt;

public class FilterToImage {

    public ColorMatrix backToOriginal() {
        float[] original_colour_matrix = {
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(original_colour_matrix);
    }

    public ColorMatrix applyRedFilter() {

        float[] red_colour_matrix = {
                2, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(red_colour_matrix);
    }

    public ColorMatrix applyOrangeFilter() {

        float[] orange_colour_matrix = {
                2, 0, 0, 0, 0,
                0, 1.3f, 0, 0, 0,
                0, 0, 0.7f, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(orange_colour_matrix);
    }

    public ColorMatrix applyBlueFilter() {

        float[] blue_colour_matrix = {
                0.5f, 0, 0, 0, 0,
                0, 1.3f, 0, 0, 0,
                0, 0, 2, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(blue_colour_matrix);
    }

    public ColorFilter applyLightingFilter() {

        ColorFilter colorFilter = new LightingColorFilter(Color.rgb(255, 64, 129), Color.rgb(255, 64, 129));

        return colorFilter;
    }


    public ColorFilter duotoneColorFilter(@ColorInt int colorBlack, @ColorInt int colorWhite, float contrast) {
        ColorMatrix cm = new ColorMatrix();

        ColorMatrix cmBlackWhite = new ColorMatrix();
        float lumR = 0.2125f;
        float lumG = 0.7154f;
        float lumB = 0.0721f;
        float[] blackWhiteArray = new float[]{
                lumR, lumG, lumB, 0, 0,
                lumR, lumG, lumB, 0, 0,
                lumR, lumG, lumB, 0, 0,
                0, 0, 0, 1, 0};
        cmBlackWhite.set(blackWhiteArray);

        ColorMatrix cmContrast = new ColorMatrix();
        float scale = contrast + 1.0f;
        float translate = (-0.5f * scale + 0.5f) * 255f;
        float[] contrastArray = new float[]{
                scale, 0, 0, 0, translate,
                0, scale, 0, 0, translate,
                0, 0, scale, 0, translate,
                0, 0, 0, 1, 0};
        cmContrast.set(contrastArray);

        ColorMatrix cmDuoTone = new ColorMatrix();
        float r1 = Color.red(colorWhite);
        float g1 = Color.green(colorWhite);
        float b1 = Color.blue(colorWhite);
        float r2 = Color.red(colorBlack);
        float g2 = Color.green(colorBlack);
        float b2 = Color.blue(colorBlack);
        float r1r2 = (r1 - r2) / 255f;
        float g1g2 = (g1 - g2) / 255f;
        float b1b2 = (b1 - b2) / 255f;
        float[] duoToneArray = new float[]{
                r1r2, 0, 0, 0, r2,
                g1g2, 0, 0, 0, g2,
                b1b2, 0, 0, 0, b2,
                0, 0, 0, 1, 0};
        cmDuoTone.set(duoToneArray);

        cm.postConcat(cmBlackWhite);
        cm.postConcat(cmContrast);
        cm.postConcat(cmDuoTone);

        return new ColorMatrixColorFilter(cm);
    }


}