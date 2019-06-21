package com.example.ayana.chekikkov1.FilterImage;

import android.graphics.ColorMatrix;

public class FilterToImage {

    public ColorMatrix defaultFilter() {

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(1.1f);

        return colorMatrix;
    }

    public ColorMatrix applyRedFilter() {

        float[] red_colour_matrix = {
                3f, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(red_colour_matrix);
    }

    public ColorMatrix applyOrangeFilter() {

        float[] orange_colour_matrix = {
                2.4f, 0, 0, 0, 0,
                0, 1.3f, 0, 0, 0,
                0, 0, 0.5f, 0, 0,
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

    public ColorMatrix applyGreenFilter() {

        float[] green_colour_matrix = {
                0.5f, 0, 0, 0, 0,
                0, 1.3f, 0, 0, 0,
                0, 0, 0.9f, 0, 0,
                0, 0, 0, 1f, 0
        };

        return new ColorMatrix(green_colour_matrix);
    }

    public ColorMatrix applyPurpleFilter() {

        float[] purple_colour_matrix = {
                1.3f, 0, 0, 0, 0,
                0, 0.7f, 0, 0, 0,
                0, 0, 1.8f, 0, 0,
                0, 0, 0, 1f, 0
        };

        return new ColorMatrix(purple_colour_matrix);
    }

    public ColorMatrix applyWhiteFilter() {

        float[] white_colour_matrix = {
                1.4f, 0, 0, 0, 0,
                0, 1.4f, 0, 0, 0,
                0, 0, 1.4f, 0, 0,
                0, 0, 0, 1f, 0
        };

        return new ColorMatrix(white_colour_matrix);
    }

    public ColorMatrix applyYellowFilter() {

        float[] yellow_colour_matrix = {
                1.4f, 0, 0, 0, 0,
                0, 1.4f, 0, 0, 0,
                0, 0, 0.7f, 0, 0,
                0, 0, 0, 1f, 0
        };

        return new ColorMatrix(yellow_colour_matrix);
    }

//    public ColorFilter applyLightingFilter() {
//
//        ColorFilter colorFilter = new LightingColorFilter(Color.rgb(255, 64, 129), Color.rgb(255, 64, 129));
//
//        return colorFilter;
//    }

//    public ColorFilter duotoneColorFilter(@ColorInt int colorBlack, @ColorInt int colorWhite, float contrast) {
//        ColorMatrix cm = new ColorMatrix();
//
//        ColorMatrix cmBlackWhite = new ColorMatrix();
//        float lumR = 0.2125f;
//        float lumG = 0.7154f;
//        float lumB = 0.0721f;
//        float[] blackWhiteArray = new float[]{
//                lumR, lumG, lumB, 0, 0,
//                lumR, lumG, lumB, 0, 0,
//                lumR, lumG, lumB, 0, 0,
//                0, 0, 0, 1, 0};
//        cmBlackWhite.set(blackWhiteArray);
//
//        ColorMatrix cmContrast = new ColorMatrix();
//        float scale = contrast + 1.0f;
//        float translate = (-0.5f * scale + 0.5f) * 255f;
//        float[] contrastArray = new float[]{
//                scale, 0, 0, 0, translate,
//                0, scale, 0, 0, translate,
//                0, 0, scale, 0, translate,
//                0, 0, 0, 1, 0};
//        cmContrast.set(contrastArray);
//
//        ColorMatrix cmDuoTone = new ColorMatrix();
//        float r1 = Color.red(colorWhite);
//        float g1 = Color.green(colorWhite);
//        float b1 = Color.blue(colorWhite);
//        float r2 = Color.red(colorBlack);
//        float g2 = Color.green(colorBlack);
//        float b2 = Color.blue(colorBlack);
//        float r1r2 = (r1 - r2) / 255f;
//        float g1g2 = (g1 - g2) / 255f;
//        float b1b2 = (b1 - b2) / 255f;
//        float[] duoToneArray = new float[]{
//                r1r2, 0, 0, 0, r2,
//                g1g2, 0, 0, 0, g2,
//                b1b2, 0, 0, 0, b2,
//                0, 0, 0, 1, 0};
//        cmDuoTone.set(duoToneArray);
//
//        cm.postConcat(cmBlackWhite);
//        cm.postConcat(cmContrast);
//        cm.postConcat(cmDuoTone);
//
//        return new ColorMatrixColorFilter(cm);
//    }

}
