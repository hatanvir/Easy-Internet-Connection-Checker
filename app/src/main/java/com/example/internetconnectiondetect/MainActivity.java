package com.example.internetconnectiondetect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    InternetConnectionChecker implementation;
    ListView listView;
    ProgressBar progressBar;

    List<String> todos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);

        todos = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        implementation = new InternetConnectionChecker(this);

        implementation.getStatus( new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                if(st){
                    progressBar.setVisibility(View.VISIBLE);
                    retrofit.create(Api.class)
                            .getTodoList()
                            .enqueue(new Callback<List<Todo>>() {
                                @Override
                                public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                                    if(response.code() == 200){
                                        for(Todo s:response.body()){
                                            todos.add(s.getTitle());
                                        }
                                        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,todos));
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Todo>> call, Throwable t) {
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }else {
                    Toast.makeText(MainActivity.this, "No network connection !", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        implementation.unRegister();
    }
}