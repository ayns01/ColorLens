package com.example.ayana.chekikkov1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.R;

import java.util.List;

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.MyViewHolder> {
    private int[] thumbnailItemList;
    private Context mContext;

    public ThumbnailsAdapter(Context context, int[] thumbnailItemList) {
        this.mContext = context;
        this.thumbnailItemList = thumbnailItemList;

    }

    @NonNull
    @Override
    public ThumbnailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.color_thumbnail_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailsAdapter.MyViewHolder myViewHolder, int i) {
        final int thumbnailItem = thumbnailItemList[i];

        myViewHolder.colorThumbnail.setImageResource(thumbnailItem);

    }

    @Override
    public int getItemCount() {
        return thumbnailItemList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView colorThumbnail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             colorThumbnail = itemView.findViewById(R.id.colorThumbnail);
        }
    }
}
