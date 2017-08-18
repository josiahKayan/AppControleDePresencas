package com.example.ljosias.appcontroledepresencas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.models.Professor;

import java.util.ArrayList;

/**
 * Created by ljosias on 11/06/2017.
 */

public class ProfessoresListAdapter extends ArrayAdapter<Professor>  {

    private  ArrayList<Professor> dataSet ;
    Context ProfessorContext ;
    TextView textViewNome,textViewNomeCompleto ;

    public ProfessoresListAdapter(ArrayList<Professor> ProfessorArrayAdapter, Context context) {
        super(context, R.layout.professor_item,ProfessorArrayAdapter);
        this.dataSet = ProfessorArrayAdapter;
        this.ProfessorContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Professor professor = getItem(position);
        // Check if1 an existing view is being reused, otherwise inflate the view

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.professor_item, parent, false);
            textViewNome = (TextView) convertView.findViewById(R.id.textViewProfessor);
        textViewNomeCompleto = (TextView) convertView.findViewById(R.id.textViewNomeCompleto);

        // Populate the data into the template view using the data object
        textViewNome.setText("Professor: "+professor.nome);
        textViewNomeCompleto.setText("Nome Completo : "+ professor.nomeCompleto );
        // Return the completed view to render on screen
        return convertView;
    }

}
