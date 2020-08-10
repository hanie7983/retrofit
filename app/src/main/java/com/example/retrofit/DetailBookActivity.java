 package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

 public class DetailBookActivity extends AppCompatActivity {

     public static String ID = "id";

     int id;

     Bundle bundle;

     TextView name_book , description , name_author , published , genre , price;
     ImageView img_Book;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail_book);

         id = Integer.parseInt(getIntent().getStringExtra(ID));


         bundle = getIntent().getExtras();

         name_book = findViewById(R.id.name_nook);
         img_Book = findViewById(R.id.img_books);
         description = findViewById(R.id.description);
         name_author = findViewById(R.id.name_author);
         published = findViewById(R.id.published);
         genre = findViewById(R.id.genre);
         price = findViewById(R.id.txt_price);


         name_book.setText(bundle.getString("name"));
         Picasso.get().load(bundle.getString("link_img")).into(img_Book);
         description.setText(bundle.getString("description"));
         name_author.setText(new StringBuilder("Author : ") + bundle.getString("author"));
         published.setText(new StringBuilder("Published At : ")+ bundle.getString("publish"));
         genre.setText(new StringBuilder("Genre : ")+bundle.getString("genre"));
         price.setText(bundle.getString("price"));



     }

 }