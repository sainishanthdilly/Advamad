package com.example.venkatesh.inclass01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        final EditText etfname = (EditText) findViewById(R.id.etfname);
        final EditText etlname = (EditText) findViewById(R.id.etlname);
        final EditText etemail = (EditText) findViewById(R.id.etemail);
        final EditText etpwd = (EditText) findViewById(R.id.etpwd);
        final EditText etcpwd = (EditText) findViewById(R.id.etcpwd);
        final EditText etage = (EditText) findViewById(R.id.etage);
        final EditText etweight = (EditText) findViewById(R.id.etweight);

        Button btncancel = (Button) findViewById(R.id.btncancel);
        Button btnsignup = (Button) findViewById(R.id.btnsignup1);



        final String url = "https://advancemad.000webhostapp.com/signup.php";

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();


                RequestBody formBody = new FormBody.Builder()
                        .add("fname", etfname.getText().toString().trim())
                        .add("lname",etlname.getText().toString().trim())
                        .add("email",etemail.getText().toString().trim())
                        .add("age",etage.getText().toString().trim())
                        .add("weight",etweight.getText().toString().trim())
                        .add("password",etpwd.getText().toString().trim())
                        .build();





                Request request =new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        SignUp.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                        //Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                        Log.d("check","errorrrrrr");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                        try {
                            if (response.isSuccessful()) {

                                JSONObject jwt = new JSONObject(response.body().string());

                                JSONObject data = jwt.getJSONObject("data");

                                int signupStatus = data.getInt("id");

                                if(signupStatus==0)
                                {

                                    SignUp.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            try {
                                                Toast.makeText(SignUp.this,"Email already registered",Toast.LENGTH_LONG).show();

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    });


                                    //Toast.makeText(SignUp.this,"Email already registered",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                    finish();
                                    startActivity(i);
                                }
                                else if(signupStatus==1)
                                {

                                    SignUp.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            try {
                                                Toast.makeText(SignUp.this,"Signed up successfully",Toast.LENGTH_LONG).show();

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    });


                                  //  Toast.makeText(SignUp.this,"Signed up successfully",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                    finish();
                                    startActivity(i);
                                }
                                else{


                                    SignUp.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            try {
                                                Toast.makeText(SignUp.this,"Unknown error",Toast.LENGTH_LONG).show();

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    });


                                }




                            } else {

                                SignUp.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_LONG).show();

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });

                               //
                            }
                        }catch (Exception e)
                        {
                            SignUp.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_LONG).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }
                            });

                            Log.e("eroror",e.getMessage());

                        }
                    }
                });

            }
        });



    }
}
