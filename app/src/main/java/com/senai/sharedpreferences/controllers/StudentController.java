package com.senai.sharedpreferences.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.senai.sharedpreferences.database.DatabaseHelper;
import com.senai.sharedpreferences.entities.Student;

import java.util.ArrayList;

public class StudentController {

    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public StudentController(Context context){
        this.databaseHelper = new DatabaseHelper(context);
    }

    //Método para cadastrar um student
    public String save(Student  student){
        ContentValues values;

        db = databaseHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("name", student.getName());
        values.put("email", student.getEmail());
        values.put("password", student.getPassword());

        long result = db.insert("student", null, values);

        if(result == -1){
            return "Error to save student";
        }else{
            return "Student saved successfully";
        }
    }

    public ArrayList<Student>  findAll(){
        db = databaseHelper.getReadableDatabase();

        String[] campos = {"id", "name", "email", "password"};
        ArrayList<Student> students = new ArrayList<>();

        Cursor cursor =  db.query("student", campos, null, null, null, null, null);

        while(cursor.moveToNext()){

            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            students.add(new Student(id, name, email, password));
        }
        cursor.close();
        return students;
    }

    public boolean authentication(String email, String password){
        db = databaseHelper.getReadableDatabase();
        String where = String.format("email = '%s' AND password = '%s'", email, password);
        String[] campos = {"email", "password"};

        Cursor cursor = db.query("student", campos, where, null, null, null, null);

        return cursor.moveToNext() ;

    }
}
