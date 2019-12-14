package com.example.listafitness.rest;

import com.example.listafitness.entities.ListaExercicioEntry;
import com.example.listafitness.entities.UsuarioEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ListaExerciciosService {
    //Adicionar o endereço Ip do webserver
    String BASE_URL = "http://169.254.152.15:9090";






    @POST("validar")
    Call<UsuarioEntry> validar(@Body UsuarioEntry usuarioEntry);



    @POST("usuarios")
    Call<UsuarioEntry> add(@Body UsuarioEntry usuarioEntry);

    @GET("lista_exercicio")
    Call<List<ListaExercicioEntry>> list();


    @POST("lista_exercicio")
    Call<ListaExercicioEntry> add(@Body ListaExercicioEntry listaExercicio);




    @DELETE("lista_exercicio/{id}")
    Call<Void> remove(@Path("id") int id);

}
