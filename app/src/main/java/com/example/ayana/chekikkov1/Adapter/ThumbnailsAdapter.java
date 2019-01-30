package com.example.ayana.chekikkov1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
        ImageView colorThumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            colorThumbnail = itemView.findViewById(R.id.colorThumbnail);
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

        myViewHolder.colorThumbnail.setImageResource(thumbnailItem);

        myViewHolder.colorThumbnail.setOnClickListener(new View.OnClickListener() {
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
