package com.example.venkatesh.inclass01;

/**
 * Created by Venkatesh on 8/27/2017.
 */
public class Profile {

    String fname,lname,email;
    int age,weight;

    public Profile(String fname, String lname, String email, int age, int weight) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.age = age;
        this.weight = weight;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
