package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //inisialisasi variabel
    DatabaseHelper mDbHelper;
    private List<ToDoList> ToDoLists = new ArrayList<>();
    private RecyclerView recyclerView;
    private ToDoAdapter mAdapter;
    int i = 0;
    int[] id = new int[20];
    String[] name = new String[20];
    String[] description;
    String[] priority;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi variabel dengan memanggil id dr recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ToDoAdapter(this, ToDoLists);

        //menginisiasi adapter untuk recycleView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //menghubungkan adapter dan layout
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mDbHelper = new DatabaseHelper(this);
        prepareData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                //Get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(ToDoLists, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ToDoList todoList = ToDoLists.get(viewHolder.getAdapterPosition());
                Log.d("msg", todoList.getNama());
                String id = String.valueOf(todoList.getId());

                //Remove the item from the dataset
                ToDoLists.remove(viewHolder.getAdapterPosition());
                mDbHelper.deleteData(todoList.getId());
                //Untuk memberikan notifikasi ke adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(recyclerView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddToDo.class);
                startActivity(i);
            }
        });

        SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(this);
        color = Preference.getString("chosenColor", "-1");
    }

    private void prepareData() {
        Cursor data = mDbHelper.getData();
        ToDoLists.clear();
        while (data.moveToNext()) {
            ToDoLists.add(new ToDoList(data.getInt(0), data.getString(1),
                    data.getString(2), data.getString(3)));
        }
        //memasukkan beberapa menu ke dalam array objek

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}