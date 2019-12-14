package com.example.listafitness.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listafitness.R;
import com.example.listafitness.entities.ListaExercicioEntry;

import java.util.List;

public class ListaExercicioAdapter extends RecyclerView.Adapter<ListaExercicioAdapter.ListaExercicioViewHolder> {
    private List<ListaExercicioEntry> mListaExercicio;

    @NonNull
    @Override
    public ListaExercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_treino, parent, false);

        return new ListaExercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaExercicioViewHolder holder, int position) {
        ListaExercicioEntry listaExercicio = mListaExercicio.get(position);
        holder.bind(listaExercicio);
    }

    @Override
    public int getItemCount() {

        if (mListaExercicio != null) {
            return mListaExercicio.size();


        }
        return 0;
    }


    public List<ListaExercicioEntry> getListaEspera() {
        return mListaExercicio;
    }


    public void setmListaExercicio(List<ListaExercicioEntry> listaExercicio) {

        mListaExercicio = listaExercicio;
        this.notifyDataSetChanged();

    }


    class ListaExercicioViewHolder extends RecyclerView.ViewHolder {

        TextView mNomeExercicio;
        TextView mTv_quantidade_series;
        TextView mTv_quantidade_repeticoes;
        TextView mTv_nome_treino;

        public ListaExercicioViewHolder(View view) {
            super(view);
            mNomeExercicio = view.findViewById(R.id.tv_nome_exercicio);
            mTv_quantidade_series = view.findViewById(R.id.tv_quantidade_series);
            mTv_quantidade_repeticoes = view.findViewById(R.id.tv_quantidade_repeticoes);
            mTv_nome_treino = view.findViewById(R.id.tv_nome_treino);

        }


        public void bind(ListaExercicioEntry listaExercicio) {
            mNomeExercicio.setText(listaExercicio.getNomeExercicio());
            mTv_nome_treino.setText(listaExercicio.getNomeLista());
            Log.v("teste","Lista: "+listaExercicio.getNomeLista());

            Log.v("teste","Lista Exercicio: "+listaExercicio.getNomeExercicio());
            mTv_quantidade_series.setText(String.valueOf(listaExercicio.getNumeroSeries()));
            mTv_quantidade_repeticoes.setText(String.valueOf(listaExercicio.getNumeroRepeticoes()));

        }


    }


}










