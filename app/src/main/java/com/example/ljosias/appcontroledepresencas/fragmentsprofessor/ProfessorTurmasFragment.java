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

import com.example.ljosias.appcontroledepresencas.PresencaActivity;
import com.example.ljosias.appcontroledepresencas.ProfessorMainActivity;
import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.adapters.ProfessoresListAdapter;
import com.example.ljosias.appcontroledepresencas.models.Presenca;

import java.util.ArrayList;


public class ProfessorTurmasFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;

    public static ProfessorTurmasFragment newInstance() {
        ProfessorTurmasFragment fragment = new ProfessorTurmasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_professor_turmas, container, false);

        listView = (ListView) rootView.findViewById(R.id.ListViewProfessorTurma);

        settingsArrayListAdapter = new ArrayList<>();

//        http://www.codehenge.net/2011/05/customizing-android-listview-item-layout/
//        https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
//        https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/


        settingsArrayListAdapter.add("Nova Chamada");
        settingsArrayListAdapter.add("Adicionar Aluno a turma");
        settingsArrayListAdapter.add("Presenças");

        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;


                if(itemPosition ==0)
                {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog_nova_chamada);
                    dialog.setTitle("Nova Chamada");

                    Button dialogButtonSalvar = (Button) dialog.findViewById(R.id.buttonNovaChamadaSalvar);
                    dialogButtonSalvar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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

                    CheckBox checkBoxStatus = (CheckBox) dialog.findViewById(R.id.checkBoxAtivo);

                    dialog.show();
                }

                else if(itemPosition ==1)
                {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog_novo_aluno);
                    dialog.setTitle("Novo Aluno");

                    Button dialogButtonSalvar = (Button) dialog.findViewById(R.id.buttonNovoAlunoSalvar);
                    dialogButtonSalvar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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

                else if(itemPosition ==2)
                {

                    Intent myIntent = new Intent(getContext(), PresencaActivity.class);
                    startActivityForResult(myIntent, 0);


                }

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