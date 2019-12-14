package com.example.listafitness.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.listafitness.R;
import com.example.listafitness.entities.UsuarioEntry;
import com.example.listafitness.rest.ListaExerciciosService;
import com.example.listafitness.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroUsuario extends Fragment {

    EditText mEt_cadastrar_nome;
    EditText mEt_cadastro_senha;

    Button mBt_cadastrar_credenciais;

    List<UsuarioEntry> mUsuarioEntries = new ArrayList();

    public CadastroUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);

        mEt_cadastrar_nome = view.findViewById(R.id.et_cadastro_nome);

        mEt_cadastro_senha = view.findViewById(R.id.et_cadastro_senha);

        mBt_cadastrar_credenciais = view.findViewById(R.id.bt_cadastrar_credenciais);


        mBt_cadastrar_credenciais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String nome = mEt_cadastrar_nome.getText().toString();
                    String senha = mEt_cadastro_senha.getText().toString();

                    UsuarioEntry usuarioEntry = new UsuarioEntry(nome, senha);
                    salvarUsuario(usuarioEntry);

                    mEt_cadastrar_nome.setText("");
                    mEt_cadastro_senha.setText("");

                    Toast.makeText(getContext(), "Usuario Criado", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }


            }
        });


        return view;
    }

    private void salvarUsuario(final UsuarioEntry usuarioEntry) {

        RestClient restClient = RestClient.getInstance();

        ListaExerciciosService listaExerciciosService = restClient.getListaExercicioService();

        try {
            Call<UsuarioEntry> add = listaExerciciosService.add(usuarioEntry);

            add.enqueue(new Callback<UsuarioEntry>() {
                @Override
                public void onResponse(Call<UsuarioEntry> call, Response<UsuarioEntry> response) {

                    UsuarioEntry usuario = response.body();

                    mUsuarioEntries.add(usuario);


                }

                @Override
                public void onFailure(Call<UsuarioEntry> call, Throwable t) {

                }
            });


        } catch (Exception ex) {

        }


    }

}
