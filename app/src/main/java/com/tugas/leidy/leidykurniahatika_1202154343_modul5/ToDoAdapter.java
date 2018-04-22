package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>{
    DatabaseHelper mDbHelper;
    int id;
    MainActivity m = new MainActivity();
    String color;
    private LayoutInflater mInflater;
    private java.util.List<ToDoList> todoLists;

    public ToDoAdapter(Context context, java.util.List<ToDoList> todoList) {
        //inisiasi inflater
        mInflater = LayoutInflater.from(context);
        this.todoLists = todoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.to_do_list, parent, false);

        //method untuk menginflate dengan class lainnya
        return new MyViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ToDoList todoList = this.todoLists.get(position);
        id = todoList.getId();

        //get value ke textView
        holder.description.setText(todoList.getDekripsi());
        holder.name.setText(todoList.getNama());
        holder.priority.setText(todoList.getPrioritas());
        switch (color) {
            case "Red":
                holder.bgColor.setBackgroundColor(Color.RED);
                break;
            case "Blue":
                holder.bgColor.setBackgroundColor(Color.BLUE);
                break;
            case "Green":
                holder.bgColor.setBackgroundColor(Color.GREEN);
                break;
            case "White":
                holder.bgColor.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // class MyCiewHolder
        public TextView name, description, priority;
        ConstraintLayout bgColor;


        public MyViewHolder(View view) {
            //menginisiasi variable2 attribute
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            priority = (TextView) view.findViewById(R.id.priority);
            bgColor = (ConstraintLayout) view.findViewById(R.id.layout_background);
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            color = Preference.getString("chosenColor", "-1");
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {        // ketika di klik salah satu menu
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            String element = todoLists.get(mPosition).toString();
            Toast.makeText(view.getContext(), name.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
