package com.example.ljosias.appcontroledepresencas.fragmentsprofessor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.ListaCursoTurmaActivity;
import com.example.ljosias.appcontroledepresencas.ListaOpcoesActivity;
import com.example.ljosias.appcontroledepresencas.PresencaActivity;
import com.example.ljosias.appcontroledepresencas.ProfessorMainActivity;
import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.adapters.ProfessoresListAdapter;
import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Presenca;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static java.lang.Thread.sleep;


public class ProfessorTurmasFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;
    Professor professor;
    int id = 0;

    public static ProfessorTurmasFragment newInstance() {
        ProfessorTurmasFragment fragment = new ProfessorTurmasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        this.id = id ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_professor_turmas, container, false);

        listView = (ListView) rootView.findViewById(R.id.ListViewProfessorTurma);


        new Thread(new Runnable()
        {
            public void run() {
                IProfessorService professoreservice;
                professoreservice = Utils.getProfessorService();
                //Adicionar um EndPoint para capturar o Id do professor
                Call<Professor> call = professoreservice.getProfessorPeloId(id);
                professor = null;
                try {
                    professor = call.execute().body();

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

        if ( professor != null )
        {

            List<String> listTurmas = new ArrayList<>();

            for ( Turma turma  : professor.turmaLista )
            {
                listTurmas.add(turma.nomeTurma);
            }

            listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listTurmas));

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                Turma turmaSelected = professor.turmaLista.get(position);

                Intent myIntent = new Intent(getContext(), ListaOpcoesActivity.class);
                myIntent.putExtra("turma", new Gson().toJson(turmaSelected));

                startActivityForResult(myIntent, 0);

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

        return rootView;
    }
}
