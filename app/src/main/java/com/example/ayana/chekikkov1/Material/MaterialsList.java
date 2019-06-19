package com.example.ayana.chekikkov1.Material;

import com.example.ayana.chekikkov1.R;

public class MaterialsList {

        private int[] colorFiltersList = {R.drawable.colorrens, R.drawable.colorrens,
                R.drawable.colorrens, R.drawable.colorrens, R.drawable.colorrens, R.drawable.colorrens,
                R.drawable.colorrens, R.drawable.colorrens};

        private int[] framesList = {R.drawable.frame_white, R.drawable.frame_black ,
                R.drawable.frame_yellow, R.drawable.frame_pink, R.drawable.frame_paleblue,
                R.drawable.frame_turquoise, R.drawable.frame_orange, R.drawable.frame_green,
                R.drawable.frame_vividyellow, R.drawable.frame_mix_papermint,
                R.drawable.frame_mix_purple, R.drawable.frame_mix_red, R.drawable.frame_mix_yellow,
                R.drawable.frame_mix_toy, R.drawable.frame_mix_wheat, R.drawable.frame_drop,
                R.drawable.frame_star};

        private int[] paletteList = {R.color.black, R.color.gold, R.color.pastel_blue, R.color.lavender_gray,
                R.color.queen_pink, R.color.orange_yellow, R.color.white,
                R.color.deep_moss_green, R.color.deep_peach, R.color.deep_pink, R.color.maastricht_blue,
                R.color.deep_puce, R.color.deep_carmine_pink, R.color.deep_lilac, R.color.aero_blue,
                R.color.sea_serpent};

        private int[] rList = {16, 212, 160, 190, 248, 249, 255, 53, 255, 255, 0, 169, 239, 153, 201, 75};
        private int[] gList = {16, 175, 195, 190, 205, 200, 255, 94, 203, 20, 28, 92, 48, 85, 255, 199};
        private int[] bList = {16, 55, 210, 209, 210, 99, 255, 59, 164, 147, 61, 104, 56, 187, 225, 207};

        public int getColorFilter(int pos) {
            return colorFiltersList[pos];
        }

        public int getFrame(int pos) {
            return framesList[pos];
        }

        public int getPalette(int pos) {
            return paletteList[pos];
        }

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
