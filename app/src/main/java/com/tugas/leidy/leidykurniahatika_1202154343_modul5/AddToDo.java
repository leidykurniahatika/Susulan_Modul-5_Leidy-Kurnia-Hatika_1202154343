package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    //inisialisasi variabel
    DatabaseHelper mDbHelper;
    private EditText addName, addDescription, addPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        //inisialisasi varible dengan memanggil id masing-masing variable
        addName = (EditText) findViewById(R.id.add_todoName);
        addDescription = (EditText) findViewById(R.id.add_description);
        addPriority = (EditText) findViewById(R.id.add_priority);
        mDbHelper = new DatabaseHelper(this);
    }

    public void Submit(View view) {
        //membuat variable tipe data string, dan get data dari edit text
        String name = addName.getText().toString();
        String description = addDescription.getText().toString();
        String priority = addPriority.getText().toString();
        AddData(name, description, priority);   //memasukkan data ke masing-masing variabel
    }

    public void AddData(String nama, String deskripsi, String prioritas) {
        boolean inserData = mDbHelper.addData(nama, deskripsi, prioritas);  //membuat boolean dengan memanggil database

        if (inserData) {    //ketika data berhasil dimasukkan
            Toast.makeText(this, "Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {    //ketika data gagal dimasukkan
            Toast.makeText(this, "Gagal Ditambahkan", Toast.LENGTH_LONG).show();
        }
    }

    public void tampilDAta(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);   //menampilkan data yanag telah diinputkan ke class Main Activity
    }
}

