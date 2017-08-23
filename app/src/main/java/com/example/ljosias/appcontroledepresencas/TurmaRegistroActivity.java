package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurmaRegistroActivity extends AppCompatActivity {


    EditText EditTNome,EditTHoraInicial, EditTHoraFinal,EditTDataInicial,EditTDataFinal;
    Button buttonSalvar,buttonDelete;
    Turma turma;
    int id ;
    Spinner turmaProfessorSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_registro);

        EditTNome = (EditText) findViewById(R.id.editTextTNome);
        EditTHoraInicial= (EditText) findViewById(R.id.editTextTHoraInicial);
        EditTHoraFinal = (EditText) findViewById(R.id.editTextTHoraFinal);
        EditTDataInicial = (EditText) findViewById(R.id.editTexttextViewTDataInicial);
        EditTDataFinal = (EditText) findViewById(R.id.editTextTDataFinal);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarTurma);
        buttonDelete = (Button) findViewById(R.id.buttonApagarTurma);
        turmaProfessorSpinner = (Spinner) findViewById(R.id.spinnerTProfessor);


        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("turma");
            turma = new Gson().fromJson(jsonMyObject, Turma.class);
            Toast.makeText(getApplicationContext(), "" + turma.nomeTurma, Toast.LENGTH_LONG).show();
            EditTNome.setText(turma.nomeTurma);
            EditTHoraInicial.setText(turma.horaInicial);
            EditTHoraFinal.setText(turma.horaFinal);
            EditTDataInicial.setText(turma.dataInicio);
            EditTDataFinal.setText(turma.dataTermino);
            id = turma.turmaId;
        }



        List<String> listTurmas = new ArrayList<>();

        listTurmas.add("Sammya");
        listTurmas.add("Josias");
        listTurmas.add("Leonardo");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listTurmas);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        turmaProfessorSpinner.setAdapter(spinnerArrayAdapter);

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
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
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
