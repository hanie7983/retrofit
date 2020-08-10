package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("getData.php")

    Call<List<Books>>getData();

    @POST("register.php")
    Call<users>Registeraccount(@Query("username")String username,
                       @Query("email")String email,
                       @Query("phone")String phone,
                       @Query("password")String password);


    @GET("Login.php")
    Call<users>Loginaccount(@Query("email")String email,
                               @Query("password")String password);


    @GET("{url}")

    Call<List<Books>>getDataUserpath(@Path("url")String url);

}
