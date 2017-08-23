package com.example.ljosias.appcontroledepresencas.fragmentsaluno;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.fragmentsprofessor.ProfessorPerfilFragment;

import java.util.ArrayList;


public class AlunoPerfilFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;

    public static AlunoPerfilFragment newInstance() {
        AlunoPerfilFragment fragment = new AlunoPerfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aluno_perfil, container, false);

        return rootView;
    }
}
