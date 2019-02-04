package com.example.ayana.chekikkov1.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.R;
import com.example.ayana.chekikkov1.RecyclerPaletteClick;

public class DoodleAdapter extends RecyclerView.Adapter<DoodleAdapter.DoodleViewHolder> {
    private int[] colorPaletteList;
    private Context mContext;
    private RecyclerPaletteClick listener;

    public DoodleAdapter(Context context, int[] paletteItemList, RecyclerPaletteClick listener) {
        this.mContext = context;
        this.colorPaletteList = paletteItemList;
        this.listener = listener;
    }

    public class DoodleViewHolder extends RecyclerView.ViewHolder {
        ImageView palette_iv;

        public DoodleViewHolder(@NonNull View itemView) {
            super(itemView);
            palette_iv = itemView.findViewById(R.id.color_palette);
        }
    }

    @NonNull
    @Override
    public DoodleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.doodle_list_item, viewGroup, false);
        return new DoodleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoodleViewHolder doodleViewHolder, final int i) {
        final int colorPaletteItem = colorPaletteList[i];
        GradientDrawable drawable = (GradientDrawable) doodleViewHolder.palette_iv.getDrawable();
        drawable.setColor(doodleViewHolder.palette_iv.getResources().getColor(colorPaletteItem));

        doodleViewHolder.palette_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onPaletteColorChange(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorPaletteList.length;
    }
}
