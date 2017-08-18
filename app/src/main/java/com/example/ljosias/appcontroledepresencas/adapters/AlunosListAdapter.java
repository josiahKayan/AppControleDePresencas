package com.example.ljosias.appcontroledepresencas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.models.Aluno;

import java.util.ArrayList;

/**
 * Created by ljosias on 11/06/2017.
 */

public class AlunosListAdapter extends ArrayAdapter<Aluno>  {

    private  ArrayList<Aluno> dataSet ;
    Context AlunoContext ;
    TextView textViewNome,textViewNomeCompleto ;

    public AlunosListAdapter(ArrayList<Aluno> AlunoArrayAdapter, Context context) {
        super(context, R.layout.aluno_item,AlunoArrayAdapter);
        this.dataSet = AlunoArrayAdapter;
        this.AlunoContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Aluno aluno = getItem(position);
        // Check if1 an existing view is being reused, otherwise inflate the view

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.aluno_item, parent, false);
            textViewNome = (TextView) convertView.findViewById(R.id.textViewAluno);
        textViewNomeCompleto = (TextView) convertView.findViewById(R.id.textViewNomeCompleto);

        // Populate the data into the template view using the data object
        textViewNome.setText("Aluno: "+aluno.nome);
        textViewNomeCompleto.setText("Nome Completo : "+ aluno.nomeCompleto );
        // Return the completed view to render on screen
        return convertView;
    }

}
