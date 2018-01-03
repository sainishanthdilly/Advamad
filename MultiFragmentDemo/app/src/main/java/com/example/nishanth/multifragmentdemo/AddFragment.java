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
 * {@link AddFragment.OnFragmentAddMovieInteractionListener} interface
 * to handle interaction events.
 */
public class AddFragment extends Fragment {

    Button b;
    EditText mn,des,eYear,eIMDB;
    Spinner genre;
    SeekBar sb;
    TextView rate;

    private OnFragmentAddMovieInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        b = (Button) getActivity().findViewById(R.id.e_buttonSubmitAddMovie);
        mn = (EditText) getActivity().findViewById(R.id.e_editTextMovieName);


        des = (EditText) getActivity().findViewById(R.id.e_editTextDescription);


        genre = (Spinner) getActivity().findViewById(R.id.e_spinnerGenre);
        // genre.getSelectedItem().toString();


        sb = (SeekBar) getActivity().findViewById(R.id.e_seekBarRating);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rate.setText(progress+" ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        eYear = (EditText)getActivity().findViewById(R.id.e_editTextYear);

        eIMDB = (EditText)getActivity().findViewById(R.id.editTextIMDB);
        rate=(TextView) getActivity().findViewById(R.id.rate);


        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick (View v){

                try

                {
                    String movieName = mn.getText().toString();
                    String descrip = des.getText().toString();
                    String arr[] = getResources().getStringArray(R.array.genre_array);
                    String val = arr[(int) genre.getSelectedItemId()];
                    int pg = sb.getProgress();
                    String yr = eYear.getText().toString();
                    String imdb = eIMDB.getText().toString();




                    if(movieName.length()==0||descrip.length()==0||yr.length()==0||imdb.length()==0)
                        throw  new Exception();

                    if(movieName.length()<0 ||movieName.length()>50)
                    {
                        Toast.makeText(getActivity(),"Enter name upto 50 characters only",Toast.LENGTH_LONG).show();
                    }
                    else if(descrip.length()<0 ||descrip.length()>50)
                    {
                        Toast.makeText(getActivity(),"Enter Description upto 1000 character only",Toast.LENGTH_LONG).show();
                    }
                    else if(yr.length()!=4)
                    {
                        Toast.makeText(getActivity(),"Enter valid year(4 numbers)",Toast.LENGTH_LONG).show();
                    }
                    else {


                        FavoriteMovie fb = new FavoriteMovie(movieName, descrip, val, pg, yr, imdb);

                        mListener.onAddMovieFragmentInteraction(fb);
                    }

                }
                catch (Exception e)
                {

                    Toast.makeText(getActivity(),"Enter all deails",Toast.LENGTH_LONG).show();
                }
            }

        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentAddMovieInteractionListener) {
            mListener = (OnFragmentAddMovieInteractionListener) activity;
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
    public interface OnFragmentAddMovieInteractionListener {
        // TODO: Update argument type and name
        void onAddMovieFragmentInteraction(FavoriteMovie f);
    }
}
