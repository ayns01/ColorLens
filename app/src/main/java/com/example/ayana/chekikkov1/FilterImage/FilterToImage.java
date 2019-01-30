package com.example.ayana.chekikkov1.FilterImage;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;

public class FilterToImage {

    public ColorFilter applySepiaFilter() {

        ColorFilter colorFilter = new LightingColorFilter(Color.WHITE, Color.rgb(255, 64, 129));

        return colorFilter;
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

    public ColorMatrix applyGreenFilter() {

        float[] blue_colour_matrix = {
                1, 0, 0, 0, 0,
                0, 2, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(blue_colour_matrix);
    }

    public ColorMatrix applyBlueFilter() {

        float[] yellow_colour_matrix = {
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 2, 0, 0,
                0, 0, 0, 1, 0
        };

        return new ColorMatrix(yellow_colour_matrix);
    }


}
