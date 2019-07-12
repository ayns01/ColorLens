package com.peenats.ayana.colorlens.Material;

import android.graphics.ColorMatrix;

import com.peenats.ayana.colorlens.FilterImage.FilterToImage;
import com.peenats.ayana.colorlens.R;

public class MaterialsList {

    private FilterToImage mFilterToImage = new FilterToImage();
    private ColorMatrix[] colorFiltersList = {mFilterToImage.defaultFilter(),
            mFilterToImage.applyRedFilter(), mFilterToImage.applyOrangeFilter(),
            mFilterToImage.applyBlueFilter(), mFilterToImage.applyGreenFilter(),
            mFilterToImage.applyPurpleFilter(), mFilterToImage.applyWhiteFilter(),
            mFilterToImage.applyYellowFilter()};

    private int[] colorFiltersImageList = {R.drawable.colorrens, R.drawable.colorrens,
            R.drawable.colorrens, R.drawable.colorrens, R.drawable.colorrens, R.drawable.colorrens,
            R.drawable.colorrens, R.drawable.colorrens};

    private int[] framesList = {R.drawable.frame_white, R.drawable.frame_black,
            R.drawable.frame_yellow, R.drawable.frame_paleblue, R.drawable.frame_palepink,
            R.drawable.frame_orange, R.drawable.frame_pappermint, R.drawable.frame_tiffanyblue,
            R.drawable.frame_gra_yellowblue,
            R.drawable.frame_gra_redgreen, R.drawable.frame_gra_peach,
            R.drawable.frame_drop, R.drawable.frame_star};

    private int[] framesForSaveList = {R.drawable.frame_white_save, R.drawable.frame_black_save,
            R.drawable.frame_yellow_save, R.drawable.frame_paleblue_save, R.drawable.frame_palepink_save,
            R.drawable.frame_orange_save, R.drawable.frame_pappermint_save, R.drawable.frame_tiffanyblue_save,
            R.drawable.frame_gra_yellowblue_save,
            R.drawable.frame_gra_redgreen_save, R.drawable.frame_gra_peach_save,
            R.drawable.frame_drop_save, R.drawable.frame_star_save};

    private int[] paletteList = {R.color.black, R.color.gold, R.color.pastel_blue, R.color.lavender_gray,
            R.color.queen_pink, R.color.orange_yellow, R.color.white,
            R.color.deep_moss_green, R.color.deep_peach, R.color.deep_pink, R.color.maastricht_blue,
            R.color.deep_puce, R.color.deep_carmine_pink, R.color.deep_lilac, R.color.aero_blue,
            R.color.sea_serpent};

    private int[] rList = {16, 212, 160, 190, 248, 249, 255, 53, 255, 255, 0, 169, 239, 153, 201, 75};
    private int[] gList = {16, 175, 195, 190, 205, 200, 255, 94, 203, 20, 28, 92, 48, 85, 255, 199};
    private int[] bList = {16, 55, 210, 209, 210, 99, 255, 59, 164, 147, 61, 104, 56, 187, 225, 207};

    public ColorMatrix getColorFilter(int pos) {
        return colorFiltersList[pos];
    }

    public int getFrame(int pos) {
        return framesList[pos];
    }

    public int getFrameForSave(int pos) {
        return framesForSaveList[pos];
    }

    public int[] getFramesList() { return framesList; }

    public int[] getColorFiltersImageList() { return colorFiltersImageList; }

    public int[] getPaletteList() { return paletteList; }

    public int getR(int pos) {
        return rList[pos];
    }

    public int getG(int pos) {
        return gList[pos];
    }

    public int getB(int pos) {
        return bList[pos];
    }
}
