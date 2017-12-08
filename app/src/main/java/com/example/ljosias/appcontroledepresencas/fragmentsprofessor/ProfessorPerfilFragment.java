package com.example.ljosias.appcontroledepresencas.fragmentsprofessor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.MainUsuarioActivity;
import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;


public class ProfessorPerfilFragment extends Fragment {

    EditText editTextNome, editTextNomeCompleto, editTextIdade, editTextUsuario, editTextPassword,editTextDataNascimento;
    Spinner spinnerListaTurmas ;
    Button buttonSalvar;
    Professor professor = null;

    public static ProfessorPerfilFragment newInstance() {
        ProfessorPerfilFragment fragment = new ProfessorPerfilFragment();
        return fragment;
    }

//    public static ProfessorPerfilFragment newInstance(Usuario usuario) {
//        ProfessorPerfilFragment fragment = new ProfessorPerfilFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_professor_perfil, container, false);

        editTextNome = (EditText) rootView.findViewById(R.id.editTextPPNome);
        editTextNomeCompleto = (EditText) rootView.findViewById(R.id.editTextPPNomeCompleto);
        editTextIdade = (EditText) rootView.findViewById(R.id.editTextPPIdade);
        editTextUsuario = (EditText) rootView.findViewById(R.id.editTextPPProfName);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPProfSenha);
        editTextDataNascimento = (EditText) rootView.findViewById(R.id.editTextPPDataNascimento);
        spinnerListaTurmas = (Spinner) rootView.findViewById(R.id.spinnerPP);
        buttonSalvar = (Button) rootView.findViewById(R.id.buttonSalvarPPProfessor);


        new Thread(new Runnable()
        {
            public void run() {
                IProfessorService professoreservice;
                professoreservice = Utils.getProfessorService();
                //Adicionar um EndPoint para capturar o Id do professor
                Call<Professor> call = professoreservice.getProfessorPeloId(8);
                professor = null;
                try {
                    professor = call.execute().body();
                    if ( professor != null )
                    {
                        editTextNome.setText(professor.nome);
                        editTextNomeCompleto.setText(professor.nomeCompleto);
                        editTextIdade.setText(professor.idade);
                        editTextUsuario.setText(professor.usuario.Email);
                        editTextDataNascimento.setText(professor.dataNascimento);

                        List<String> listTurmas = new ArrayList<>();

                        for ( Turma turma  : professor.turmaLista )
                        {
                            listTurmas.add(turma.nomeTurma);
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTurmas);
                        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spinnerListaTurmas.setAdapter(spinnerArrayAdapter);


                    }
                } catch (Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }).start();

        try
        {
            sleep(1000);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                professor.nome = editTextNome.getText().toString();
                professor.nomeCompleto = editTextNomeCompleto.getText().toString();
                professor.idade = editTextIdade.getText().toString();
                professor.usuario.Email = editTextUsuario.getText().toString();
                professor.usuario.Senha = editTextPassword.getText().toString();
                professor.dataNascimento = editTextDataNascimento.getText().toString();
                professor.turmaLista =  professor.turmaLista;

                IProfessorService professoreservice;
                professoreservice = Utils.getProfessorService();

                Call<Log> call = professoreservice.atualizaProfessor(professor);

                call.enqueue(new Callback<Log>() {
                    @Override
                    public void onResponse(Call<Log> call, Response<Log> response)
                    {
                        if (response.isSuccessful())
                        {
                            Intent myIntent = new Intent(getContext(), MainUsuarioActivity.class);
                            Toast.makeText(getContext(), "Salvou " + response.body().getmessage(), Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Log> call, Throwable t)
                    {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });



            }
        });

        return rootView;
    }

}
