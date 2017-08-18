package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ljosias on 15/06/2017.
 */

public class Turma {

    @SerializedName("TurmaId")
    @Expose
    public int turmaId ;

    @SerializedName("DataInicio")
    @Expose
    public String dataInicio ;

    @SerializedName("DataTermino")
    @Expose
    public String dataTermino ;

    @SerializedName("Professor")
    @Expose
    public Professor professor ;

    @SerializedName("Curso")
    @Expose
    public  Curso curso ;
    /// <summary>
    /// Lista de Alunos
    /// </summary>
    @SerializedName("AlunoLista")
    @Expose
    public List<Aluno> alunoLista ;
    /// <summary>
    /// Lista de Presenças
    /// </summary>
    @SerializedName("PresencaLista")
    @Expose
    public  List<Presenca> presencaLista ;

}
