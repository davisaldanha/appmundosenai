package com.senai.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText inputEmail;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.email);
        btnLogin = findViewById(R.id.login);

        sp = getSharedPreferences(getString(R.string.email), Context.MODE_PRIVATE);
        inputEmail.setText(sp.getString(getString(R.string.email), ""));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = sp.edit();
                editor.putString(getString(R.string.email), inputEmail.getText().toString());
                editor.apply();

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);

                finish(); //Finalizar a execução da activity atual
            }
        });
    }
}