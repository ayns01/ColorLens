package com.example.ayana.chekikkov1.FilterImage;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;

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


}
