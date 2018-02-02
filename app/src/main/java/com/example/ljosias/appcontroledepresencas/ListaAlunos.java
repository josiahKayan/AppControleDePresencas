package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.adaptersexpandable.ChamadaAdapter;
import com.example.ljosias.appcontroledepresencas.models.Aluno;
import com.example.ljosias.appcontroledepresencas.models.Presenca;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.services.IPresencaService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

import static java.lang.Thread.sleep;

public class ListaAlunos extends AppCompatActivity {

    private Turma turma = null;
    private List<Aluno> listaAlunos = null;
    private  ListView listViewAlunos = null;
    private ArrayList<String> settingsArrayListAdapter;
    Call<List<Aluno>> call = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos_resume);

        listViewAlunos = (ListView) findViewById(R.id.listviewAlunos) ;

        setTitle("Turmas Ativas");


        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            jsonMyObject = extras.getString("turma");
            turma = new Gson().fromJson(jsonMyObject, Turma.class);
        }

        settingsArrayListAdapter = new ArrayList<>();


        //Captura a lista de Presenças
        final ITurmaService turmaService;
        turmaService = Utils.getTurmaService();

        new Thread(new Runnable()
        {
            public void run() {
                Call<List<Aluno>> call = turmaService.getAlunoPorTurmaId(turma.turmaId);
                try {
                    listaAlunos = call.execute().body();
                    if ( listaAlunos != null ){
                        for ( Aluno p : listaAlunos ) {
                            settingsArrayListAdapter.add(p.nome);
                        }
                    }
                } catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }).start();

        try {
            while(call == null) {
                sleep(3000);
            }
        } catch (InterruptedException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }


        listViewAlunos.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));

        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;



                Intent myIntent = new Intent(getBaseContext(), AlunoTurmaDetalhes.class);
                myIntent.putExtra("turma", new Gson().toJson(turma));
                startActivityForResult(myIntent, 0);

            }
        });




    }
}
