package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by ljosias on 16/06/2017.
 */

public class Aluno {

    @SerializedName("AlunoId")
    @Expose
    public int alunoId ;
    /// <summary>
    /// atributo da classe Tag
    /// </summary>
    @SerializedName("Tag")
    @Expose
    public  Tag tag ;
    /// <summary>
    /// atributo da classe Usuario
    /// </summary>
    @SerializedName("Usuario")
    @Expose
    public  Usuario usuario ;
    /// <summary>
    /// atributo da classe Turma
    /// </summary>
    @SerializedName("Turmas")
    @Expose
    public List<Turma> turmas ;

    @SerializedName("Nome")
    @Expose
    public String nome ;

    /// <summary>
    /// Nome Completo de uma Pessoa
    /// </summary>
    @SerializedName("NomeCompleto")
    @Expose
    public String nomeCompleto ;

    /// <summary>
    /// Data de Nascimento de uma Pessoa
    /// </summary>
    @SerializedName("DataNascimento")
    @Expose
    public String dataNascimento ;

    /// <summary>
    /// Idade de uma pessoas
    /// </summary>
    @SerializedName("Idade")
    @Expose
    public String idade ;

}
