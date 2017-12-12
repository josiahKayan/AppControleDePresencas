package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Aluno;
import com.example.ljosias.appcontroledepresencas.models.Tag;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IAlunoService;
import com.example.ljosias.appcontroledepresencas.services.ITagService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class AlunoRegistroActivity extends AppCompatActivity {


    EditText EditTextNome,EditTextNomeCompleto, EditTextIdade,EditTextName, EditTextPassword,EditTextDataNascimento;
    Button buttonSalvar,buttonDelete;
    Aluno aluno;
    int id ;
    Spinner tagSpinner;
    Call<List<Tag>> call = null;
    List<Tag> lisTag = null;
    String jsonMyObject = null;
    int positionTag = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aluno_registro);
        setTitle("Registro de Aluno");


        EditTextNome = (EditText) findViewById(R.id.editTextNome);
        EditTextNomeCompleto= (EditText) findViewById(R.id.editTextNomeCompleto);
        EditTextIdade = (EditText) findViewById(R.id.editTextIdade);
        EditTextDataNascimento = (EditText) findViewById(R.id.editTextDataNascimento);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarAluno);
        buttonDelete = (Button) findViewById(R.id.buttonApagarAluno);
        EditTextName = (EditText) findViewById(R.id.editTextAlunoUserName);
        EditTextPassword = (EditText) findViewById(R.id.editTextAlunoPass);
        tagSpinner = (Spinner) findViewById(R.id.spinnerTag);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("Aluno");
            aluno = new Gson().fromJson(jsonMyObject, Aluno.class);
            Toast.makeText(getApplicationContext(), "" + aluno.nome, Toast.LENGTH_LONG).show();
            EditTextNome.setText(aluno.nome);
            EditTextNomeCompleto.setText(aluno.nomeCompleto);
            EditTextIdade.setText(aluno.idade);
            EditTextName.setText(aluno.usuario.Email);
            EditTextDataNascimento.setText(aluno.dataNascimento);
            id = aluno.alunoId;
        }

        final ArrayList<String> tagListAdapter = new ArrayList<>();


        new Thread(new Runnable()
        {
            public void run() {
                ITagService tagService;
                tagService = Utils.getTagService();

                if( jsonMyObject == null) {
                    call = tagService.getTags();
                }
                else {
                    call = tagService.getTag(aluno.tag.tagId);

                }

                try {
                    lisTag = call.execute().body();
                    if ( lisTag != null ){
                        for ( Tag t : lisTag ) {
                            tagListAdapter.add(t.code);
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tagListAdapter);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tagSpinner.setAdapter(spinnerArrayAdapter);
        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionTag =  tagSpinner.getSelectedItemPosition() ;
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aluno == null) {
                    aluno = new Aluno();
                }

                IAlunoService alunoService;

                alunoService = Utils.getAlunoService();

                Call<Log> call = alunoService.deleteAluno(aluno.alunoId);
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
                if (aluno == null) {
                    aluno = new Aluno();
                }
                aluno.nome = EditTextNome.getText().toString();
                aluno.nomeCompleto =EditTextNomeCompleto.getText().toString();
                aluno.idade = (EditTextIdade.getText().toString());
                aluno.dataNascimento = (EditTextDataNascimento.getText().toString());

                String textName = EditTextName.getText().toString();
                String textPass =  EditTextPassword.getText().toString();

                Usuario u = new Usuario(textName,textPass);
                aluno.usuario = u;

                Tag auxTag =null;

                auxTag = lisTag.get(positionTag);

                aluno.tagId = auxTag.tagId;


                IAlunoService alunoService;

                alunoService = Utils.getAlunoService();

                Call<Log> call = alunoService.addAluno(aluno);
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
