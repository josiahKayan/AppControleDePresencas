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

    @SerializedName("NomeTurma")
    @Expose
    public String nomeTurma ;

    @SerializedName("DataInicio")
    @Expose
    public String dataInicio ;

    @SerializedName("DataTermino")
    @Expose
    public String dataTermino ;

    @SerializedName("HoraInicial")
    @Expose
    public String horaInicial ;

    @SerializedName("HoraFinal")
    @Expose
    public String horaFinal ;

    @SerializedName("Professor")
    @Expose
    public Professor professor ;

    @SerializedName("Curso")
    @Expose
    public  Curso curso ;

    @SerializedName("ProfessorId")
    @Expose
    public int professorId ;


    @SerializedName("CursoId")
    @Expose
    public  int cursoId ;


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
