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
import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.models.Presenca;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Turma;
import com.example.ljosias.appcontroledepresencas.services.IPresencaService;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class PresencaActivity extends AppCompatActivity {

    private ExpandableListView expListView;
    private ArrayList<String> settingsArrayListAdapter;
    private Turma turma;
    private List<Presenca> listaPresenca = null;
    private Presenca presenca = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenca);

        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            jsonMyObject = extras.getString("turma");
            turma = new Gson().fromJson(jsonMyObject, Turma.class);
        }

        expListView = (ExpandableListView) findViewById(R.id.expandableListPresenca);

        settingsArrayListAdapter = new ArrayList<>();

        //Captura a lista de Presenças
        final IPresencaService presencaService;
        presencaService = Utils.getPresencaService();

        new Thread(new Runnable()
        {
            public void run() {
                //Adicionar um EndPoint para capturar o Id do professor
                Call<List<Presenca>> call = presencaService.getPresencasPelaTurma(turma.turmaId);
                try {
                    listaPresenca = call.execute().body();

                } catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }).start();

        try
        {
            sleep(1000);
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // cria os grupos
        List<String> listMeses = new ArrayList<>();
        int encontrou = 0;

        for (Presenca p: listaPresenca)
        {
            if( listMeses.size() < 1)
            {
                listMeses.add(""+p.mes+"/"+p.ano);
            }

            for(int index = 0 ; index < listMeses.size() ;index++ ) {
                if (listMeses.get(index).contains("" + p.mes+"/"+p.ano)) {
                    encontrou++;
                }
            }
            if(encontrou == 0){
                listMeses.add(""+p.mes+"/"+p.ano);
            }
            encontrou = 0;
        }

        // cria os grupos
        final HashMap<String, List<Presenca>> listaOrganizada = new HashMap<>();
        encontrou = 0;

        for (Presenca p: listaPresenca)
        {


            if( listaOrganizada.size() < 1)
            {
                listaOrganizada.put(""+p.mes+"/"+p.ano,new ArrayList<Presenca>());
            }

            for(int index = 0 ; index < listaOrganizada.size() ;index++ ) {
                if (listaOrganizada.containsKey(""+p.mes+"/"+p.ano)   ) {
                    encontrou++;
                }
            }
            if(encontrou == 0){
                listaOrganizada.put(""+p.mes+"/"+p.ano,new ArrayList<Presenca>());
            }
            encontrou = 0;
        }



        HashMap<String, List<Presenca>> lstItensGrupo = new HashMap<>();

        for (Presenca presenca : listaPresenca)
        {
            if(listaOrganizada.containsKey(""+presenca.mes+"/"+presenca.ano))
            {
                listaOrganizada.get(""+presenca.mes+"/"+presenca.ano).add(presenca);
            }
        }

        try {
            // cria um adaptador (BaseExpandableListAdapter) com os dados acima
            final ChamadaAdapter adaptador = new ChamadaAdapter(getBaseContext(), listMeses, listaOrganizada);
            // define o apadtador do ExpandableListView
            expListView.setAdapter(adaptador);

            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                    Presenca presenca = (Presenca)adaptador.getChild(groupPosition, childPosition);

                    Intent myIntent = new Intent(getBaseContext(), ListaAlunosMainActivity.class);
                    myIntent.putExtra("presenca", new Gson().toJson(presenca));
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
