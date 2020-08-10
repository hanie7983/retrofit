package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;

    BooksAdapter adapter;

    List<Books>list=new ArrayList<>();

    APIInterface request;

    String url="https://retrofit.mihanaserver.ir/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request=APIClient.getAPIClient(url).create(APIInterface.class);\

        recyclerview=findViewById(R.id.recyclerView);

        recyclerview.setHasFixedSize(true);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));




        getDataUsePath();
    }
    private void getData(){

        request.getDataUserpath("getData.php").enqueue(new Callback<List<Books>>() {

            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                list=response.body();
                adapter=new BooksAdapter(getApplicationContext(),list);
                recyclerview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage()+" ");

            }
        });

    }
    private void getDataUsePath(){

}