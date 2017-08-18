package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ljosias on 03/06/2017.
 */

public class Usuario {

    @SerializedName("UsuarioId")
//    @Expose
    public int UsuarioId ;

    @SerializedName("Email")
//    @Expose
    public String Email ;

    @SerializedName("Senha")
//    @Expose
    public String Senha ;

    @SerializedName("Perfil")
//    @Expose
    public int Perfil ;


    public Usuario(String email, String senha) {
        this.Email = email;
        this.Senha = senha;
    }

    public Usuario(String email, String senha, int perfil) {
        this.Email = email;
        this.Senha = senha;
        this.Perfil = perfil;
    }

//    public String getPerfil() {
//        return perfil;
//    }
//
//    public void setPerfil(String perfil) {
//        this.perfil = perfil;
//    }

//    public String getUsuarioId() {
//        return usuarioId;
//    }
//
//    public void setUsuarioId(String usuarioId) {
//        this.usuarioId = usuarioId;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
}
