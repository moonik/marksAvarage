package com.mysan.roman.romanmysan;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 21.03.17.
 */
public class InteraktywnyAdapterTablicy extends ArrayAdapter<ModelOceny>
{
    //przechowujemy referencję do listy ocen
    private List<ModelOceny> listaOcen;
    private Activity kontekst;
    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen)
    {
        super(kontekst, R.layout.activity_mark_buttons, listaOcen);
        this.listaOcen = listaOcen;
        this.kontekst = kontekst;
        //ustawienie wartości pól
    }
    //tworzenie nowego wiersza
    @Override
    public View getView(final int numerWiersza, View widokDoRecyklingu, ViewGroup parent)
    {
        View widok = null;
        //tworzenie nowego wiersza
        final ModelOceny ocena = listaOcen.get(numerWiersza);
        if (widokDoRecyklingu == null)
        {
            //utworzenie layout na podstawie pliku XML
            //wybranie konkretnego przycisku radiowego musi zmieniać dane w modelu
            widok = kontekst.getLayoutInflater().inflate(R.layout.activity_mark_buttons, null);
            ModelOceny modelOceny = getOceny(numerWiersza);
            ((TextView) widok.findViewById(R.id.listaTextEtykieta)).setText(modelOceny.getNazwa());
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId)
                        {
                            RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                            String ocena = checkedRadioButton.getText().toString();
                            listaOcen.get(numerWiersza).setOcena(Integer.parseInt(ocena));
                        }
                    }
            );
            //powiązanie grupy przycisków z obiektem w modelu
        }
        //aktualizacja istniejącego wiersza
        else
        {
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
            //powiązanie grupy przycisków z obiektem w modelu
        }

        TextView etykieta = (TextView) widok.findViewById(R.id.listaTextEtykieta);
        //ustawienie tekstu etykiety na podstawie modelu
        RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
        //zaznaczenie odpowiedniego przycisku na podtawie modelu
        //zwrócenie nowego lub zaktualizowanego wiersza listy
        return widok;
    }

    ModelOceny getOceny(int position) {
        return (ModelOceny) getItem(position);
    }
}