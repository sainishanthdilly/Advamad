package com.example.nishanth.multifragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentEditedMovieInteractionListener } interface
 * to handle interaction events.
 */
public class EditFragment extends Fragment {

    private OnFragmentEditedMovieInteractionListener  mListener;

    EditText e1,e2,e3,e4;
    Spinner sp;
    SeekBar sb;
    FavoriteMovie f;
    String arr[];
    TextView rate1;

    public EditFragment() {
        // Required empty public constructor
    }

    public void setMovieData(FavoriteMovie f1){
        f=f1;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        e1 = (EditText) getActivity().findViewById(R.id.e_editTextMovieName);

        e1.setText(f.movieName);

        e2 = (EditText) getActivity().findViewById(R.id.e_editTextDescription);
        e2.setText(f.descr);

        sp = (Spinner)getActivity().findViewById(R.id.e_spinnerGenre);
        arr = getResources().getStringArray(R.array.genre_array);
        int i=0;
        while(!arr[i].equalsIgnoreCase(f.genre)){
            i++;
        }
        sp.setSelection(i);

        sb = (SeekBar)getActivity().findViewById(R.id.e_seekBarRating);
        sb.setProgress(f.rating);


        rate1=(TextView) getActivity().findViewById(R.id.rate1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rate1.setText(progress+" ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        e3 = (EditText)getActivity().findViewById(R.id.e_editTextYear);
        e3.setText(f.year);

        e4 = (EditText)getActivity().findViewById(R.id.e_editTextIMDB);
        e4.setText(f.IMDB);


        Button b = (Button)getActivity().findViewById(R.id.e_buttonSubmitAddMovie);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try

                {

                    f.movieName = e1.getText().toString();


                    f.descr = e2.getText().toString();
                    f.genre = sp.getSelectedItem().toString();
                    f.rating = sb.getProgress();
                    f.year = e3.getText().toString();
                    f.IMDB = e4.getText().toString();


                    if(f.movieName.length()==0||f.descr.length()==0||f.year.length()==0||f.IMDB.length()==0)
                        throw  new Exception();

                    if(f.movieName.length()<0 ||f.movieName.length()>50)
                    {
                        Toast.makeText(getActivity(),"Enter name upto 50 characters only",Toast.LENGTH_LONG).show();
                    }
                    else if(f.descr.length()<0 ||f.descr.length()>50)
                    {
                        Toast.makeText(getActivity(),"Enter Description upto 1000 character only",Toast.LENGTH_LONG).show();
                    }
                    else if(f.year.length()!=4)
                    {
                        Toast.makeText(getActivity(),"Enter valid year(4 numbers)",Toast.LENGTH_LONG).show();
                    }
                    else {

                        mListener.onFragmentEditedMovieInteraction();
                    }}
                catch (Exception e)
                {

                    Toast.makeText(getActivity(),"Enter all deails",Toast.LENGTH_LONG).show();
                }


            }
        });








    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof  OnFragmentEditedMovieInteractionListener ) {
            mListener = (OnFragmentEditedMovieInteractionListener ) activity;
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
    public interface OnFragmentEditedMovieInteractionListener {
        // TODO: Update argument type and name
        void onFragmentEditedMovieInteraction();
    }
}
