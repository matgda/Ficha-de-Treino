package com.example.listafitness.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.listafitness.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CronometroFragment extends Fragment {

    Chronometer mChronometer;
    Button mIniciar;
    Button mParar;
    private boolean isResume;
    Handler handler;
    long tMilliSec,tStart,tBuff,tUpdate = 0L;
    int sec,min,milliSec;

    public CronometroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_cronometro, container, false);

        mChronometer = view.findViewById(R.id.ch_cronometro);
        mIniciar = view.findViewById(R.id.bt_iniciar);
        mParar =view.findViewById(R.id.bt_parar);
        mParar.setVisibility(View.GONE);
        handler = new Handler();

        mIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isResume){
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    mChronometer.start();
                    isResume = true;
                    mIniciar.setText("Pausar");


                    mParar.setVisibility(View.GONE);
                }
                else {
                    tBuff += tMilliSec;
                    handler.removeCallbacks(runnable);
                    mChronometer.stop();
                    isResume = false;
                    mParar.setVisibility(View.VISIBLE);
                    mIniciar.setText("Iniciar");


                }


            }




        });

        mParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isResume){

                    mIniciar.setText("Iniciar");
                    tBuff = 0L;
                    tMilliSec = 0L;
                    tStart = 0L;
                    tUpdate = 0L;
                    sec = 0;
                    min = 0;
                    milliSec = 0;
                    mChronometer.setText("00:00:00");
                    mParar.setVisibility(View.GONE);

                }
            }
        });



        return view;
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMilliSec = SystemClock.uptimeMillis() - tStart;
            tUpdate = tBuff + tMilliSec;
            sec = (int) (tUpdate/1000);
            min = sec/60;
            sec = sec%60;
            milliSec = (int) (tUpdate%100);
            mChronometer.setText(String.format("%02d",min)+":" +String.format("%02d",sec)+":" +String.format("%02d",milliSec));

            handler.postDelayed(this,30);
        }
    };







}
