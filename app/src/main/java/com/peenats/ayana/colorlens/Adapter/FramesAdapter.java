package com.peenats.ayana.colorlens.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.peenats.ayana.colorlens.R;
import com.peenats.ayana.colorlens.RecyclerFrameClick;

public class FramesAdapter extends RecyclerView.Adapter<FramesAdapter.MyViewHolder> {
    private int[] frameItemList;
    private Context mContext;
    private RecyclerFrameClick listener;

    public FramesAdapter(Context context, int[] frameItemList, RecyclerFrameClick listener) {
        this.mContext = context;
        this.frameItemList = frameItemList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView frame_iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            frame_iv = itemView.findViewById(R.id.photo_frame);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frame_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final int frameItem = frameItemList[i];

        myViewHolder.frame_iv.setImageResource(frameItem);

        myViewHolder.frame_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onFrameImageChange(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return frameItemList.length;
    }


}
