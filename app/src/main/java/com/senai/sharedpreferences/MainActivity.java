package com.senai.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText inputEmail;
    private EditText inputPass;
    private Button btnLogin;
    private MaterialSwitch switchLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.email);
        inputPass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.login);
        switchLogin = findViewById(R.id.switchLogin);

        //Criando um arquivo compartilhável  para armazenar dados do usuário em modo privado
        //O MODE_PRIVATE permite que apenas a aplicação possa acessar o arquivo
        sp = getSharedPreferences(getString(R.string.prefs_login), Context.MODE_PRIVATE);

        //Recuperar dados do arquivo de preferências
        //Capturar o valor armazenado na chave correspondente ao email
        inputEmail.setText(sp.getString(getString(R.string.prefs_email), ""));
        //Capturar o valor armazenado na chave correspondente a senha
        inputPass.setText(sp.getString(getString(R.string.prefs_pass), ""));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchLogin.isChecked()){
                    editor = sp.edit();
                    editor.putString(getString(R.string.prefs_email), inputEmail.getText().toString());
                    editor.putString(getString(R.string.prefs_pass), inputPass.getText().toString());
                    editor.apply();
                }else{
                    //Feature para próxima aula
                }

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);

                finish(); //Finalizar a execução da activity atual
            }
        });
    }
}