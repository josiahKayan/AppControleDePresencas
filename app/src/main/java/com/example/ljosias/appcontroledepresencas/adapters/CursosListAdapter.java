package com.example.ljosias.appcontroledepresencas.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.models.Curso;

import java.util.ArrayList;

/**
 * Created by ljosias on 11/06/2017.
 */

public class CursosListAdapter extends ArrayAdapter<Curso>  {

    private  ArrayList<Curso> dataSet ;
    Context cursoContext ;
    TextView textViewNome, textViewDescricao;
    CheckBox checkBoxAtivo ;

    public CursosListAdapter(ArrayList<Curso> cursoArrayAdapter, Context context) {
        super(context, R.layout.cursos_item,cursoArrayAdapter);
        this.dataSet = cursoArrayAdapter;
        this.cursoContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Curso curso = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cursos_item, parent, false);
            textViewNome = (TextView) convertView.findViewById(R.id.textViewNomeCurso);
            textViewDescricao = (TextView) convertView.findViewById(R.id.textViewDescricao);
            checkBoxAtivo = (CheckBox) convertView.findViewById(R.id.checkBoxAtivo);

        // Populate the data into the template view using the data object
        textViewNome.setText("Curso: "+curso.getnome());
        textViewDescricao.setText("Descrição:"+curso.getdescricao());
        checkBoxAtivo.setChecked(curso.getativo());
        checkBoxAtivo.setEnabled(false);
        // Return the completed view to render on screen
        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//
//        int position=(Integer) v.getTag();
//        Object object= getItem(position);
//        Curso curso=(Curso)object;
//
//        Toast.makeText(getContext(), "Clicou em "+position+ " and "+curso.getnome(), Toast.LENGTH_LONG).show();
//    }

}
