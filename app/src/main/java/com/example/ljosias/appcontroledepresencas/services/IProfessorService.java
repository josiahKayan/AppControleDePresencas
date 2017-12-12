package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Professor;

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

public interface IProfessorService {

    @GET("professor/professores")
    Call<List<Professor>> getProfessores( );



    @GET("professor/professores/ordenados/{professorId}")
    Call<List<Professor>> getProfessoresOrdenado(@Path("professorId") int professorId );

    @GET("professor/{professorId}")
    Call<Professor> getProfessorPeloId(@Path("professorId") int professorId );

    @POST("professor/addprofessor")
    Call<Log> addProfessor(@Body Professor professor  );

    @POST("professor/addprofessor")
    Call<Log> atualizaProfessor(@Body Professor professor);

    @POST("professor/delete/{professorId}")
    Call<Log> deleteProfessor( @Path("professorId") int professorId  );

}
