package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ljosias on 16/06/2017.
 */

public class Professor {

    @SerializedName("ProfessorId")
    @Expose
    public int professorId ;
    /// <summary>
    /// Lista de Cursos
    /// </summary>
    @SerializedName("CursoLista")
    @Expose
    public  List<Curso> cursoLista ;
    /// <summary>
    /// Lista de Turmas
    /// </summary>
    @SerializedName("TurmaLista")
    @Expose
    public List<Turma> turmaLista ;

    @SerializedName("Nome")
    @Expose
    public String nome ;

    /// <summary>
    /// Nome Completo de uma Pessoa
    /// </summary>
    @SerializedName("NomeCompleto")
    @Expose
    public String nomeCompleto ;


    @SerializedName("Usuario")
    @Expose
    public  Usuario usuario ;

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
