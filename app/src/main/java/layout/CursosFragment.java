package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.CrudActivity;
import com.example.ljosias.appcontroledepresencas.MainUsuarioActivity;
import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.adapters.CursosListAdapter;
import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.services.ICursoService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class CursosFragment extends Fragment {

    ListView listView;
    ArrayList<Curso> cursosListAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cursos, container, false);

//        Curso curso = new Curso();
//        curso.nome = "Origens";
//        curso.descricao = "Origens de tudo";
//        curso.ativo = true;
//
//        Curso curso2 = new Curso();
//        curso2.nome = "Princípios de Vida";
//        curso2.descricao = "O estudo dos princípios elementares da vida de Cristo";
//        curso2.ativo = true;



        listView = (ListView) rootView.findViewById(R.id.listViewCursos);


        cursosListAdapter = new ArrayList<>();



        new Thread(new Runnable()
        {
            public void run() {
                ICursoService cursoService;
                cursoService = Utils.getCursoService();
                Call<List<Curso>> call = cursoService.getCursos();
                List<Curso> listCursos = null;
                try {
                    listCursos = call.execute().body();
                    if ( listCursos != null ){
                        cursosListAdapter.addAll(listCursos);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }).start();

        try {
            sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        GetAllCursos();
//        cursosListAdapter.add(curso);



        final CursosListAdapter adapter ;

        adapter= new CursosListAdapter(cursosListAdapter,getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Curso cursoSelected = cursosListAdapter.get(position);
                Intent myIntent = new Intent(getContext().getApplicationContext(), CrudActivity.class);

                myIntent.putExtra("curso", new Gson().toJson(cursoSelected));
                startActivityForResult(myIntent, 0);

            }
        });

        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getContext(), CrudActivity.class);
                Toast.makeText(getActivity(), "Crud Fragment ", Toast.LENGTH_LONG).show();
                startActivityForResult(myIntent, 0);

            }
        });
    }


}
