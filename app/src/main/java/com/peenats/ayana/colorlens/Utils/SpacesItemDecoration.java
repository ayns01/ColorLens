package com.peenats.ayana.colorlens.Utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }


    /**
     * @param outRect Rect to receive the output.
     * @param view The child view to decorate.
     * @param parent RecyclerView this ItemDecoration is decorating.
     * @param state The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            outRect.left = 0;
            outRect.right = 0;
        }else if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = space * 2;
            outRect.right = space;
        }else {
            outRect.right = space;
            outRect.left = 0;
        }
    }

}
