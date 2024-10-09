package com.senai.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.senai.sharedpreferences.controllers.StudentController;
import com.senai.sharedpreferences.database.DatabaseHelper;
import com.senai.sharedpreferences.entities.Student;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPass, inputName;
    private Button btnRegister, btnLogin;

    private StudentController studentController;

    private TextInputLayout layoutEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        inputName = findViewById(R.id.inputName);
        btnRegister = findViewById(R.id.cadastrar);
        btnLogin = findViewById(R.id.login);
        layoutEmail = findViewById(R.id.layoutEmail);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(
                        null,
                        inputName.getText().toString(),
                        inputEmail.getText().toString(),
                        inputPass.getText().toString()
                        );
               try {
                    studentController = new StudentController(RegisterActivity.this);
                    if (studentController.checkEmail(inputEmail.getText().toString())){
                        layoutEmail.setError("Email já cadastrado!");
                    }else{
                        layoutEmail.setError(null);
                        Snackbar.make(view,  studentController.save(student), Snackbar.LENGTH_SHORT).show();
                        inputName.setText("");
                        inputEmail.setText("");
                        inputPass.setText("");
                    }
               }catch (Exception e){}
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}