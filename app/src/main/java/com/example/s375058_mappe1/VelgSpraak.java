package com.example.s375058_mappe1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class VelgSpraak extends Fragment {
    View view;
    Button btnNorsk, btnTysk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.velg_spraak, container, false);

        // Hent referanser til knappene
        btnNorsk = (Button) view.findViewById(R.id.btnNorsk);
        btnTysk = (Button) view.findViewById(R.id.btnTysk);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Velg språk
        btnNorsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("no-NO");
                AppCompatDelegate.setApplicationLocales(appLocale);
                editor.putString("language_preference", "no-NO");  // Lagrer språkinnstilling
                editor.apply();
                Toast.makeText(getActivity(), getString(R.string.SpråkValgtNorsk), Toast.LENGTH_SHORT).show();
            }
        });

        btnTysk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("de-DE");
                AppCompatDelegate.setApplicationLocales(appLocale);
                editor.putString("language_preference", "de-DE");
                editor.apply();
                Toast.makeText(getActivity(), getString(R.string.SpråkValgtTysk), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
