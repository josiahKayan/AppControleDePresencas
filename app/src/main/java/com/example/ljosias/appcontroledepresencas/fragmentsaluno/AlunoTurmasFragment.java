package com.example.ljosias.appcontroledepresencas.fragmentsaluno;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.R;

import java.util.ArrayList;


public class AlunoTurmasFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;

    public static AlunoTurmasFragment newInstance() {
        AlunoTurmasFragment fragment = new AlunoTurmasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aluno_turmas, container, false);

        listView = (ListView) rootView.findViewById(R.id.ListViewAlunoTurma);

        settingsArrayListAdapter = new ArrayList<>();

//        http://www.codehenge.net/2011/05/customizing-android-listview-item-layout/

        settingsArrayListAdapter.add("Nova Chamada");
        settingsArrayListAdapter.add("Novo Aluno");
        settingsArrayListAdapter.add("Presenças");

        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

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
