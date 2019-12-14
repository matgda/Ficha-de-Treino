package com.example.listafitness.entities;

import com.google.gson.annotations.SerializedName;

public class ListaExercicioEntry {

    private int id;

    @SerializedName("nome_exercicio")
    private String nomeExercicio;

    @SerializedName("nome_Lista")
    private String nomeLista;

    @SerializedName("numero_series")
    private
    int numeroSeries;

    @SerializedName("numero_repeticoes")
    private
    int numeroRepeticoes;

    @SerializedName("fk_usuario")
    private
    int fkUsuario;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public ListaExercicioEntry(String nomeExercicio, String nomeLista, int numeroSeries, int numeroRepeticoes, int fkUsuario) {
        this.nomeExercicio = nomeExercicio;
        this.nomeLista = nomeLista;
        this.numeroSeries = numeroSeries;
        this.numeroRepeticoes = numeroRepeticoes;
        this.fkUsuario = fkUsuario;
    }



    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public int getNumeroSeries() {
        return numeroSeries;
    }

    public void setNumeroSeries(int numeroSeries) {
        this.numeroSeries = numeroSeries;
    }

    public int getNumeroRepeticoes() {
        return numeroRepeticoes;
    }

    public void setNumeroRepeticoes(int numeroRepeticoes) {
        this.numeroRepeticoes = numeroRepeticoes;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
