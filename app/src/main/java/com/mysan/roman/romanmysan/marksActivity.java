package com.mysan.roman.romanmysan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mysan.roman.romanmysan.R;

import java.util.ArrayList;
import java.util.List;

public class marksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        Bundle tobolek = getIntent().getExtras();
        int liczba = tobolek.getInt("amountOfMarks"); // pobiera liczbe ocen z MainActivity
        final List<ModelOceny> oceny = new ArrayList<>();
        // tworzenie radio buttonow
        for(int i = 0; i <liczba; i++)
        {
            oceny.add(new ModelOceny("ocena " + (i+1)));
        }

        InteraktywnyAdapterTablicy interaktywnyAdapterTablicy = new InteraktywnyAdapterTablicy(this, oceny);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(interaktywnyAdapterTablicy);

        Button srednia = (Button)findViewById(R.id.srednia);
        // przy wcisnieciu przyciska liczy sume ocen oraz srednia
        srednia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double sum = 0;

                for(int i = 0; i < oceny.size(); i++){
                    sum += oceny.get(i).getOcena();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("sredniaOcena", String.valueOf((double) sum / oceny.size())); // przekazuje srednia ocene do MainActivity
                setResult(1,intent);

                marksActivity.this.finish(); // zakonc marksActivity

            }
        });

    }

}
