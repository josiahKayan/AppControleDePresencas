package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PresencaActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> settingsArrayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenca);

        listView = (ListView) findViewById(R.id.listViewPresenca);

        settingsArrayListAdapter = new ArrayList<>();

        settingsArrayListAdapter.add("1/1");
        settingsArrayListAdapter.add("2/1");
        settingsArrayListAdapter.add("3/1");

        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, settingsArrayListAdapter));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                Intent myIntent = new Intent(getBaseContext(), ListaAlunosMainActivity.class);
                startActivityForResult(myIntent, 0);


                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getBaseContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }
        });

    }
}
