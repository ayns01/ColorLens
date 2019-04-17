package com.example.ayana.chekikkov1;

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
import android.widget.ImageButton;

import com.example.ayana.chekikkov1.Adapter.DoodleAdapter;
import com.example.ayana.chekikkov1.Utils.SpacesItemDecoration;

public class DoodleFragment extends Fragment implements RecyclerPaletteClick{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    DoodleAdapter mDoodleAdapter;
    ImageButton undoButton;
    ImageButton pen1;
    ImageButton pen2;

    int[] paletteList = {R.color.black, R.color.gold, R.color.pastel_blue, R.color.lavender_gray,
            R.color.queen_pink, R.color.orange_yellow, R.color.white,
            R.color.deep_moss_green, R.color.deep_peach, R.color.deep_pink, R.color.maastricht_blue,
            R.color.deep_puce, R.color.deep_carmine_pink, R.color.deep_lilac, R.color.aero_blue,
            R.color.sea_serpent};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private OnFragmentUndoListener mUndoListener;
    private OnFragmentDefaultPenListener mDefaultPenListener;
    private OnFragmentPoscaPenListener mPoscaPenListener;

    public DoodleFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoodleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoodleFragment newInstance(String param1, String param2) {
        DoodleFragment fragment = new DoodleFragment();
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
        View view = inflater.inflate(R.layout.fragment_doodle, container, false);

        recyclerView = view.findViewById(R.id.doodle_recycler_view);

        // Click Undo Button
        undoButton = view.findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUndoListener.onDoodleFragmentUndoInteraction();
            }
        });

        // Click default pen
        pen1 = view.findViewById(R.id.pen1);
        pen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pen1.setImageResource(R.drawable.pen1_on);
                pen2.setImageResource(R.drawable.pen2_off);
                mDefaultPenListener.onDoodleFragmentDefaultPenInteraction();
            }
        });

        // Click posca pen
        pen2 = view.findViewById(R.id.pen2);
        pen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pen1.setImageResource(R.drawable.pen1_off);
                pen2.setImageResource(R.drawable.pen2_on);
                mPoscaPenListener.onDoodleFragmentPoscaPenInteraction();
            }
        });

        mDoodleAdapter = new DoodleAdapter(getActivity(), paletteList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
                getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(mDoodleAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int pos) {
        if (mListener != null) {
            mListener.onDoodleFragmentInteraction(pos);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        if (context instanceof OnFragmentUndoListener) {
            mUndoListener = (OnFragmentUndoListener) context;
        }
        if (context instanceof OnFragmentDefaultPenListener) {
            mDefaultPenListener = (OnFragmentDefaultPenListener) context;
        }
        if (context instanceof OnFragmentPoscaPenListener) {
            mPoscaPenListener = (OnFragmentPoscaPenListener) context;
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
    public void onPaletteColorChange(int pos) {
        mListener.onDoodleFragmentInteraction(pos);
    }

    public interface OnFragmentUndoListener {
        void onDoodleFragmentUndoInteraction();
    }

    public interface OnFragmentInteractionListener {
        void onDoodleFragmentInteraction(int pos);
    }

    public interface OnFragmentDefaultPenListener {
        void onDoodleFragmentDefaultPenInteraction();
    }

    public interface OnFragmentPoscaPenListener {
        void onDoodleFragmentPoscaPenInteraction();
    }
}
