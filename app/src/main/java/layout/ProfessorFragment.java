package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.CursoCrudActivity;
import com.example.ljosias.appcontroledepresencas.ProfessorRegisterActivity;
import com.example.ljosias.appcontroledepresencas.R;
import com.example.ljosias.appcontroledepresencas.adapters.ProfessoresListAdapter;
import com.example.ljosias.appcontroledepresencas.models.Curso;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static java.lang.Thread.sleep;


public class ProfessorFragment extends Fragment {

    ListView listView;
    ArrayList<Professor> professorArrayListListAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_professor, container, false);

        listView = (ListView) rootView.findViewById(R.id.listViewProfessores);


        professorArrayListListAdapter = new ArrayList<>();



        new Thread(new Runnable()
        {
            public void run() {
                IProfessorService professoreservice;
                professoreservice = Utils.getProfessorService();
                Call<List<Professor>> call = professoreservice.getProfessores();
                List<Professor> listProfessores = null;
                try {
                    listProfessores = call.execute().body();
                    if ( listProfessores != null ){
                        professorArrayListListAdapter.addAll(listProfessores);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }).start();

        try {
            sleep(900);
        } catch (InterruptedException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

        }


        final ProfessoresListAdapter adapter ;

        adapter= new ProfessoresListAdapter(professorArrayListListAdapter,getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Professor Professoreselected = professorArrayListListAdapter.get(position);
                Intent myIntent = new Intent(getContext().getApplicationContext(), ProfessorRegisterActivity.class);
                myIntent.putExtra("professor", new Gson().toJson(Professoreselected));
                startActivityForResult(myIntent, 0);

            }
        });

        listView.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.newProfessor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(getContext(), ProfessorRegisterActivity.class);
                Toast.makeText(getActivity(), "Crud Fragment ", Toast.LENGTH_LONG).show();
                startActivityForResult(myIntent, 0);


            }
        });
    }





}
