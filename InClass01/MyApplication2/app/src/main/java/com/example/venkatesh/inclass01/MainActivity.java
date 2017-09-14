package com.example.venkatesh.inclass01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs =  getSharedPreferences("inclass01",MODE_PRIVATE);
        prefsEditor = mPrefs.edit();

        token=mPrefs.getString("token","null");

        if(!token.equals("null"))
        {
            Intent i = new Intent(getApplicationContext(),ProfileDetails.class);
            finish();
            startActivity(i);
        }

        Button btnlogin = (Button) findViewById(R.id.btnlogin);
        Button btnsignup = (Button) findViewById(R.id.btnsignup);

        final EditText etusername = (EditText) findViewById(R.id.etusename);
        final EditText etpassword = (EditText) findViewById(R.id.etpassword);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient client = new OkHttpClient();

                String url = "https://advancemad.000webhostapp.com/index.php";

                RequestBody formBody = new FormBody.Builder()
                        .add("email", etusername.getText().toString().trim())
                        .add("password", etpassword.getText().toString())
                        .build();

                Request request =new Request.Builder().addHeader("TOKEN","null")
                        .url(url)
                        .post(formBody)
                        .build();


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_LONG).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });

                        Log.d("debug1","Invalid Credentials");
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException {

                        try {
                            if (response.isSuccessful()) {



                               Log.d( "sdd",response.body().contentType().toString());


                                JSONObject jwt = new JSONObject(response.body().string());

                                prefsEditor.putString("token",jwt.getString("jwt"));
                                prefsEditor.commit();

                                Intent i = new Intent(MainActivity.this,ProfileDetails.class);
                                finish();
                                startActivity(i);


                            } else {

                                MainActivity.this.runOnUiThread(new Runnable() {
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

                            MainActivity.this.runOnUiThread(new Runnable() {
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
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),SignUp.class);
                finish();
                startActivity(i);
            }
        });

    }
}
