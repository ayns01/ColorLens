package com.example.ayana.chekikkov1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ayana.chekikkov1.Adapter.FramesAdapter;
import com.example.ayana.chekikkov1.Adapter.ThumbnailsAdapter;
import com.example.ayana.chekikkov1.Utils.SpacesItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FramesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FramesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FramesFragment extends Fragment implements RecyclerFrameThumbnailClick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    FramesAdapter mFramesAdapter;

    int[] framesList = {R.drawable.frame_2x, R.drawable.frame_black_2x,
            R.drawable.street, R.drawable.woman, R.drawable.upface, R.drawable.backstreetboy};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FramesFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FramesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FramesFragment newInstance(String param1, String param2) {
        FramesFragment fragment = new FramesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frames, container, false);

        recyclerView = view.findViewById(R.id.frame_recycler_view);

        mFramesAdapter = new FramesAdapter(getActivity(), framesList, this);

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int pos) {
        if (mListener != null) {
            mListener.onFramesFragmentInteraction(pos);
        }
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
        mListener.onFramesFragmentInteraction(1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFrameImageChange(int pos) {
//        Toast.makeText(getContext(), pos, Toast.LENGTH_SHORT).show();
        mListener.onFramesFragmentInteraction(pos);
    }

    public interface OnFragmentInteractionListener {
        void onFramesFragmentInteraction(int pos);
    }
}
