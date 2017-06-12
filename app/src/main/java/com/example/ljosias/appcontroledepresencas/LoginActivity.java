package com.example.ljosias.appcontroledepresencas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljosias.appcontroledepresencas.models.Usuario;
import com.example.ljosias.appcontroledepresencas.services.IUsuarioService;
import com.example.ljosias.appcontroledepresencas.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                email = editTextLogin.getText().toString();
                password = editTextSenha.getText().toString();
                Logar();
            }
        });

    }

    public void Logar() {
        IUsuarioService usuarioService;

        usuarioService = Utils.getUsuarioService();

        Usuario usuario = new Usuario(email, password);
        usuario.setUsuarioId("");

        Call<Usuario> call = usuarioService.login(usuario);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
                            Toast.makeText(getApplicationContext(), "Bem-Vindo " + email, Toast.LENGTH_LONG).show();
                            startActivityForResult(myIntent, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error:"+t.getMessage(), Toast.LENGTH_LONG).show();
//                        Intent myIntent = new Intent(getBaseContext(), MainUsuarioActivity.class);
//                        startActivityForResult(myIntent, 0);
                    }


                });
    }
}


