package com.peenats.ayana.colorlens.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.peenats.ayana.colorlens.Material.MaterialsList;
import com.peenats.ayana.colorlens.R;
import com.peenats.ayana.colorlens.RecyclerImageClick;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.MyViewHolder> {
    private int[] thumbnailItemList;
    private Context mContext;
    private RecyclerImageClick listener;

    public ColorsAdapter(Context context, int[] thumbnailItemList, RecyclerImageClick listener) {
        this.mContext = context;
        this.thumbnailItemList = thumbnailItemList;
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView colorThumbnail_iv;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            colorThumbnail_iv = itemView.findViewById(R.id.colorThumbnail);
        }
    }

    @NonNull
    @Override
    public ColorsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.color_thumbnail_list_item, viewGroup, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ColorsAdapter.MyViewHolder myViewHolder,
                                 @SuppressLint("RecyclerView") final int i) {

        MaterialsList materialsList = new MaterialsList();
        myViewHolder.colorThumbnail_iv.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(materialsList.getColorFilter(i))));

        final int thumbnailItem = thumbnailItemList[i];
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
