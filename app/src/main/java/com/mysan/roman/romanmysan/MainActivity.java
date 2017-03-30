package com.mysan.roman.romanmysan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName; // imię użytkownika
    private EditText editTextSurName; // nazwisko użytkownika
    private EditText editTextMark; // liczba ocen użytkownika
    private Button marks;
    private boolean isNameCorrect = false; // przyjmuje wartości "false" jeżeli imię wprowadzono źle
    private boolean isSurNameCorrect = false; // przyjmuje wartości "false" jeżeli nazwisko wprowadzono źle
    private boolean isMarksCorrect = false; // przyjmuje wartości "false" jeżeli liczba ocen wprowadzono źle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurName = (EditText) findViewById(R.id.editTextSurName);
        editTextMark = (EditText) findViewById(R.id.editTextMark);
        marks = (Button) findViewById(R.id.marks);
        // ustawiam przycisk Oceny w Invisible dopoki uzytkownik nie wprowadzi poprawne dane
        marks.setVisibility(View.INVISIBLE);

        // przechodzi do funkcji walidacji kiedy focus jest na "false"
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    return;
                }
                checkInputName(editTextName.getText().toString()); // funcka walidacji dla imieni uzytkownika
            }
        });

        // przechodzi do funkcji walidacji kiedy focus jest na "false"
        editTextSurName.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    return;
                }
                checkInputSureName(editTextSurName.getText().toString());// funcka walidacji dla nazwiska uzytkownika
            }
        });

        // przechodzi do funkcji walidacji kiedy focus jest na "false"
        editTextMark.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    return;
                }
                checkInputMarks(editTextMark.getText().toString()); // funkcja walidacji ocen
            }
        });

        // funkcja sprawdza poprawnosc wprowadzanych danych odrazy przy wprowadzaniu
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            // wywoluje funkcje walidacji imieni po wprowadzaniu imieni
            @Override
            public void afterTextChanged(Editable s) {
                checkInputName(editTextName.getText().toString()); // funckja dla sprawdzenia poprawnosci danych
                if(isNameCorrect && isSurNameCorrect && isMarksCorrect) // sprawdzenie poprawności wprowadzonych danych
                {
                    marks.setVisibility(View.VISIBLE); // jeżeli dane są poprawne - pokaż przycisk
                }else
                    marks.setVisibility(View.INVISIBLE); // jeżeli dane są nie poprawne - nie pokazuj przycisk

            }
        });

        // funkcja sprawdza poprawnosc wprowadzanych danych odrazy przy wprowadzaniu
        editTextSurName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            // wywoluje funkcje walidacji nazwiska po wprowadzaniu nazwiska
            @Override
            public void afterTextChanged(Editable s) {
                checkInputSureName(editTextSurName.getText().toString()); // funckja dla sprawdzenia poprawnosci danych
                if(isNameCorrect && isSurNameCorrect && isMarksCorrect) // sprawdzenie poprawności wprowadzonych danych
                {
                    marks.setVisibility(View.VISIBLE); // jeżeli dane są poprawne - pokaż przycisk
                }else
                    marks.setVisibility(View.INVISIBLE); // jeżeli dane są nie poprawne - nie pokazuj przycisk

            }
        });

        // funkcja sprawdza poprawnosc wprowadzanych danych odrazy przy wprowadzaniu
        editTextMark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            // wywoluje funkcje walidacji ocen po wprowadzaniu ocen
            @Override
            public void afterTextChanged(Editable s) {
                checkInputMarks(editTextMark.getText().toString()); // funckja dla sprawdzenia poprawnosci danych
                if(isNameCorrect && isSurNameCorrect && isMarksCorrect) // sprawdzenie poprawności wprowadzonych danych
                {
                    marks.setVisibility(View.VISIBLE); // jeżeli dane są poprawne - pokaż przycisk
                }else
                    marks.setVisibility(View.INVISIBLE); // jeżeli dane są nie poprawne - nie pokazuj przycisk

            }
        });
    }

    /**
     * walidacja pola w ktorym wprowadza sie imie
     * @param input - imie
     */
    public void checkInputName(String input)
    {
        if(input.equals("") || input.indexOf(" ") != -1)
        {
            Toast.makeText(MainActivity.this, "Nie wprowadzono imię!", Toast.LENGTH_SHORT).show();
            isNameCorrect = false;
        }else
            isNameCorrect = true;
    }

    /**
     * walidacja pola w ktorym wprowadza sie nazwisko
     * @param input - nazwisko
     */
    public void checkInputSureName(String input)
    {
        if(input.equals("") || input.indexOf(" ") != -1)
        {
            Toast.makeText(MainActivity.this, "Nie wprowadzono nazwisko!", Toast.LENGTH_SHORT).show();
            isSurNameCorrect = false;
        }else
            isSurNameCorrect = true;
    }

    /**
     * walidacja pola w ktorym wprowadza sie liczba ocen
     * @param input - liczba ocen
     */
    public void checkInputMarks(String input)
    {
        if((input.equals("") || input.indexOf(" ") != -1)  ||(Integer.parseInt(input) < 5 || Integer.parseInt(input) > 15))
        {
            Toast.makeText(MainActivity.this, "Źle wprowadzono ocene!", Toast.LENGTH_SHORT).show();
            isMarksCorrect = false;
        }else
            isMarksCorrect = true;
    }

    /**
     * funkcja ktora przechodzi do listy ocen
     * @param view
     */
    public void goToMarks(View view)
    {
        Intent intent = new Intent(this, marksActivity.class);
        try {
            EditText editTextMark = (EditText) findViewById(R.id.editTextMark);
            int mark = Integer.parseInt(editTextMark.getText().toString()); // pobiera liczbe ocen
            intent.putExtra("amountOfMarks", mark); // przekazuje ta liczbe do marksActivity
            startActivityForResult(intent, 13);
        }catch (NumberFormatException ex){}

    }

    // otrzymuje srednia ocene z marksActivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // blokada wszystkich pol
        editTextName.setEnabled(false);
        editTextSurName.setEnabled(false);
        editTextMark.setEnabled(false);

        Bundle extras = data.getExtras();
        String srednia = extras.getString("sredniaOcena"); // pobieranie sredniej oceny

        final double avarageMark = Double.parseDouble(srednia); // srednia ocena

        //wyswietlenie sredniej oceny
        TextView info = (TextView) findViewById(R.id.showSrednia);
        info.setText("Twoja średnia to: " + avarageMark);
        info.setVisibility(View.VISIBLE);

        // przy wcisnieciu przyciska wyswietla sie odpowiednio tekst
        // tekst jest wyswitlany w zaleznosci od sredniej oceny
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (avarageMark >= 3) {// sprawdzenie sredniej oceny
                    Toast toast = Toast.makeText(getApplicationContext(), "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 300); // polozenie wyswietlanego tekstu
                    toast.show(); // wyswietlenie tekstu
                    //onBackPressed();
                    return;
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wysylam o zaliczenie warunkowe", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 300); // polozenie wyswietlanego tekstu
                    toast.show(); // wyswietlenie tekstu
                    //onBackPressed();
                    return;
                }

            }
        });
        // sprawdzenie sredniej oceny
        // wyswietlenie tekstu w zaleznosci od oceny
        if (avarageMark >= 3) {
            marks.setText("Super:)");
        } else {
            marks.setText("Tym razem mi nie poszlo. ");
        }
    }
}

