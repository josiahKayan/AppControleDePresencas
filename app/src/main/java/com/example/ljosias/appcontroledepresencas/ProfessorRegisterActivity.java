package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class ProfessorRegisterActivity extends AppCompatActivity {

    EditText EditTextNome,EditTextNomeCompleto, EditTextIdade,EditTextName, EditTextPasword,EditTextDataNascimento;
    Button buttonSalvar,buttonDelete;
    Professor professor;
    int id ;
    Spinner turmasSpinner;
    Call<List<Turma>> call = null;
    List<Turma> lisTurmas = null;
    String jsonMyObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_register);
        setTitle("Registro de Professor");

        EditTextNome = (EditText) findViewById(R.id.editTextNome);
        EditTextNomeCompleto= (EditText) findViewById(R.id.editTextNomeCompleto);
        EditTextIdade = (EditText) findViewById(R.id.editTextIdade);
        EditTextDataNascimento = (EditText) findViewById(R.id.editTextDataNascimento);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarProfessor);
        buttonDelete = (Button) findViewById(R.id.buttonApagarProfessor);
        EditTextName = (EditText) findViewById(R.id.editTextProfName);
        EditTextPasword = (EditText) findViewById(R.id.editTextProfSenha);
        turmasSpinner = (Spinner) findViewById(R.id.spinner);

        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("professor");
            professor = new Gson().fromJson(jsonMyObject, Professor.class);
            Toast.makeText(getApplicationContext(), "" + professor.nome, Toast.LENGTH_LONG).show();
            EditTextNome.setText(professor.nome);
            EditTextNomeCompleto.setText(professor.nomeCompleto);
            EditTextIdade.setText(professor.idade);
            EditTextName.setText(professor.usuario.Email);
            EditTextDataNascimento.setText(professor.dataNascimento.toString());
            id = professor.professorId;
        }

        List<String> listTurmas = new ArrayList<>();

        final ArrayList<String> turmaListAdapter = new ArrayList<>();

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            jsonMyObject = extras.getString("curso");
//            curso = new Gson().fromJson(jsonMyObject, Curso.class);
//            Toast.makeText(getApplicationContext(), "" + curso.nome, Toast.LENGTH_LONG).show();
//            editTextNome.setText(curso.nome);
//            editTextDescricao.setText(curso.descricao);
//            checkBoxAtivo.setChecked(curso.ativo);
//            id = curso.cursoId;
//
//        }

        new Thread(new Runnable()
        {
            public void run() {
                ITurmaService turmaService;
                turmaService = Utils.getTurmaService();

               call = turmaService.getTurmaPorProfessorId(professor.professorId);

                try {
                    lisTurmas = call.execute().body();
                    if ( lisTurmas != null ){
                        for ( Turma p : lisTurmas ) {
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


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, turmaListAdapter);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        turmasSpinner.setAdapter(spinnerArrayAdapter);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (professor == null) {
                    professor = new Professor();
                }

                IProfessorService professorService;

                professorService = Utils.getProfessorService();

                Call<Log> call = professorService.deleteProfessor(professor.professorId);
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
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                    }



                });
            }
        });


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (professor == null) {
                    professor = new Professor();
                }
                professor.nome = EditTextNome.getText().toString();
                professor.nomeCompleto =EditTextNomeCompleto.getText().toString();
                professor.idade = (EditTextIdade.getText().toString());
                professor.dataNascimento = (EditTextDataNascimento.getText().toString());
                String textName = EditTextName.getText().toString();
                String textPass =  EditTextPasword.getText().toString();
                Usuario u= new Usuario(textName,textPass);
                professor.usuario = u;

                IProfessorService professorService;

                professorService = Utils.getProfessorService();

                Call<Log> call = professorService.addProfessor(professor);
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
