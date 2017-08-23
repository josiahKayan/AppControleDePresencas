package com.example.ljosias.appcontroledepresencas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ljosias.appcontroledepresencas.fragmentsprofessor.ProfessorPerfilFragment;
import com.example.ljosias.appcontroledepresencas.fragmentsprofessor.ProfessorTurmasFragment;

public class ProfessorMainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_main);

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
//http://www.truiton.com/2017/01/android-bottom-navigation-bar-example/
    }

}
