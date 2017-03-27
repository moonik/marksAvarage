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

    private EditText editTextName;
    private EditText editTextSureName;
    private EditText editTextMark;
    private Button marks;
    private boolean isNameCorrect = false;
    private boolean isSureNameCorrect = false;
    private boolean isMarksCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSureName = (EditText) findViewById(R.id.editTextSureName);
        editTextMark = (EditText) findViewById(R.id.editTextMark);
        marks = (Button) findViewById(R.id.marks);
        marks.setVisibility(View.INVISIBLE);

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    return;
                }
                checkInputName(editTextName.getText().toString());
            }
        });

        editTextSureName.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    return;
                }
                checkInputSureName(editTextSureName.getText().toString());
            }
        });

        editTextMark.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    return;
                }
                checkInputMarks(editTextMark.getText().toString());
            }
        });

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputName(editTextName.getText().toString());
                if(isNameCorrect && isSureNameCorrect && isMarksCorrect)
                {
                    marks.setVisibility(View.VISIBLE);
                }else
                    marks.setVisibility(View.INVISIBLE);

            }
        });

        editTextSureName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputSureName(editTextSureName.getText().toString());
                if(isNameCorrect && isSureNameCorrect && isMarksCorrect)
                {
                    marks.setVisibility(View.VISIBLE);
                }else
                    marks.setVisibility(View.INVISIBLE);

            }
        });

        editTextMark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputMarks(editTextMark.getText().toString());
                if(isNameCorrect && isSureNameCorrect && isMarksCorrect)
                {
                    marks.setVisibility(View.VISIBLE);
                }else
                    marks.setVisibility(View.INVISIBLE);

            }
        });
    }

    public void checkInputName(String input)
    {
        if(input.equals("") || input.indexOf(" ") != -1)
        {
            Toast.makeText(MainActivity.this, "Nie wprowadzono imię!", Toast.LENGTH_SHORT).show();
            isNameCorrect = false;
        }else
            isNameCorrect = true;
    }

    public void checkInputSureName(String input)
    {
        if(input.equals("") || input.indexOf(" ") != -1)
        {
            Toast.makeText(MainActivity.this, "Nie wprowadzono nazwisko!", Toast.LENGTH_SHORT).show();
            isSureNameCorrect = false;
        }else
            isSureNameCorrect = true;
    }

    public void checkInputMarks(String input)
    {
        if((input.equals("") || input.indexOf(" ") != -1)  ||(Integer.parseInt(input) < 5 || Integer.parseInt(input) > 15))
        {
            Toast.makeText(MainActivity.this, "Źle wprowadzono ocene!", Toast.LENGTH_SHORT).show();
            isMarksCorrect = false;
        }else
            isMarksCorrect = true;
    }

    public void goToMarks(View view)
    {
        Intent intent = new Intent(this, marksActivity.class);
        try {
            EditText editTextMark = (EditText) findViewById(R.id.editTextMark);
            int mark = Integer.parseInt(editTextMark.getText().toString());
            intent.putExtra("amountOfMarks", mark);
            startActivityForResult(intent, 13);
        }catch (NumberFormatException ex){}

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        editTextName.setEnabled(false);
        editTextSureName.setEnabled(false);
        marks.setEnabled(false);
        editTextMark.setEnabled(false);

        Bundle extras = data.getExtras();
        String srednia = extras.getString("srednia_ocena");

        final double avarageMark = Double.parseDouble(srednia);

        TextView info = (TextView) findViewById(R.id.showSrednia);
        info.setText("Twoja średnia to: " + avarageMark);
        info.setVisibility(View.VISIBLE);

        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (avarageMark >= 3) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 300);
                    toast.show();
                    //onBackPressed();
                    return;
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wysylam o zaliczenie warunkowe", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 300);
                    toast.show();
                    //onBackPressed();
                    return;
                }

            }
        });

        if (avarageMark >= 3) {
            marks.setText("Super:)");
        } else {
            marks.setText("Tym razem mi nie poszlo. ");
        }
    }
}

