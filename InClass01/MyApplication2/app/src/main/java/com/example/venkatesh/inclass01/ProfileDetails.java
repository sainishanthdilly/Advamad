package com.example.venkatesh.inclass01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileDetails extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView etpemail = (TextView) findViewById(R.id.tvpemail);
        final EditText etpfname = (EditText) findViewById(R.id.etpfname);
        final EditText etplname = (EditText) findViewById(R.id.etplname);
        final EditText etpage = (EditText) findViewById(R.id.etpage);
        final EditText etpweight = (EditText) findViewById(R.id.etpweight);



        Button btnpupdate = (Button) findViewById(R.id.btnpupdate);
        Button btnplogout = (Button) findViewById(R.id.btnplogout);
        Button btnpexit = (Button) findViewById(R.id.btnpexit);

        btnpupdate.setOnClickListener(this);

        btnplogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefsEditor.clear();
                prefsEditor.commit();
                finish();

            }
        });



        btnpexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });






        mPrefs =  getSharedPreferences("inclass01",MODE_PRIVATE);
        prefsEditor = mPrefs.edit();


        token=mPrefs.getString("token","null");


        OkHttpClient client = new OkHttpClient();

        String url = "https://advancemad.000webhostapp.com/index.php";


        Request request =new Request.Builder().addHeader("TOKEN",token)
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                Log.d("debug1","Invalid Credentials");
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                try {
                    if (response.isSuccessful()) {



                        String s = response.body().string();

                        Log.d("asa","assa");

                        if(s.contains("Expired token")){


                            Log.d("sd","ssdsd");
                            prefsEditor.clear();
                            prefsEditor.commit();

                            Intent i = new Intent(ProfileDetails.this,MainActivity.class);
                            finish();
                            startActivity(i);


                        }
                        JSONObject jwt = new JSONObject(s);
                      final JSONObject dat = jwt.getJSONObject("data");
                        Log.d("data",dat.toString());

                        ProfileDetails.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    etpemail.setText(dat.getString("email"));
                                    etpfname.setText(dat.getString("fname"));
                                    etplname.setText(dat.getString("lname"));
                                    etpage.setText(dat.getString("age"));
                                    etpweight.setText(dat.getString("weight"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                    } else {

                        ProfileDetails.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_LONG).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                    }
                }catch (Exception e)
                {

                    Log.e("Error ", e.getMessage());
                    ProfileDetails.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_LONG).show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    });

                }


            }
        });












}

    @Override
    public void onClick(View view) {

        final TextView etpemail = (TextView) findViewById(R.id.tvpemail);
        final EditText etpfname = (EditText) findViewById(R.id.etpfname);
        final EditText etplname = (EditText) findViewById(R.id.etplname);
        final EditText etpage = (EditText) findViewById(R.id.etpage);
        final EditText etpweight = (EditText) findViewById(R.id.etpweight);


        if(view.getId() == R.id.btnpupdate) {

            String email = etpemail.getText().toString();
            String fname = etpfname.getText().toString();
            String lname = etplname.getText().toString();
            String age = etpage.getText().toString();
            String weight = etpweight.getText().toString();


            String url = "https://advancemad.000webhostapp.com/updateDetails.php";
            OkHttpClient client = new OkHttpClient();


            RequestBody formBody = new FormBody.Builder()
                    .add("fname", fname)
                    .add("lname", lname)
                    .add("email", email)
                    .add("age", age)
                    .add("weight", weight)
                    .build();


            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if (response.isSuccessful()) {

                        try {
                            String s = response.body().string();
                            JSONObject jwt = new JSONObject(s);

                            Log.d("s","sd");

                            prefsEditor.putString("token",jwt.getString("jwt"));
                            prefsEditor.commit();

                            ProfileDetails.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }
                            });

                           //








                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    else {


                        Log.d("sxd","op");
                    }
                }
            });


        }

    }
}
