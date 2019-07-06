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
}
