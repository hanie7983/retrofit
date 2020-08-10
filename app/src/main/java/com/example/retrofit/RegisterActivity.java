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

public class RegisterActivity extends AppCompatActivity {

    TextView txt_login;

    APIInterface request;

    TextInputEditText edt_username,edt_email,edt_phone,edt_password;

    Button btn_register;

    String url="https://retrofit.mihanaserver.ir/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(RestoreStateuser()){

            startActivity(new Intent(RegisterActivity.this,MainActivity.class));

            finish();


        }

        setContentView(R.layout.activity_register);

         request=APIClient.getAPIClient(url).create(APIInterface.class);

         edt_username=findViewById(R.id.edt_username);

         edt_email=findViewById(R.id.edt_emial);

         edt_password=findViewById(R.id.edt_password);

         edt_phone=findViewById(R.id.edt_phone);

         btn_register=findViewById(R.id.btn_register);


        txt_login=findViewById(R.id.txt_login);


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getuser();

                SaveStateUser();
            }
        });
    }


    private void getuser() {
        String username=edt_username.getText().toString();

        String email=edt_email.getText().toString();

        String phone=edt_phone.getText().toString();

        String password=edt_password.getText().toString();

        Call<users> call=request.Registeraccount(username,email,phone,password);
        
        call.enqueue(new Callback<users>() {

            @Override
            public void onResponse(Call<users> call, Response<users> response) {


                if(response.body().getResponse().equals("USER_REGISTER")){

                    Toast.makeText(RegisterActivity.this, "You Are Registerd", Toast.LENGTH_SHORT).show();

                }else if(response.body().getResponse().equals("SUCCESS")){

                    Toast.makeText(RegisterActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                    finish();

                }else if (response.body().getResponse().equals("WRONG")){

                    Toast.makeText(RegisterActivity.this, "Something  WRONG", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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