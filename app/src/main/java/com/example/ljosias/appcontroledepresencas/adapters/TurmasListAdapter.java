package com.example.ljosias.appcontroledepresencas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;

import java.util.ArrayList;

/**
 * Created by ljosias on 20/08/2017.
 */

public class TurmasListAdapter extends ArrayAdapter<Turma> {

    private TextView turmaNome, turmaProfessorNome;

    private ArrayList<Turma> dataSet ;
    Context TurmaContext ;

    public TurmasListAdapter(ArrayList<Turma> TurmaoArrayAdapter, Context context) {
        super(context, R.layout.turma_item,TurmaoArrayAdapter);
        this.dataSet = TurmaoArrayAdapter;
        this.TurmaContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Turma turma = getItem(position);
        // Check if1 an existing view is being reused, otherwise inflate the view

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.turma_item, parent, false);
        turmaNome = (TextView) convertView.findViewById(R.id.textViewTurmaProfessor);
//        turmaProfessorNome = (TextView) convertView.findViewById(R.id.textViewTurmaProfessor);

        // Populate the data into the template view using the data object
        turmaNome.setText("Turma: "+turma.nomeTurma);
//        turmaProfessorNome.setText("Nome Completo : "+ turma. );
        // Return the completed view to render on screen
        return convertView;
    }

}
