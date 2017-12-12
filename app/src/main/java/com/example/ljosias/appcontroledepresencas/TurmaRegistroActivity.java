package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class TurmaRegistroActivity extends AppCompatActivity {

    EditText EditTNome,EditTHoraInicial, EditTHoraFinal,EditTDataInicial,EditTDataFinal;
    Button buttonSalvar,buttonDelete;
    Turma turma;
    int id ;
    Spinner turmaProfessorSpinner;
    List<Professor> listProfessor = null;
    int positionProfessor ;
    Curso curso = null ;
    String jsonMyObject = null;
    Call<List<Professor>> call = null;
//    ArrayList<String> professorList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_registro);
        setTitle("Adicionar Nova Turma");
        EditTNome = (EditText) findViewById(R.id.editTextTNome);
        EditTHoraInicial= (EditText) findViewById(R.id.editTextTHoraInicial);
        EditTHoraFinal = (EditText) findViewById(R.id.editTextTHoraFinal);
        EditTDataInicial = (EditText) findViewById(R.id.editTexttextViewTDataInicial);
        EditTDataFinal = (EditText) findViewById(R.id.editTextTDataFinal);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarTurma);
        buttonDelete = (Button) findViewById(R.id.buttonApagarTurma);
        turmaProfessorSpinner = (Spinner) findViewById(R.id.spinnerTProfessor);


        final ArrayList<String> professorList = new ArrayList<>();

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("turma");
            if(jsonMyObject != null) {
                turma = new Gson().fromJson(jsonMyObject, Turma.class);
                Toast.makeText(getApplicationContext(), "" + turma.nomeTurma, Toast.LENGTH_LONG).show();
                EditTNome.setText(turma.nomeTurma);
                EditTHoraInicial.setText(turma.horaInicial);
                EditTHoraFinal.setText(turma.horaFinal);
                EditTDataInicial.setText(turma.dataInicio);
                EditTDataFinal.setText(turma.dataTermino);
                id = turma.turmaId;
            }
        }

        curso = new Gson().fromJson(extras.getString("curso"), Curso.class);


        new Thread(new Runnable()
        {
            public void run() {
                IProfessorService professorService;
                professorService = Utils.getProfessorService();

                if (jsonMyObject != null){

                    call = professorService.getProfessoresOrdenado(turma.professor.professorId);
                }
                else{
                    call = professorService.getProfessores();
                }

                try {
                    listProfessor = call.execute().body();

                    if ( listProfessor != null ){
                        for ( Professor p : listProfessor ) {
                            professorList.add(p.nomeCompleto);

                        }



                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }).start();

        try {
            sleep(1200);
        } catch (InterruptedException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }

//        professorList.add("Teste");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, professorList);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        turmaProfessorSpinner.setAdapter(spinnerArrayAdapter);

        turmaProfessorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String nsvNome = turmaProfessorSpinner.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), nsvNome, Toast.LENGTH_LONG).show();
                positionProfessor =  turmaProfessorSpinner.getSelectedItemPosition() ;
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        if (  jsonMyObject == null  )
        {
                buttonDelete.setEnabled(false);
        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turma == null) {
                    turma = new Turma();
                }

                ITurmaService turmaService;

                turmaService = Utils.getTurmaService();

                Call<Log> call = turmaService.deleteTurma(turma.turmaId);
                call.enqueue(new Callback<Log>() {
                    @Override
                    public void onResponse(Call<Log> call, Response<Log> response) {
                        if (response.isSuccessful()) {
                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
                            Toast.makeText(getApplicationContext(), "Deletou "+ response.body().getmessage(), Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Log> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"ERRo "+ t.getMessage(), Toast.LENGTH_LONG).show();

                    }



                });
            }
        });


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turma == null) {
                    turma = new Turma();
                }

                turma.nomeTurma = EditTNome.getText().toString();
                turma.dataInicio = EditTDataInicial.getText().toString();
                turma.dataTermino = EditTDataFinal.getText().toString();
                turma.horaInicial = EditTHoraInicial.getText().toString();
                turma.horaFinal = EditTHoraFinal.getText().toString();
                turma.professor =  listProfessor.get(positionProfessor);
                turma.curso = curso ;

                ITurmaService turmaService;

                turmaService = Utils.getTurmaService();

                Call<Log> call = turmaService.addTurma(turma);
                call.enqueue(new Callback<Log>() {
                    @Override
                    public void onResponse(Call<Log> call, Response<Log> response) {
                        if (response.isSuccessful()) {
                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
                            Toast.makeText(getApplicationContext(), "Salvou " + response.body().getmessage(), Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Log> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                    }


                });
            }
        });


    }
}
