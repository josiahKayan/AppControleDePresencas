package com.example.ljosias.appcontroledepresencas;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Presenca;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.services.IPresencaService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaOpcoesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;
    Turma turma = null;
    CheckBox checkBoxStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_opcoes);

        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            jsonMyObject = extras.getString("turma");
            turma = new Gson().fromJson(jsonMyObject, Turma.class);

        }

        listView = (ListView) findViewById(R.id.listViewListaOpcoes);

        settingsArrayListAdapter = new ArrayList<>();

        settingsArrayListAdapter.add("Nova Chamada");
        settingsArrayListAdapter.add("Adicionar Aluno a turma");
        settingsArrayListAdapter.add("Lista de Presenças");

        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // Chama o EndPoint de nova Presença
                if(itemPosition ==0)
                {
                    final Dialog dialog = new Dialog(ListaOpcoesActivity.this);
                    dialog.setContentView(R.layout.dialog_nova_chamada);
                    dialog.setTitle("Nova Chamada");

                    checkBoxStatus = (CheckBox) dialog.findViewById(R.id.checkBoxNovaChamada);

                    DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker2);
                    final int day = datePicker.getDayOfMonth();
                    final int month = datePicker.getMonth() + 1;
                    final int year = datePicker.getYear();

                    Button dialogButtonSalvar = (Button) dialog.findViewById(R.id.buttonNovaChamadaSalvar);
                    dialogButtonSalvar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {

                            Presenca presenca = new Presenca();
                            presenca.horaEntrada = turma.horaInicial;
                            presenca.dia = day;
                            presenca.mes = month;
                            presenca.ano = year;
                            presenca.ativo = checkBoxStatus.isChecked();
                            presenca.turmaId = turma.turmaId;

                            IPresencaService presencaService;

                            presencaService = Utils.getPresencaService();

                            Call<Presenca> call = presencaService.addPresenca(presenca);
                            call.enqueue(new Callback<Presenca>() {
                                @Override
                                public void onResponse(Call<Presenca> call, Response<Presenca> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Salvou ", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();

                                    }
                                }

                                @Override
                                public void onFailure(Call<Presenca> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                                }


                            });
                            dialog.dismiss();
                        }
                    });

                    Button dialogButtonCancelar = (Button) dialog.findViewById(R.id.buttonNovaChamadaCancelar);
                    dialogButtonCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });


                    dialog.show();
                }

                // Chama o EndPoint de novo Aluno
                else if(itemPosition ==1)
                {
                    final Dialog dialog = new Dialog(ListaOpcoesActivity.this);
                    dialog.setContentView(R.layout.dialog_novo_aluno);
                    dialog.setTitle("Novo Aluno");

                    final EditText editTextNovoAlunoMatricula = (EditText) dialog.findViewById(R.id.editTextNovoAlunoMatricula);


                    Button dialogButtonSalvar = (Button) dialog.findViewById(R.id.buttonNovoAlunoSalvar);
                    dialogButtonSalvar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ITurmaService turmaService;


                            int turmaId = turma.turmaId;

                            int alunoId = Integer.parseInt(editTextNovoAlunoMatricula.getText().toString());

                            turmaService = Utils.getTurmaService();
                            try {
                                Call<Log> call = turmaService.adicionaAlunoATurma(2, 1);
                                call.enqueue(new Callback<Log>() {
                                    @Override
                                    public void onResponse(Call<Log> call, Response<Log> response) {
                                        if (response.isSuccessful()) {
                                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
                                            Toast.makeText(getApplicationContext(), "Salvou ", Toast.LENGTH_LONG).show();
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
                            }catch (Exception e)
                            {
                                System.out.println(e.getMessage());
                            }
                            dialog.dismiss();
                        }
                    });

                    Button dialogButtonCancelar = (Button) dialog.findViewById(R.id.buttonNovoAlunoCancelar);
                    dialogButtonCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });


                    dialog.show();
                }

                // Chama o EndPoint de Presença
                else if(itemPosition ==2)
                {

                    Intent myIntent = new Intent(getBaseContext(), PresencaActivity.class);
                    startActivityForResult(myIntent, 0);

                }

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getBaseContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });


    }
}
