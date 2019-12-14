package com.example.listafitness.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listafitness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuPrincipal extends AppCompatActivity {
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteiner);
        getSupportActionBar().setTitle("Lista de Treinos");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ListaTreinoFragment()).commit();

        navigationView = findViewById(R.id.navigation_view);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(savedInstanceState == null){

                }

                switch (menuItem.getItemId()) {
                    case R.id.navigation_adicionar: {
                        getSupportActionBar().setTitle("Cadastrar novo Treino");

                         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new CadastrarListaTreinosFragment()).commit();

                        break;
                    }
                    case R.id.navigation_lista: {
                        getSupportActionBar().setTitle("Lista de Treinos");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ListaTreinoFragment()).commit();
                        break;
                    }
                    case R.id.navigation_time: {
                        getSupportActionBar().setTitle("Cronometro");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CronometroFragment()).commit();
                        break;
                    }

                }
                return  true;

            }
        });


    }

     }
