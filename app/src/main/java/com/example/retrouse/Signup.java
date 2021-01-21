package com.example.retrouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrouse.api_interfaces.JsonPlaceHolderApi;
import com.example.retrouse.models.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    private Button post,read;
    private EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.emailv);
        password=findViewById(R.id.passwordv);
        post=findViewById(R.id.postb);
        read=findViewById(R.id.readb);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();
            }
        });
        read.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,MainActivity.class));
            }
        });
    }

//    private boolean validate(String email, String password){
//        if(email.isEmpty()){
//            email.setError("email Required");
//            email.requestFocus();
//        }
//        if(password.isEmpty()){
//            password.setError("Password Required");
//            password.requestFocus();
//        }
//
//        return false;
//    }

    void addPost() {
        String str1 = email.getText().toString().trim();
        String str2 = password.getText().toString().trim();


        Post pt = new Post(str1, str2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.createPost(pt);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(Signup.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}