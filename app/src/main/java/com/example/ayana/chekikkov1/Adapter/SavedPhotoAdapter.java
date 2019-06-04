package com.example.ayana.chekikkov1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayana.chekikkov1.R;
import com.example.ayana.chekikkov1.SavedPhoto;

import java.util.List;

public class SavedPhotoAdapter extends RecyclerView.Adapter<SavedPhotoAdapter.MyViewHolder>{

    private Context mContext;
    private List<SavedPhoto> savedPhotoList;

    public SavedPhotoAdapter(Context mContext, List<SavedPhoto> savedPhotoList) {
        this.mContext = mContext;
        this.savedPhotoList = savedPhotoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_card, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final SavedPhoto savedPhoto = savedPhotoList.get(i);
        myViewHolder.savedDate.setText(savedPhoto.getSavedDate());

        myViewHolder.savedPhoto.setImageBitmap(savedPhoto.getSavedPhoto());


        myViewHolder.savedPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked savedPhoto!",
                        Toast.LENGTH_LONG).show();
                showPopupMenu(myViewHolder.savedPhoto);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_history, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_resave_photo:
                    Toast.makeText(mContext, "Resave this photo", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete_photo:
                    Toast.makeText(mContext, "Delete this photo", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return savedPhotoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView savedDate;
        public ImageView savedPhoto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            savedDate = itemView.findViewById(R.id.saved_date);
            savedPhoto = itemView.findViewById(R.id.saved_photo);
        }
    }
}
