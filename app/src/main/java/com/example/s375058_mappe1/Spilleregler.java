package com.example.s375058_mappe1;

import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.preference.PreferenceManager;

public class Spilleregler extends Fragment {
    View view;
    Button btnFemSpill, btnTiSpill, btnFemtenSpill;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.spilleregler, container, false);

        // Hent referanser til knappene
        btnFemSpill = (Button) view.findViewById(R.id.btnFemSpill);
        btnTiSpill = (Button) view.findViewById(R.id.btnTiSpill);
        btnFemtenSpill = (Button) view.findViewById(R.id.btnFemtenSpill);

        // Bruk SharedPreferences for å lagre spilleregler
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        btnFemSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("game_length", "5");  // Lagrer valg av 5 spill
                editor.apply();
                Toast.makeText(getActivity(), getString(R.string.femSpill), Toast.LENGTH_SHORT).show();
            }
        });

        btnTiSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("game_length", "10");  // Lagrer valg av 10 spill
                editor.apply();
                Toast.makeText(getActivity(), getString(R.string.tiSpill), Toast.LENGTH_SHORT).show();
            }
        });

        btnFemtenSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("game_length", "15");  // Lagrer valg av 15 spill
                editor.apply();
                Toast.makeText(getActivity(), getString(R.string.femtenSpill), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
