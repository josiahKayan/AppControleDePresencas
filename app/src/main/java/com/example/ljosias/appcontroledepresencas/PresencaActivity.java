package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.adaptersexpandable.ChamadaAdapter;
import com.example.ljosias.appcontroledepresencas.models.Presenca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PresencaActivity extends AppCompatActivity {

    private ExpandableListView expListView;
    private ArrayList<String> settingsArrayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenca);

        expListView = (ExpandableListView) findViewById(R.id.expandableListPresenca);

        settingsArrayListAdapter = new ArrayList<>();

        // cria os grupos
        List<String> lstGrupos = new ArrayList<>();
        lstGrupos.add("Janeiro");
        lstGrupos.add("Fevereiro");
        lstGrupos.add("Março");

        // cria os itens de cada grupo
        List<Presenca> lstDoces = new ArrayList<>();
        lstDoces.add(new Presenca(1,2017));
        lstDoces.add(new Presenca(2,2017));
        lstDoces.add(new Presenca(3,2017));

        List<Presenca> lstLegumes = new ArrayList<>();
        lstLegumes.add(new Presenca(1,2017));
        lstLegumes.add(new Presenca(2,2017));

        List<Presenca> lstProdutos = new ArrayList<>();
        lstProdutos.add(new Presenca(3,2017));

        // cria o "relacionamento" dos grupos com seus itens
        HashMap<String, List<Presenca>> lstItensGrupo = new HashMap<>();
        lstItensGrupo.put(lstGrupos.get(0), lstDoces);
        lstItensGrupo.put(lstGrupos.get(1), lstLegumes);
        lstItensGrupo.put(lstGrupos.get(2), lstProdutos);
try {
    // cria um adaptador (BaseExpandableListAdapter) com os dados acima
     final ChamadaAdapter adaptador = new ChamadaAdapter(getBaseContext(), lstGrupos, lstItensGrupo);
    // define o apadtador do ExpandableListView
    expListView.setAdapter(adaptador);

    expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


            Presenca presenca = (Presenca)adaptador.getChild(groupPosition, childPosition);
            Intent myIntent = new Intent(getBaseContext(), ListaAlunosMainActivity.class);
            Toast.makeText(getApplicationContext(), ""+presenca.dia, Toast.LENGTH_LONG).show();
            startActivityForResult(myIntent, 0);
            // update the text view with the country
            return true;
        }
    });

}
catch (Exception e){
    String d = e.getMessage();
}


//        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // ListView Clicked item index
//                int itemPosition     = position;
//
//                Intent myIntent = new Intent(getBaseContext(), ListaAlunosMainActivity.class);
//                startActivityForResult(myIntent, 0);
//
//
//                // ListView Clicked item value
//                String  itemValue    = (String) expListView.getItemAtPosition(position);
//
//                // Show Alert
//                Toast.makeText(getBaseContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();
//
//            }
//        });

    }
}
