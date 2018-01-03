package com.example.nishanth.multifragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FirstFragment extends Fragment {

    AlertDialog.Builder ad,ad1;
    CharSequence[] ch ;
    int whc;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.goToAddFragment();
                //Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                //startActivityForResult(i,REQUESTCODE);
            }
        });

        getActivity().findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ad = new AlertDialog.Builder(getActivity());
                ad1 = new AlertDialog.Builder(getActivity());


                final ArrayList<FavoriteMovie> ls = mListener.getList();

                if(ls.size()!=0) {

                    ad.setTitle("Pick a movie");

                    CharSequence ch[] = new CharSequence[ls.size()];
                    int i = 0;
                    for (FavoriteMovie f : ls) {
                        ch[i++] = f.movieName;
                    }

                    ad.setItems(ch, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Intent i = new Intent(MainActivity.this,EditMovie.class);
                            whc = which;
                            mListener.goToEditFragment(ls.get(which));

                            //i.putExtra(MainActivity.movie_data,);
                            //startActivityForResult(i,REQUESTCODE2);


                        }
                    });

                    ad.show();
                }else{
                    Toast.makeText(getActivity()," No Movies to edit ",Toast.LENGTH_SHORT).show();
                }


                //mListener.goToEditFragment();
                //Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                //startActivityForResult(i,REQUESTCODE);
            }
        });

        getActivity().findViewById(R.id.buttonDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToDeleteFragment();
                //Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                //startActivityForResult(i,REQUESTCODE);
            }
        });

        getActivity().findViewById(R.id.buttonShowListY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToShowByYearFragment();
                //Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                //startActivityForResult(i,REQUESTCODE);
            }
        });


        getActivity().findViewById(R.id.buttonShowListR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToShowByRatingFragment();
                //Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                //startActivityForResult(i,REQUESTCODE);
            }
        });














    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
       public void goToAddFragment();
        public void goToEditFragment(FavoriteMovie f);
        public void goToDeleteFragment();
        public void goToShowByYearFragment();
        public void goToShowByRatingFragment();
        public ArrayList<FavoriteMovie> getList();


    }
}
