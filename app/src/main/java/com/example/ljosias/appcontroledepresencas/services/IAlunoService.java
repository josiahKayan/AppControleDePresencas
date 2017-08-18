package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ljosias on 17/06/2017.
 */

public interface IAlunoService {

    @GET("aluno/alunos")
    Call<List<Aluno>> getAlunos();

    @GET("aluno/{alunoId}")
    Call<Aluno> getAlunoPeloId(@Path("alunoId") int alunoId);

    @POST("aluno/addaluno")
    Call<Log> addAluno(@Body Aluno aluno);

    @POST("aluno/update/{alunoId}")
    @FormUrlEncoded
    Call<Log> atualizaAluno(@Body Aluno Aluno, @Path("alunoId") int alunoId);

    @GET("aluno/delete/{alunoId}")
    Call<Log> deleteAluno(@Path("alunoId") int alunoId);

}
