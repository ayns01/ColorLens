package com.peenats.ayana.colorlens.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peenats.ayana.colorlens.Adapter.FramesAdapter;
import com.peenats.ayana.colorlens.Material.MaterialsList;
import com.peenats.ayana.colorlens.R;
import com.peenats.ayana.colorlens.RecyclerFrameClick;
import com.peenats.ayana.colorlens.Utils.SpacesItemDecoration;

public class FramesFragment extends Fragment implements RecyclerFrameClick {

    RecyclerView recyclerView;
    FramesAdapter mFramesAdapter;

    MaterialsList mMaterialsList = new MaterialsList();

    private OnFragmentInteractionListener mListener;

    public FramesFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frames, container, false);

        recyclerView = view.findViewById(R.id.frame_recycler_view);

        mFramesAdapter = new FramesAdapter(getActivity(), mMaterialsList.getFramesList(), this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(mFramesAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        mListener.onFramesFragmentInteraction(0);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFrameImageChange(int pos) {
        mListener.onFramesFragmentInteraction(pos);
    }

    public interface OnFragmentInteractionListener {
        void onFramesFragmentInteraction(int pos);
    }
}
