package com.example.ljosias.appcontroledepresencas;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaAlunosMainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos_main);
        listView = (ListView) findViewById(R.id.listViewAlunosPresenca);

        settingsArrayListAdapter = new ArrayList<>();

        settingsArrayListAdapter.add("Josias Kayan");
        settingsArrayListAdapter.add("Sammya Oliveira");
        settingsArrayListAdapter.add("Ismael Ferreira");
        settingsArrayListAdapter.add("Patrícia Franco");


        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                Dialog dialog = new Dialog(getBaseContext());
                dialog.setContentView(R.layout.detalhes_aluno_presenca);
                dialog.setTitle("Detalhes Aluno");


                TextView nomeTextView = (TextView) dialog.findViewById(R.id.textViewPresencaNomeAluno);
                TextView nomeCompletoTextView = (TextView) dialog.findViewById(R.id.textViewPresencaNomeCompletoAluno);
                TextView horaChegadaTextView = (TextView) dialog.findViewById(R.id.textViewHoraChegada);
                TextView nTextView = (TextView) dialog.findViewById(R.id.textView102);
                TextView nCTextView = (TextView) dialog.findViewById(R.id.textView101);
                TextView hCTextView = (TextView) dialog.findViewById(R.id.textView100);

                dialog.show();

//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
//                Toast.makeText(getBaseContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();

            }
        });

    }
}
