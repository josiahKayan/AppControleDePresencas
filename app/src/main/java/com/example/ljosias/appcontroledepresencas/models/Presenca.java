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
    public Date horaEntrada ;
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

    //public  Turma Turma ;
    /// <summary>
    /// Lista de Turmas
    /// </summary>
    @SerializedName("TurmaLista")
    @Expose
    public List<Turma> turmaLista ;
    
}