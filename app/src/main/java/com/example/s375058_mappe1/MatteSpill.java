package com.example.s375058_mappe1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatteSpill extends Fragment {
    private String[] questions;
    private String[] answers;
    private int currentQuestionIndex = 0;
    private TextView questionTextView;
    private TextView questionNumberTextView;
    private EditText answerEditText;
    private Button submitButton;
    private int spillLengde;
    private StringBuilder userAnswer = new StringBuilder();  // Holder styr på brukerens inndata
    private List<Integer> questionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mattespill, container, false);

        // Lytter til Android tilbakeknappen som brukes til å trigge dialogboks
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                visAvsluttDialog();
            }
        });

        // Får tilgang til SharedPreferences og henter lengde på spill
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String spillLengdeString = sharedPreferences.getString("game_length", "5");
        spillLengde = Integer.parseInt(spillLengdeString);

        // Hent spørsmålene og svarene fra arrays.xml
        questions = getResources().getStringArray(R.array.matte_sporsmål);
        answers = getResources().getStringArray(R.array.matte_svar);

        //Setter tilfeldig rekkefølge på spørsmål til variabelen questionList
        questionList = randomIndex(spillLengde);

        // Referanse til UI-elementer
        questionTextView = view.findViewById(R.id.questionTextView);
        questionNumberTextView = view.findViewById(R.id.questionNumberTextView);
        answerEditText = view.findViewById(R.id.answerEditText);
        submitButton = view.findViewById(R.id.submitButton);

        // Nummer-knapper
        Button button0 = view.findViewById(R.id.button0);
        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button button4 = view.findViewById(R.id.button4);
        Button button5 = view.findViewById(R.id.button5);
        Button button6 = view.findViewById(R.id.button6);
        Button button7 = view.findViewById(R.id.button7);
        Button button8 = view.findViewById(R.id.button8);
        Button button9 = view.findViewById(R.id.button9);
        Button buttonClear = view.findViewById(R.id.buttonClear);

        // Vis første spørsmål
        showNextQuestion();

        // Når en knapp blir trykket, sendes det tallet til stringbuilder.
        button0.setOnClickListener(v -> appendToAnswer("0"));
        button1.setOnClickListener(v -> appendToAnswer("1"));
        button2.setOnClickListener(v -> appendToAnswer("2"));
        button3.setOnClickListener(v -> appendToAnswer("3"));
        button4.setOnClickListener(v -> appendToAnswer("4"));
        button5.setOnClickListener(v -> appendToAnswer("5"));
        button6.setOnClickListener(v -> appendToAnswer("6"));
        button7.setOnClickListener(v -> appendToAnswer("7"));
        button8.setOnClickListener(v -> appendToAnswer("8"));
        button9.setOnClickListener(v -> appendToAnswer("9"));

        buttonClear.setOnClickListener(v -> clearAnswer());

        // Sjekk svar når brukeren trykker på "Svar"-knappen
        submitButton.setOnClickListener(v -> {
            String userAnswerStr = answerEditText.getText().toString();
            checkAnswer(userAnswerStr);
        });
        if (savedInstanceState != null) {
            // Oppdaterer variabler så de kan bevare tilstanden ved tilstandsendring (rotere tlf etc.)
            currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex");
            questionList = savedInstanceState.getIntegerArrayList("questionList");

            // Gjenopprett brukersvaret hvis nødvendig
            userAnswer = new StringBuilder(savedInstanceState.getString("userAnswer", ""));
            answerEditText.setText(userAnswer.toString());

            // Vis riktig spørsmål basert på gjenopprettet tilstand
            showCurrentQuestion();
        } else {
            // Hvis ingen lagret tilstand, start som normalt
            questionList = randomIndex(spillLengde);
            showNextQuestion();
        }
        return view;
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < spillLengde) {
            // Vis spørsmålsnummer dynamisk
            String questionNumberText = getString(R.string.spørsmål, currentQuestionIndex + 1);
            questionNumberTextView.setText(questionNumberText);

            // Vis spørsmålet
            questionTextView.setText(questions[questionList.get(currentQuestionIndex)]);
            clearAnswer(); // Tøm svarfeltet for neste spørsmål
        } else {
            // Spill ferdig, vis en melding eller gå til resultatvisning
            questionTextView.setText("Spillet er ferdig!");
            questionNumberTextView.setText("");  // Tøm nummerfeltet når spillet er ferdig
            submitButton.setEnabled(false);  // Deaktiver knappen når spillet er ferdig
            visSpillFerdigDialog();
        }
    }

    private void appendToAnswer(String text) {
        userAnswer.append(text);  // Legg til tallet i StringBuilder
        answerEditText.setText(userAnswer.toString());  // Oppdater EditText-feltet
    }

    private void clearAnswer() {
        userAnswer.setLength(0);  // Tøm StringBuilder
        answerEditText.setText("");  // Tøm EditText-feltet
    }

    private void checkAnswer(String userAnswerStr) {
        // Sjekk om svaret er riktig


        String correctAnswer = answers[questionList.get(currentQuestionIndex)];

        if (userAnswerStr.equals(correctAnswer)) {
            Toast.makeText(getActivity(), getRandomSuccessResponse(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), getRandomFailureResponse() + " Riktig svar er " + correctAnswer, Toast.LENGTH_LONG).show();
        }

        // Gå videre til neste spørsmål
        currentQuestionIndex++;
        showNextQuestion();
    }

    //Returnerer en tilfeldig liste med tall fra 1 - 15 med lik lengde som spill-lengde satt i sharedpreference.
    private List<Integer> randomIndex(int spillLengde){
        List<Integer> randomQuestions = new ArrayList<>();
        for (int i = 0; i <= 14; i++){
            randomQuestions.add(i);
        }

        //Bruker Collections.shuffle for å legge tallene i tilfeldig rekkefølge
        Collections.shuffle(randomQuestions);

        return randomQuestions.subList(0,spillLengde);
    }

    //Metode som har et sett med positive responser, og returner tilfeldig respons hver gang.
    private String getRandomSuccessResponse() {
        List<String> successResponses = Arrays.asList(
                "FLOTT!",
                "WOW! RIKTIG!",
                "RIKTIG!",
                "DU ER RÅ!",
                "DET ER RIKTIG!",
                "BRA!"
        );
        Random random = new Random();
        return successResponses.get(random.nextInt(successResponses.size()));
    }

    //Returnerer tilfeldige konstruktive tilbakemeldinger på feil svar
    private String getRandomFailureResponse(){
        List<String> failureResponse = Arrays.asList(
                "NESTEN!",
                "NÆRME!",
                "GODT TENKT, MEN IKKE HELT RETT!",
                "NESTEN! IKKE HELT RIKTIG!"
        );
        Random random = new Random();
        return failureResponse.get(random.nextInt(failureResponse.size()));
    }


    public void visAvsluttDialog() {
        SpillAvsluttDialog dialog = new SpillAvsluttDialog();
        dialog.setDialogClickListener(new SpillAvsluttDialog.DialogClickListener() {
            @Override
            public void onYesClick() {
                // Avslutt aktiviteten
                getParentFragmentManager().popBackStack();
                getParentFragmentManager().popBackStack();
            }

            @Override
            public void onNoClick() {
                // Gjør ingenting
            }
        });
        dialog.show(getParentFragmentManager(), "AvsluttDialog");
    }

    public void visSpillFerdigDialog() {
        SpillFerdigDialog dialog = new SpillFerdigDialog();
        dialog.setDialogClickListener(new SpillFerdigDialog.DialogClickListener() {
            @Override
            public void onYesClick() {
                getParentFragmentManager().popBackStack();
                getParentFragmentManager().popBackStack();
            }
            @Override
            public void onNoClick() {
                getParentFragmentManager().popBackStack();
            }
        });
        dialog.show(getParentFragmentManager(), "SpillFerdigDialog");
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Lagre nåværende spørsmålindeks
        outState.putInt("currentQuestionIndex", currentQuestionIndex);

        // Lagre listen over tilfeldige spørsmål
        outState.putIntegerArrayList("questionList", new ArrayList<>(questionList));

        // Lagre brukersvaret (hvis det er viktig å bevare)
        outState.putString("userAnswer", userAnswer.toString());
    }

    private void showCurrentQuestion() {
        // Vis spørsmålsnummer dynamisk
        String questionNumberText = getString(R.string.spørsmål, currentQuestionIndex + 1);
        questionNumberTextView.setText(questionNumberText);

        // Vis spørsmålet
        questionTextView.setText(questions[questionList.get(currentQuestionIndex)]);
    }

}
