package com.example.listafitness.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.listafitness.R;
import com.example.listafitness.entities.ListaExercicioEntry;
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
public class CadastrarListaTreinosFragment extends Fragment {
    Button m_Bt_cadastrar;
    EditText m_Et_nome_exercicio;
    EditText m_Et_cadastrar_treino;

    EditText m_Et_series;

    EditText m_Et_repeticoes;

    Login login = new Login();
    int id;
    List<ListaExercicioEntry> mListaExercicio = new ArrayList();

    public CadastrarListaTreinosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_lista_treinos, container, false);

        m_Bt_cadastrar = view.findViewById(R.id.bt_cadastrar_credenciais);
        m_Et_nome_exercicio = view.findViewById(R.id.et_cadastrar_exercicio);
        m_Et_cadastrar_treino = view.findViewById(R.id.et_cadastrar_treino);



        m_Et_series = view.findViewById(R.id.et_series);
        m_Et_repeticoes = view.findViewById(R.id.et_repeticoes);


        id = login.getIdSecao();


        m_Bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {



                    int np_serie = Integer.parseInt(m_Et_series.getText().toString());
                    int np_repeticao = Integer.parseInt(m_Et_repeticoes.getText().toString());

                    String nomeExercicio = m_Et_nome_exercicio.getText().toString();
                    String nomeTreino = m_Et_cadastrar_treino.getText().toString();
                    ListaExercicioEntry listaExercicio = new ListaExercicioEntry(nomeExercicio, nomeTreino, np_serie, np_repeticao,id);

                    salvarListaExercicio(listaExercicio);

                    m_Et_nome_exercicio.setText("");
                    m_Et_cadastrar_treino.setText("");
                   m_Et_series.setText("");
                   m_Et_repeticoes.setText("");


                    Toast.makeText(getContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {

                }
            }
        });


        return view;
    }


    private void salvarListaExercicio(final ListaExercicioEntry listaExercicio) {

        RestClient restClient = RestClient.getInstance();

        ListaExerciciosService listaExerciciosService = restClient.getListaExercicioService();

        try {
            Call<ListaExercicioEntry> add = listaExerciciosService.add(listaExercicio);

            add.enqueue(new Callback<ListaExercicioEntry>() {
                @Override
                public void onResponse(Call<ListaExercicioEntry> call, Response<ListaExercicioEntry> response) {
                    ListaExercicioEntry listaExercicio = response.body();


                    mListaExercicio.add(listaExercicio);


                }

                @Override
                public void onFailure(Call<ListaExercicioEntry> call, Throwable t) {

                }
            });


        } catch (Exception ex) {

        }


    }


}
