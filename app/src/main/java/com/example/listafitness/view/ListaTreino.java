package com.example.listafitness.view;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listafitness.R;
import com.example.listafitness.adapter.ListaExercicioAdapter;
import com.example.listafitness.entities.ListaExercicioEntry;
import com.example.listafitness.rest.ListaExerciciosService;
import com.example.listafitness.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaTreino extends AppCompatActivity {

    RecyclerView mListaExercicioRecyclerView;
    ListaExercicioAdapter mlistaExercicioAdapter;
    LinearLayout mListaExercicioView;
    ProgressBar mIndicadorCarregarProgressBar;

    List<ListaExercicioEntry> mListaExercicio;
    ListaExerciciosService mListaExerciciosService;
    RestClient restClient = RestClient.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista_treino);

        mListaExercicioRecyclerView = findViewById(R.id.rv_lista_exercicio);

        mListaExercicioRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mlistaExercicioAdapter = new ListaExercicioAdapter();

        mListaExercicioRecyclerView.setAdapter(mlistaExercicioAdapter);
        mListaExercicio = new ArrayList<>();


        configurarWebservice();
        carregarDados();
        configurarRemocao();

    }

    private void configurarWebservice() {

        restClient = RestClient.getInstance();

        mListaExerciciosService = restClient.getListaExercicioService();
    }

    private void carregarDados() {




        ListaExerciciosService listaExerciciosService = restClient.getListaExercicioService();

        Call<List<ListaExercicioEntry>> list = listaExerciciosService.list();

        list.enqueue(new Callback<List<ListaExercicioEntry>>() {
            @Override
            public void onResponse(Call<List<ListaExercicioEntry>> call, Response<List<ListaExercicioEntry>> response) {
                mListaExercicio = response.body();

                mlistaExercicioAdapter.setmListaExercicio(mListaExercicio);


            }

            @Override
            public void onFailure(Call<List<ListaExercicioEntry>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


    private void configurarRemocao() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                List<ListaExercicioEntry> listaExercicioList = mlistaExercicioAdapter.getListaEspera();

                int position = viewHolder.getAdapterPosition();

                ListaExercicioEntry listaExercicio = listaExercicioList.get(position);

                Call<Void> remove = mListaExerciciosService.remove(listaExercicio.getId());

                remove.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        carregarDados();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }).attachToRecyclerView(mListaExercicioRecyclerView);
    }


}
