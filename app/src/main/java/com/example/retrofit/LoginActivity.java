package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txt_register;
    APIInterface request;

    TextInputEditText edt_email,edt_password;

    Button btn_login;

    String url="https://retrofit.mihanaserver.ir/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(RestoreStateuser()){

            startActivity(new Intent(LoginActivity.this,MainActivity.class));

            finish();


        }
        setContentView(R.layout.activity_login);

        request = APIClient.getAPIClient(url).create(APIInterface.class);



        edt_email=findViewById(R.id.edt_emial);

        edt_password=findViewById(R.id.edt_password);

        btn_login=findViewById(R.id.btn_login);


        txt_register=findViewById(R.id.txt_register);


        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getuser();

                SaveStateUser();
            }
        });
    }

    private void getuser() {
        String email=edt_email.getText().toString();

        String password=edt_password.getText().toString();

        Call<users>call=request.Loginaccount(email,password);


        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {

                if(response.body().getResponse().equals("USER_LOGIN")){

                    Toast.makeText(LoginActivity.this, "Login Succsess", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                    finish();


                }else if(response.body().getResponse().equals("NO_ACCOUNT")){


                    Toast.makeText(LoginActivity.this, "You should be Register", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<users> call, Throwable t) {


                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void SaveStateUser(){
        SharedPreferences sharedPreferences=getApplication().getSharedPreferences("prefUser",MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putBoolean("User_State",true);


        editor.commit();

    }


    private boolean RestoreStateuser(){


        SharedPreferences sharedPreferences=getApplication().getSharedPreferences("prefUser",MODE_PRIVATE);


        boolean stateuser;


        stateuser=sharedPreferences.getBoolean("User_State",false);

        return stateuser;
    }
}