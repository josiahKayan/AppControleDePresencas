package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.services.ICursoService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class CrudActivity extends AppCompatActivity {

    EditText editTextNome , editTextDescricao ;
    CheckBox checkBoxAtivo ;
    Button buttonSalvar;
    Button buttonDelete;
    Curso curso;
    int id ;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_crud);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        checkBoxAtivo = (CheckBox) findViewById(R.id.checkBoxAtivo);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarCurso);
        buttonDelete = (Button) findViewById(R.id.buttonApagarCurso);

        String jsonMyObject = "";
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

    }



}
