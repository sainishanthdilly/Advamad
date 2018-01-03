package com.example.nishanth.multifragmentdemo;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener,AddFragment.OnFragmentAddMovieInteractionListener, EditFragment.OnFragmentEditedMovieInteractionListener,ShowbyRantingFragment.OnFragmentRatingInteractionListener,ShowbyYearFragment.OnFragmentYearInteractionListener {

    public ArrayList<FavoriteMovie> ls;
    private AlertDialog.Builder ad1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls = new ArrayList<FavoriteMovie>();

        getFragmentManager().beginTransaction().add(R.id.container,new FirstFragment(),"first").commit();
    }



    public void goToNextFragment() {
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new SecondFragment(),"second").commit();

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() >0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }



    }

    @Override
    public void goToAddFragment() {

        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new AddFragment(),"add").commit();


    }

    @Override
    public void goToEditFragment(FavoriteMovie f) {
        EditFragment ef = new EditFragment();
        ef.setMovieData(f);

        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,ef,"edit").commit();

    }



    @Override
    public void goToDeleteFragment() {

        if(ls.size() !=0) {

            ad1 = new AlertDialog.Builder(this);


            ad1.setTitle("Pick a movie");

            CharSequence ch[] = new CharSequence[ls.size()];
            int i = 0;
            for (FavoriteMovie f : ls) {
                ch[i++] = f.movieName;
            }

            ad1.setItems(ch, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    FavoriteMovie f = ls.remove(which);
                    Toast.makeText(getApplicationContext(), f.movieName + " movie is deleted ", Toast.LENGTH_SHORT).show();


                }
            });

            ad1.show();
        }
        else{

            Toast.makeText(getApplicationContext()," No movies to Delete", Toast.LENGTH_SHORT).show();
        }
    }



    //getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new SecondFragment(),"second").commit();




    @Override
    public void goToShowByYearFragment() {

        ShowbyYearFragment s =new ShowbyYearFragment();
        s.setLisData(ls);

        if(ls.size()!=0) {
            //    Intent i = new Intent(MainActivity.this, MoviebyYear.class);
            Collections.sort(ls, new Comparator<FavoriteMovie>() {
                @Override
                public int compare(FavoriteMovie o1, FavoriteMovie o2) {
                    if (Integer.parseInt(o1.year) > Integer.parseInt(o2.year))
                        return 1;
                    else if (Integer.parseInt(o1.year) < Integer.parseInt(o2.year)) {
                        return -1;
                    }
                    return 0;

                }
            });


            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, s, "year").commit();
        }

    }

    @Override
    public void goToShowByRatingFragment() {
        ShowbyRantingFragment s =new ShowbyRantingFragment();
        if(ls.size()!=0) {
            Collections.sort(ls, new ComparatorByRating());

            s.setLisDataR(ls);

            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, s, "rating").commit();
        }


    }

    @Override
    public ArrayList<FavoriteMovie> getList() {
        return ls;
    }

    @Override
    public void onAddMovieFragmentInteraction(FavoriteMovie f) {
        Toast.makeText(getApplicationContext()," Movie Added",Toast.LENGTH_LONG).show();
        ls.add(f);
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new FirstFragment(),"first").commit();




    }

    @Override
    public void onFragmentEditedMovieInteraction() {
        Toast.makeText(getApplicationContext()," Movie Edited",Toast.LENGTH_LONG).show();
       // ls.add(f);
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new FirstFragment(),"first").commit();



    }

    @Override
    public void onFragmentShwRatingInteraction() {
        //Toast.makeText(getApplicationContext()," Movie Edited",Toast.LENGTH_LONG).show();
        // ls.add(f);
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new FirstFragment(),"first").commit();


    }


    @Override
    public void onFragmentShwYearInteraction() {

        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,new FirstFragment(),"first").commit();


    }






}


class ComparatorByRating implements Comparator<FavoriteMovie> {

    @Override
    public int compare(FavoriteMovie o1, FavoriteMovie o2) {
        if( o1.rating < o2.rating)
            return 1;
        else if(o1.rating > o2.rating){
            return -1;
        }
        return 0;
    }
}















