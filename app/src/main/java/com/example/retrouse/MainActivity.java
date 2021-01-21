package com.example.retrouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrouse.api_interfaces.JsonPlaceHolderApi;
import com.example.retrouse.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text_view_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getdata();
    }

    private  void getdata(){
        text_view_result = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi Api = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = Api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    text_view_result.setText("Code : "+response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post posts1:posts){
                    String content = "";
                    content += "\n\n Email: " + posts1.getEmail();
                    content += "\nPassword: " + posts1.getPassword();

                    text_view_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                text_view_result.setText(t.getMessage());
            }
        });
    }

}

