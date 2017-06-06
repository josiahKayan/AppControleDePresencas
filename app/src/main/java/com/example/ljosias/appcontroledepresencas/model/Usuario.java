package com.example.ljosias.appcontroledepresencas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ljosias on 03/06/2017.
 */

public class Usuario {

    @SerializedName("UsuarioId")
    @Expose
    private String usuarioId ;

    @SerializedName("Email")
    @Expose
    private String email ;

    @SerializedName("Senha")
    @Expose
    private String senha ;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
