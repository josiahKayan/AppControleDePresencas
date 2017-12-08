package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ljosias on 09/06/2017.
 */

public class Curso {

    @SerializedName("CursoId")
    @Expose
    public int cursoId ;

    @SerializedName("Nome")
    @Expose
    public String nome ;

    @SerializedName("Descricao")
    @Expose
    public String descricao ;

    @SerializedName("Ativo")
    @Expose
    public boolean ativo ;




    public Curso(){};

    public  Curso(int cursoId , String nome, String descricao, boolean ativo){
        this.cursoId = cursoId;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public int getcursoId() {
        return cursoId;
    }

    public void setcursoId(int cursoId) {
        cursoId = cursoId;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        nome = nome;
    }

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        descricao = descricao;
    }

    public boolean getativo() {
        return ativo;
    }

    public void setativo(boolean ativo) {
        ativo = ativo;
    }


}
