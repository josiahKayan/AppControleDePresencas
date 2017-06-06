package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.model.Company;
import com.example.ljosias.appcontroledepresencas.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ljosias on 03/06/2017.
 */

public interface IUsuarioService {

    @GET("usuario/usuarios")
    List<Call<Usuario>> getUsuarios( );

    @POST("login/logar")
    Call<Usuario> login( @Body Usuario usuario );

    @GET("usuario/{usuarioId}")
    Call<Usuario> getUsuarioPeloId(@Path("usuarioId") int usuarioId );

    @POST("usuario/addusuario")
    @FormUrlEncoded
    Call<Usuario> addUsuario(@Body Usuario usuario  );

    @PUT("usuario/update/{Id}")
    @FormUrlEncoded
    Call<Usuario> atualizaUsuario(@Body Usuario usuario, @Path("Id") int usuarioId  );

    @DELETE("usuario/delete/{Id}")
    @FormUrlEncoded
    Call<Usuario> deleteUsuario( @Path("Id") int usuarioId  );
}
