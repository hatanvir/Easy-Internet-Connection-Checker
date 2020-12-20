package com.example.internetconnectiondetect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("todos")
    Call<List<Todo>> getTodoList();
}
