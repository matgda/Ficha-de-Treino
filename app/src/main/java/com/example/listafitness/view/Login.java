package com.example.listafitness.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.listafitness.R;
import com.example.listafitness.entities.UsuarioEntry;
import com.example.listafitness.rest.ListaExerciciosService;
import com.example.listafitness.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    Button mBtlogar;
    TextView mTvcriar_senha;

    TextView mTvusuario;
    TextView mTvsenha;
    TextView mTvCronometro;
    UsuarioEntry mUsuarios;
    TextView guess;
 public  static int idSecao;

    public int getIdSecao() {
        return idSecao;
    }

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mBtlogar = view.findViewById(R.id.bt_logar);

        mTvcriar_senha = view.findViewById(R.id.tv_criar_senha);

        mTvusuario = view.findViewById(R.id.tv_usuario);

        mTvsenha = view.findViewById(R.id.tv_senha);

        mTvCronometro = view.findViewById(R.id.tv_cronometro);



        mBtlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String usuario = mTvusuario.getText().toString();
                    String senha = mTvsenha.getText().toString();
                    UsuarioEntry usuarioEntry = new UsuarioEntry(usuario,senha);
                    validarSenha(usuarioEntry);


                }
                catch (Exception e){

                }
}





        });

        mTvcriar_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_login2_to_cadastro_usuario);
            }
        });


        mTvCronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_login2_to_cronometroFragment);
            }
        });





        return view;
    }





    public void validarSenha( UsuarioEntry usuarioEntry) {
        RestClient restClient = RestClient.getInstance();
        ListaExerciciosService listaExerciciosService = restClient.getListaExercicioService();

        Call<UsuarioEntry> listCall = listaExerciciosService.validar(usuarioEntry);

            listCall.enqueue(new Callback<UsuarioEntry>() {
                @Override
                public void onResponse(Call<UsuarioEntry> call, Response<UsuarioEntry> response) {
                        mUsuarios =    response.body();
                        idSecao = response.body().getId();

                    Log.v("teste","id: "+ idSecao);


                            if(mUsuarios != null){
                                         idSecao = mUsuarios.getId();

                                        startActivity(new Intent(getActivity(), MenuPrincipal.class));

                            }
                            else {
                                Toast.makeText(getContext(),"Credenciais Inválidas",Toast.LENGTH_SHORT).show();
                            }
                }

                @Override
                public void onFailure(Call<UsuarioEntry> call, Throwable t) {


                    Toast.makeText(getContext(),"Falha ao conectar",Toast.LENGTH_SHORT).show();

                }
            });










    }


}
