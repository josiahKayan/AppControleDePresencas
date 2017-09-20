package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by ljosias on 16/06/2017.
 */

public class Presenca {



    @SerializedName("PresencaId")
    @Expose
    public int presencaId ;
    /// <summary>
    /// Marca a Hora de Entrada
    /// </summary>
    @SerializedName("HoraEntrada")
    @Expose
    public String horaEntrada ;
    /// <summary>
    /// Marca o mes
    /// </summary>
    @SerializedName("Mes")
    @Expose
    public int mes ;
    /// <summary>
    /// Marca o ano
    /// </summary>
    @SerializedName("Ano")
    @Expose
    public int ano ;

    @SerializedName("Dia")
    @Expose
    public int dia ;

    @SerializedName("Ativo")
    @Expose
    public boolean ativo ;

    //public  Turma Turma ;
    /// <summary>
    /// Lista de Turmas
    /// </summary>
    @SerializedName("TurmaId")
    @Expose
    public int turmaId ;

    @SerializedName("Alunos")
    @Expose
    public List<Aluno> listaAlunos ;

    public Presenca(int dia, int ano){
        this.dia = dia;
        this.ano = ano;
    }

    public Presenca(int dia,int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Presenca(){}

}
