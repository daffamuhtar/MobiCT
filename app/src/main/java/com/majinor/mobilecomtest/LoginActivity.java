package com.majinor.mobilecomtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.majinor.mobilecomtest.apihelper.ApiInterface;
import com.majinor.mobilecomtest.apihelper.LoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;
    String baseUrl;
    ProgressDialog progressDialog;

    Context mContext;
    ApiInterface mApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername=(EditText) findViewById(R.id.etUsername);
        etPassword=(EditText) findViewById(R.id.etPassword);
        
        baseUrl = "http://35.240.192.212:8997/InventFoodCulinary/service/";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname=etUsername.getText().toString();
                String pword=etPassword.getText().toString();

                if(uname.isEmpty()){
                    etUsername.setError("Please Input Username");
                } else if(pword.matches("")){
                    etPassword.setError("Please Input Password");
                } else {
                    login();
                }
            }
        });
    }

    private void login() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Username", etUsername.getText().toString());
        jsonObject.addProperty("Password", etPassword.getText().toString());
        jsonObject.addProperty("Token", "1234567890");
        jsonObject.addProperty("OsType", "Android");
        jsonObject.addProperty("Role", "Customer");

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<LoginResult> call = service.postData(jsonObject);
        //calling the api
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                //hiding progress dialog
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getStatuscode().equals("01")){
                        Intent home = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(getApplicationContext(), ""+response.body().getStatusmessage()
                                , Toast.LENGTH_LONG).show();
                        startActivity(home);
                        finish();


                    } else {
                        Toast.makeText(getApplicationContext(), ""+response.body().getStatusmessage()
                                , Toast.LENGTH_LONG).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}