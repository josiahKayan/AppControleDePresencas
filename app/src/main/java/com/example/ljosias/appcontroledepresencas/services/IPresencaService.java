package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Presenca;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ljosias on 28/08/2017.
 */

public interface IPresencaService {

    @GET("presenca/presenca")
    Call<List<Presenca>> getPresencas( );

    @GET("presenca/turma/{turmaId}")
    Call<List<Presenca>> getPresencasPelaTurma( @Path("turmaId") int turmaId  );

    @GET("presenca/{presencaId}")
    Call<Presenca> getPresencaPeloId(@Path("presencaId") int presencaId );

    @POST("presenca/addpresenca")
    Call<Presenca> addPresenca(@Body Presenca presenca  );

    @POST("presenca/update/{presencaId}")
    @FormUrlEncoded
    Call<Presenca> atualizaPresenca( @Path("presencaId") int presencaId  );


    @POST("presenca/delete/{presencaId}")
    Call<Presenca> deletePresenca( @Path("presencaId") int presencaId  );

}
