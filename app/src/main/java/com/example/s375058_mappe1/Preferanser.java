package com.example.s375058_mappe1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;


public class Preferanser extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.preferanser, container, false);

        //Kaller loadfragmet-metoden fra MainActivity og viser Spilleregler-fragmentet.
        Button btnSpilleregler = (Button) view.findViewById(R.id.btnSpilleregler);
        btnSpilleregler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new Spilleregler());
            }
        });

        //Kaller loadfragmet-metoden fra MainActivity og viser VelgSpraak-fragmentet.
        Button btnVelgSpraak = (Button) view.findViewById(R.id.btnVelgSpraak);
        btnVelgSpraak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new VelgSpraak());
            }
        });

        return view;
    }

}