package com.example.notesclone.api;

import com.example.notesclone.pojo.DeletePojo;
import com.example.notesclone.pojo.NotePojo;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("notes/")
    Call<ArrayList<NotePojo>> getAllNotes();

    @POST("notes/")
    Call<NotePojo> createNote(@Body JsonObject note);

    @PUT("notes/{id}")
    Call<NotePojo> updateNote(@Path("id") String id, @Body JsonObject note);

    @GET("notes/{id}")
    Call<NotePojo> getNote(@Path("id") String id);

    @DELETE("notes/{id}")
    Call<DeletePojo> deleteNote(@Path("id") String id);
}
