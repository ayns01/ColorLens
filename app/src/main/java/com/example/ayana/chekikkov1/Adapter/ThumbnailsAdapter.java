package com.example.ayana.chekikkov1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.FilterImage.FilterToImage;
import com.example.ayana.chekikkov1.R;
import com.example.ayana.chekikkov1.RecyclerImageClick;

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.MyViewHolder> {
    private int[] thumbnailItemList;
    private Context mContext;
    private RecyclerImageClick listener;

    public ThumbnailsAdapter(Context context, int[] thumbnailItemList, RecyclerImageClick listener) {
        this.mContext = context;
        this.thumbnailItemList = thumbnailItemList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView colorThumbnail_iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            colorThumbnail_iv = itemView.findViewById(R.id.colorThumbnail);
        }
    }

    @NonNull
    @Override
    public ThumbnailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.color_thumbnail_list_item, viewGroup, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ThumbnailsAdapter.MyViewHolder myViewHolder,final int i) {
        final int thumbnailItem = thumbnailItemList[i];

        switch (i) {
            case 1:
                ColorMatrix redMatrix = new FilterToImage().applyRedFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
                break;

            case 2:
                ColorMatrix orangeMatrix = new FilterToImage().applyOrangeFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(orangeMatrix)));
                break;

            case 3:
                ColorMatrix blueMatrix = new FilterToImage().applyBlueFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                break;

            case 4:
                ColorMatrix greenMatrix = new FilterToImage().applyGreenFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                break;
            case 5:
                ColorMatrix purpleMatrix = new FilterToImage().applyPurpleFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(purpleMatrix)));
//                ColorFilter duoChinaColor = new FilterToImage().duotoneColorFilter(Color.BLACK, Color.WHITE, 1.3f);
//                myViewHolder.colorThumbnail_iv.setColorFilter(duoChinaColor);
                break;
            case 6:
                ColorMatrix whiteMatrix = new FilterToImage().applyWhiteFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(whiteMatrix)));
                break;
            case 7:
                ColorMatrix yellowMatrix = new FilterToImage().applyYellowFilter();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(yellowMatrix)));
                break;

            default:
                ColorMatrix originalMatrix = new FilterToImage().backToOriginal();
                myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(originalMatrix)));
        }

        myViewHolder.colorThumbnail_iv.setImageResource(thumbnailItem);

        myViewHolder.colorThumbnail_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onCenterImageChange(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return thumbnailItemList.length;
    }
}
