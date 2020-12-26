package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView TV;
    private Retrofit retrofit;

    private final static String BASE_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        GithubInterface githubInterface = retrofit.create(GithubInterface.class);

        Call<List<Contributor>> call = githubInterface.contributor("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> contributors = response.body();

                for (Contributor contributor : contributors){
                    TV.append(contributor.login);
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

                Log.e("정보 받아오기 실패","ㅜㅜ");
            }
        });

    }

    private void init() {

        TV = findViewById(R.id.TV);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}