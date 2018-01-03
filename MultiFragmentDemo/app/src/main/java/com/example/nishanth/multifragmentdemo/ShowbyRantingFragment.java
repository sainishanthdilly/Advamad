package com.example.nishanth.multifragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowbyRantingFragment.OnFragmentRatingInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowbyRantingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowbyRantingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button bf,bp,bn,bl,finish;
    TextView title,gen,rating,yr,imdb;
    EditText Desc;
    int i=0;
    ArrayList<FavoriteMovie> ly;
    private OnFragmentRatingInteractionListener mListener;

    public ShowbyRantingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowbyRantingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowbyRantingFragment newInstance(String param1, String param2) {
        ShowbyRantingFragment fragment = new ShowbyRantingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setLisDataR(ArrayList<FavoriteMovie> lisDataR) {
        ly = lisDataR;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_showby_ranting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




            title = (TextView) getActivity().findViewById(R.id.r_textViewRTitle);
            Desc = (EditText) getActivity().findViewById(R.id.r_editTextRResult);
            gen = (TextView) getActivity().findViewById(R.id.r_textViewRGenre);
            rating = (TextView) getActivity().findViewById(R.id.r_textViewRRating);
            yr = (TextView) getActivity().findViewById(R.id.r_textViewRYear);
            imdb = (TextView) getActivity().findViewById(R.id.r_textViewRMDB);

            bp = (Button) getActivity().findViewById(R.id.r_buttonPrevious);
            bn = (Button) getActivity().findViewById(R.id.r_buttonNext);
            bl = (Button) getActivity().findViewById(R.id.r_buttonLast);

            bf = (Button) getActivity().findViewById(R.id.r_buttonFirst);
            finish = (Button) getActivity().findViewById(R.id.r_buttonFinish);

            FavoriteMovie mv = ly.get(0);
            i = 0;
            title.setText(mv.movieName);
            Desc.setText(mv.descr);
            gen.setText(mv.genre);
            rating.setText(mv.rating + "/5");
            yr.setText(mv.year);
            imdb.setText(mv.IMDB);

            bf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   Toast.makeText(getApplicationContext(),"Clickedf",Toast.LENGTH_SHORT).show();
                    FavoriteMovie mv = ly.get(0);
                    i = 0;
                    title.setText(mv.movieName);
                    Desc.setText(mv.descr);
                    gen.setText(mv.genre);
                    rating.setText(mv.rating + "/5");
                    yr.setText(mv.year);
                    imdb.setText(mv.IMDB);

                }
            });

            bp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("click","Prev");
                    Toast.makeText(getActivity(),"Clickedp",Toast.LENGTH_SHORT).show();

                    if (i != 0) {
                        FavoriteMovie mv = ly.get(--i);

                        title.setText(mv.movieName);
                        Desc.setText(mv.descr);
                        gen.setText(mv.genre);
                        rating.setText(mv.rating + "/5");
                        yr.setText(mv.year);
                        imdb.setText(mv.IMDB);
                    }


                }
            });

            bn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(getApplicationContext(),"Clickedn",Toast.LENGTH_SHORT).show();

                    if (i < (ly.size()-1)) {
                        i++;
                        FavoriteMovie mv = ly.get(i);

                        title.setText(mv.movieName);
                        Desc.setText(mv.descr);
                        gen.setText(mv.genre);
                        rating.setText(mv.rating + "/5");
                        yr.setText(mv.year);
                        imdb.setText(mv.IMDB);
                    }


                }
            });

            bl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(),"Clickedl",Toast.LENGTH_SHORT).show();

                    if (i < (ly.size()-1) ){
                        i = ly.size() - 1;
                        FavoriteMovie mv = ly.get(i);

                        title.setText(mv.movieName);
                        Desc.setText(mv.descr);
                        gen.setText(mv.genre);
                        rating.setText(mv.rating + "/5");
                        yr.setText(mv.year);
                        imdb.setText(mv.IMDB);

                    }
                }


            });


            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentShwRatingInteraction();
                }
            });






    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentRatingInteractionListener) {
            mListener = (OnFragmentRatingInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentRatingInteractionListener {
        // TODO: Update argument type and name
        void onFragmentShwRatingInteraction();
    }
}
