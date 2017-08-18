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

    @GET("professor/{professorId}")
    Call<Professor> getProfessorPeloId(@Path("professorId") int professorId );

    @POST("professor/addprofessor")
    Call<Log> addProfessor(@Body Professor professor  );

    @PUT("professor/update/{professorId}")
    @FormUrlEncoded
    Call<Professor> atualizaProfessor(@Body Professor Professor, @Path("ProfessorId") int professorId  );

    @POST("professor/delete/{professorId}")
    Call<Log> deleteProfessor( @Path("professorId") int professorId  );

}
