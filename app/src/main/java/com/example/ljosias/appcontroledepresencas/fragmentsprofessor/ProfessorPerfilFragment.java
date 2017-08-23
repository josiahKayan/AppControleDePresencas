package com.example.ljosias.appcontroledepresencas.fragmentsprofessor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljosias.appcontroledepresencas.R;


public class ProfessorPerfilFragment extends Fragment {

    public static ProfessorPerfilFragment newInstance() {
        ProfessorPerfilFragment fragment = new ProfessorPerfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_professor_perfil, container, false);

        return rootView;
    }

}
