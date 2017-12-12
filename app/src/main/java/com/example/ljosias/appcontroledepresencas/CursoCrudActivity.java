package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.services.ICursoService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;
import static java.security.AccessController.getContext;

public class CursoCrudActivity extends AppCompatActivity {

    EditText editTextNome , editTextDescricao ;
    CheckBox checkBoxAtivo ;
    Button buttonSalvar, buttonDetails;
    Button buttonDelete;
    Curso curso;
    int id ;
    Spinner turmaSpinner;
    FloatingActionButton buttonTurmaAdd ;
    Turma turmaAux = null ;
    int positionTurma = 0 ;
    Call<List<Turma>> call = null;
    List<Turma> listTurmas = null;
    String jsonMyObject = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_crud);
        setTitle("Adicionar Novo Curso");
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        checkBoxAtivo = (CheckBox) findViewById(R.id.checkBoxAtivo);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarCurso);
        buttonDelete = (Button) findViewById(R.id.buttonApagarCurso);
        turmaSpinner = (Spinner) findViewById(R.id.spinnerTurmas);
        buttonTurmaAdd = (FloatingActionButton) findViewById(R.id.floatingActionButtonCursoAdd);
        buttonDetails = (Button) findViewById(R.id.buttonTurmaDetail);

        final ArrayList<String> turmaListAdapter = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("curso");
            curso = new Gson().fromJson(jsonMyObject, Curso.class);
            Toast.makeText(getApplicationContext(), "" + curso.nome, Toast.LENGTH_LONG).show();
            editTextNome.setText(curso.nome);
            editTextDescricao.setText(curso.descricao);
            checkBoxAtivo.setChecked(curso.ativo);
            id = curso.cursoId;

        }
//

        new Thread(new Runnable()
        {
            public void run() {
                ITurmaService turmaService;
                turmaService = Utils.getTurmaService();

                if ( jsonMyObject != null ) {
                    call = turmaService.getTurmasPeloId(curso.cursoId);
                }else{
                    call = null;
                }

                try {
                    listTurmas = call.execute().body();
                    if ( listTurmas != null ){
                        for ( Turma p : listTurmas ) {
                            turmaListAdapter.add(p.nomeTurma);
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, turmaListAdapter);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        turmaSpinner.setAdapter(spinnerArrayAdapter);

        turmaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String nsvNome = turmaSpinner.getSelectedItem().toString();
                        Toast.makeText(getBaseContext(), nsvNome, Toast.LENGTH_LONG).show();
                positionTurma =  turmaSpinner.getSelectedItemPosition() ;
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });





        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curso == null) {
                    curso = new Curso();
                }
                curso.nome = editTextNome.getText().toString();
                curso.descricao =editTextDescricao.getText().toString();
                curso.ativo = checkBoxAtivo.isChecked();


                ICursoService cursoService;

                cursoService = Utils.getCursoService();

                Call<Curso> call = cursoService.addCurso(curso);
                call.enqueue(new Callback<Curso>() {
                    @Override
                    public void onResponse(Call<Curso> call, Response<Curso> response) {
                        if (response.isSuccessful()) {
                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);

                            myIntent.putExtra("curso", new Gson().toJson(curso));

                            Toast.makeText(getApplicationContext(), "Salvou ", Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Curso> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                    }


                });
            }
        });



        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curso == null) {
                    curso = new Curso();
                }

                ICursoService cursoService;

                cursoService = Utils.getCursoService();

                Call<Curso> call = cursoService.deleteCurso(curso.cursoId);
                call.enqueue(new Callback<Curso>() {
                    @Override
                    public void onResponse(Call<Curso> call, Response<Curso> response) {
                        if (response.isSuccessful()) {
                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
                            Toast.makeText(getApplicationContext(), "Deletou ", Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Curso> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"ERRo "+ t.getMessage(), Toast.LENGTH_LONG).show();
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                    }



                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonCursoAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listTurmas.size() != 0) {
                    turmaAux = listTurmas.get(positionTurma);
                }
//                Toast.makeText(getBaseContext() , "Nova Turma: "+turmaAux.nomeTurma, Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(getBaseContext(), TurmaRegistroActivity.class);
                myIntent.putExtra("curso", new Gson().toJson(curso));
                startActivityForResult(myIntent, 0);

            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                turmaAux = listTurmas.get(positionTurma);

                Intent myIntent = new Intent(getBaseContext(), TurmaRegistroActivity.class);

                myIntent.putExtra("turma", new Gson().toJson(turmaAux));
                myIntent.putExtra("curso", new Gson().toJson(curso));
                startActivityForResult(myIntent, 0);

            }
        });
    }




}
