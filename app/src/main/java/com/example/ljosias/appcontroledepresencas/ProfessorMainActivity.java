package com.example.ljosias.appcontroledepresencas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.fragmentsprofessor.ProfessorPerfilFragment;
import com.example.ljosias.appcontroledepresencas.fragmentsprofessor.ProfessorTurmasFragment;
import com.example.ljosias.appcontroledepresencas.models.Aluno;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.google.gson.Gson;

public class ProfessorMainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_main);

        Usuario usuario = null;

        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("usuario");
            usuario = new Gson().fromJson(jsonMyObject, Usuario.class);
            id = usuario.UsuarioId;
            Toast.makeText(getApplicationContext(), "" + usuario.Email, Toast.LENGTH_LONG).show();

        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_professor);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        selectedFragment = ProfessorPerfilFragment.newInstance();
                        break;
                    case R.id.navigation_dashboard:
                        selectedFragment = ProfessorTurmasFragment.newInstance();
                        break;
                    default:
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, ProfessorPerfilFragment.newInstance());
        transaction.commit();

    }

}
