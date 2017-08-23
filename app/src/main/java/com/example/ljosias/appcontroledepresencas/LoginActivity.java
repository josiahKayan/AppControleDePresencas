package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.log.Log;
import com.example.ljosias.appcontroledepresencas.mainaluno.AlunoMainActivity;
import com.example.ljosias.appcontroledepresencas.menuprofessor.ProfessorLoginMainActivity;
import com.example.ljosias.appcontroledepresencas.models.Professor;
import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IUsuarioService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ljosias.appcontroledepresencas.R.id.buttonApi;

public class LoginActivity extends AppCompatActivity {

    public String email ;
    public String password ;
    private Button button ;
    private  EditText editTextLogin, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(buttonApi);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //email = editTextLogin.getText().toString();
                //password = editTextSenha.getText().toString();
                email = "jkayanlima@gmail.com";
                password = "123";

                Logar();
            }
        });

    }

    public void Logar() {

        Intent myIntent = new Intent(getBaseContext(), ProfessorMainActivity.class);
        startActivityForResult(myIntent, 0);


//        IUsuarioService usuarioService;
//
//        usuarioService = Utils.getUsuarioService();
//
//        Usuario usuario = new Usuario(email, password);
//        usuario.Email =  "jkayanlima@gmail.com" ;
//        usuario.Senha =  "123" ;
//
//        Call<Log> call = usuarioService.login(usuario);
//                call.enqueue(new Callback<Log>() {
//                    @Override
//                    public void onResponse(Call<Log> call, Response<Log> response) {
//                        if (response.isSuccessful()) {
//
//                            if(response.body() == null) {
//                                Toast.makeText(getApplicationContext(), "Error:", Toast.LENGTH_LONG).show();
//                            }
//                            Intent myIntent = new Intent(getBaseContext(), ProfessorLoginMainActivity.class);
//                                    Toast.makeText(getApplicationContext(), "Bem-Vindo " + email, Toast.LENGTH_LONG).show();
//                                    startActivityForResult(myIntent, 0);
//
////                            else {
////                                if (response.body().perfil== 0) {
////                                    Intent myIntent = new Intent(getBaseContext(), AlunoMainActivity.class);
////                                    Toast.makeText(getApplicationContext(), "Bem-Vindo " + email, Toast.LENGTH_LONG).show();
////                                    startActivityForResult(myIntent, 0);
////                                }
////
////                                if (response.body().perfil ==1) {
////                                    Intent myIntent = new Intent(getBaseContext(), ProfessorLoginMainActivity.class);
////                                    Toast.makeText(getApplicationContext(), "Bem-Vindo " + email, Toast.LENGTH_LONG).show();
////                                    startActivityForResult(myIntent, 0);
////                                }
////
////                                if (response.body().perfil ==2) {
////                                    Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
////                                    Toast.makeText(getApplicationContext(), "Bem-Vindo " + email, Toast.LENGTH_LONG).show();
////                                    startActivityForResult(myIntent, 0);
////                                }
////                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Log> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "Error:"+t.getMessage(), Toast.LENGTH_LONG).show();
////                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
////                        startActivityForResult(myIntent, 0);
//                    }
//
//
//                });
    }
}


