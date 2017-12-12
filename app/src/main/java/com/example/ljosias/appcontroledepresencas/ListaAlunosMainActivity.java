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

import com.example.ljosias.appcontroledepresencas.models.Aluno;
import com.example.ljosias.appcontroledepresencas.models.Presenca;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListaAlunosMainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;
    private Presenca presenca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Alunos");
        setContentView(R.layout.activity_lista_alunos_main);
        listView = (ListView) findViewById(R.id.listViewAlunosPresenca);

        settingsArrayListAdapter = new ArrayList<>();



        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("presenca");
            presenca = new Gson().fromJson(jsonMyObject, Presenca.class);
        }

        for (Aluno aluno : presenca.listaAlunos) {
            settingsArrayListAdapter.add(aluno.nome);
        }



        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                Dialog dialog = new Dialog(ListaAlunosMainActivity.this,android.R.style.Animation_Activity);

                dialog.setContentView(R.layout.detalhes_aluno_presenca);
                dialog.setTitle("Detalhes Aluno");


                TextView nomeTextView = (TextView) dialog.findViewById(R.id.textViewPresencaNomeAluno);
                nomeTextView.setText(presenca.listaAlunos.get(itemPosition).nome);
                TextView nomeCompletoTextView = (TextView) dialog.findViewById(R.id.textViewPresencaNomeCompletoAluno);
                nomeCompletoTextView.setText(presenca.listaAlunos.get(itemPosition).nomeCompleto);

                TextView horaChegadaTextView = (TextView) dialog.findViewById(R.id.textViewDataNascimento);
                horaChegadaTextView.setText(presenca.listaAlunos.get(itemPosition).dataNascimento);

                TextView nTextView = (TextView) dialog.findViewById(R.id.textView102);
                TextView nCTextView = (TextView) dialog.findViewById(R.id.textView101);
                TextView hCTextView = (TextView) dialog.findViewById(R.id.textView100);
                try {
                    dialog.show();
                }
                catch (Exception E){
                    String teste = E.getMessage();

                }


            }
        });

    }
}
