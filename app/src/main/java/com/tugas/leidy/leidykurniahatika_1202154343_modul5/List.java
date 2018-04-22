package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    DatabaseHelper mDbHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView);
        mDbHelper = new DatabaseHelper(this);
        showData();
    }

    public void showData(){
        Cursor data = mDbHelper.getData();
        ArrayList<String> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add(data.getString(6));
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);
    }
}
