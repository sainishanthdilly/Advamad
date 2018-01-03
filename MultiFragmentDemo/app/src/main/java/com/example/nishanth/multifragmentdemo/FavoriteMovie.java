package com.example.nishanth.multifragmentdemo;

import android.os.Parcel;
import android.os.Parcelable;


//Sai Nishanth Dilly
// Shaik rajia Shareen
//Group04 HW02


public class FavoriteMovie implements Parcelable{

    String movieName;
    String descr;
    String genre;
    int rating;
    String year;
    String IMDB;

    FavoriteMovie(String movieName, String descr, String genre, int rating, String year, String IMDB){
        this.movieName = movieName;
        this.descr = descr;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.IMDB = IMDB;
    }

    protected FavoriteMovie(Parcel in) {
        movieName = in.readString();
        descr = in.readString();
        genre = in.readString();
        rating = in.readInt();
        year = in.readString();
        IMDB = in.readString();
    }

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(descr);
        dest.writeString(genre);
        dest.writeInt(rating);
        dest.writeString(year);
        dest.writeString(IMDB);
    }

    @Override
    public String toString() {
        return movieName+" : "+year+" : "+descr + ":"+rating +" : "+genre;
    }

}
