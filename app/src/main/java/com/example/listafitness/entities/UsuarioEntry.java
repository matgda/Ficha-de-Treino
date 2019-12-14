package com.example.listafitness.entities;

import com.google.gson.annotations.SerializedName;

public class UsuarioEntry {

    private
    int id;
    @SerializedName("usuario")
    private
    String nome;
    @SerializedName("senha")
    private
    String senha;

    public UsuarioEntry(String nome, String senha) {
        this.setNome(nome);
        this.setSenha(senha);

    }
    public UsuarioEntry(String nome, String senha, int id) {
        this.setNome(nome);
        this.setSenha(senha);
        this.setId(id);

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
