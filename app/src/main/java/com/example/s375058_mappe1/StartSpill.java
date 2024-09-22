package com.example.s375058_mappe1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class StartSpill extends Fragment {
    View view;
    private Button submit;
    private String[] question;
    private String[] answer;
    private int questionTracker;
    private TextView questionTextView;
    private EditText answerEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.start_spill, container, false);

        //Kaller loadfragmet-metoden fra MainActivity og viser Start-spill-fragmentet.
        Button startSpill = (Button) view.findViewById(R.id.btnKlar);
        startSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new MatteSpill());
            }
        });

        return view;
    }
}