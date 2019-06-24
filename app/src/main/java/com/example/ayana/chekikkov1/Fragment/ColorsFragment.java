package com.example.ayana.chekikkov1.Fragment;

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

import com.example.ayana.chekikkov1.Adapter.ThumbnailsAdapter;
import com.example.ayana.chekikkov1.Material.MaterialsList;
import com.example.ayana.chekikkov1.R;
import com.example.ayana.chekikkov1.RecyclerImageClick;
import com.example.ayana.chekikkov1.Utils.SpacesItemDecoration;


public class ColorsFragment extends Fragment implements RecyclerImageClick {

    RecyclerView recyclerView;
    ThumbnailsAdapter mThumbnailsAdapter;

    MaterialsList mMaterialsList = new MaterialsList();


    private OnFragmentInteractionListener mListener;

    public ColorsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colors, container, false);

        recyclerView = view.findViewById(R.id.color_thumbnail_recycler_view);

        mThumbnailsAdapter = new ThumbnailsAdapter(getActivity(), mMaterialsList.getColorFiltersImageList(), this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(mThumbnailsAdapter);

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCenterImageChange(int pos) {
        mListener.onFragmentInteraction(pos);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int filter);
    }
}
