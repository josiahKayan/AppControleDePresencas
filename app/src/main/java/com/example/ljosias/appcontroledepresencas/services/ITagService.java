package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ljosias on 11/12/2017.
 */

public interface ITagService {

    @GET("tag/tags")
    Call<List<Tag>> getTags( );



    @GET("tag/{tagId}")
    Call<List<Tag>> getTag(@Path("tagId") int tagId );



}
