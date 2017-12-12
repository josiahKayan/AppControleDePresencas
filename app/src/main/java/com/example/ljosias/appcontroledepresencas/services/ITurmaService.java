package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ljosias on 20/08/2017.
 */

public interface ITurmaService {

    @GET("turma/turmas")
    Call<List<Turma>> getTurmas( );

    @GET("turma/turmas/{professorId}")
    Call<List<Turma>> getTurmaPorProfessorId( @Path("professorId") int professorId);


    @GET("turma/cursos/{cursoId}")
    Call<List<Turma>> getTurmasPeloId(@Path("cursoId") int cursoId );

    @GET("turma/{turmaId}")
    Call<Turma> getTurmaPeloId(@Path("turmaId") int turmaId );

    @POST("turma/addturma")
    Call<Log> addTurma(@Body Turma turma  );

    @PUT("turma/update/{turmaId}")
    @FormUrlEncoded
    Call<Turma> atualizaTurma(@Body Turma turma, @Path("turmaId") int turmaId  );

    @POST("turma/delete/{turmaId}")
    Call<Log> deleteTurma( @Path("turmaId") int turmaId  );

    @POST("turma/{turmaId}/{alunoId}")
//    @FormUrlEncoded
    Call<Log> adicionaAlunoATurma(@Path("turmaId") int turmaId, @Path("alunoId") int alunoId   );
}
