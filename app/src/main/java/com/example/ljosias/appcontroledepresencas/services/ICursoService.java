package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.models.Curso;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by ljosias on 09/06/2017.
 */

public interface ICursoService {

    @GET("curso/cursos")
    Call<List<Curso>> getCursos( );

    @GET("curso/{cursoId}")
    Call<Curso> getCursoPeloId(@Path("cursoId") int cursoId );

    @POST("curso/addcurso")
    Call<Curso> addCurso(@Body Curso curso  );

    @PUT("curso/update/{cursoId}")
    @FormUrlEncoded
    Call<Curso> atualizaCurso(@Body Curso curso, @Path("cursoId") int cursoId  );

    @POST("curso/delete/{cursoId}")
    Call<Curso> deleteCurso( @Path("cursoId") int cursoId  );

}
