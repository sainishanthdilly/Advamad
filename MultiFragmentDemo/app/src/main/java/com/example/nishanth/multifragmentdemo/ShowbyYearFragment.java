package com.example.nishanth.multifragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowbyYearFragment.OnFragmentYearInteractionListener } interface
 * to handle interaction events.
 */
public class ShowbyYearFragment extends Fragment {

    Button bf,bp,bn,bl,finish;
    TextView title,gen,rating,yr,imdb;
    EditText Desc;
    int i=0;
    ArrayList<FavoriteMovie> ly;

    private OnFragmentYearInteractionListener  mListener;

    public ShowbyYearFragment() {
        // Required empty public constructor
    }

    public void setLisData(ArrayList<FavoriteMovie> l){
        ly=l;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_showby_year, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {


                title = (TextView) getActivity().findViewById(R.id.y_textViewRTitle);
                Desc = (EditText) getActivity().findViewById(R.id.y_editTextRResult);
                gen = (TextView) getActivity().findViewById(R.id.y_textViewRGenre);
                rating = (TextView) getActivity().findViewById(R.id.y_textViewRRating);
                yr = (TextView) getActivity().findViewById(R.id.y_textViewRYear);
                imdb = (TextView) getActivity().findViewById(R.id.y_textViewRMDB);

                bp = (Button) getActivity().findViewById(R.id.y_buttonPrevious);
                bn = (Button) getActivity().findViewById(R.id.y_buttonNext);
                bl = (Button) getActivity().findViewById(R.id.y_buttonLast);

                bf = (Button) getActivity().findViewById(R.id.y_buttonFirst);
                finish = (Button) getActivity().findViewById(R.id.y_buttonFinish);

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
                        // Toast.makeText(getApplicationContext(),"Clickedf",Toast.LENGTH_SHORT).show();
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
                        //  Log.d("click","Prev");
                        // Toast.makeText(getApplicationContext(),"Clickedp",Toast.LENGTH_SHORT).show();

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
                        mListener.onFragmentShwYearInteraction();
                    }
                });

        }catch (Exception e){
            e.printStackTrace();
        }





    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentYearInteractionListener ) {
            mListener = (OnFragmentYearInteractionListener ) activity;
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
    public interface OnFragmentYearInteractionListener {
        // TODO: Update argument type and name
        void onFragmentShwYearInteraction();
    }
}
