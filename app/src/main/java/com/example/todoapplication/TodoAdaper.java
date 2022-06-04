package com.example.todoapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodoAdaper extends RecyclerView.Adapter<TodoAdaper.ViewHolder>{
    private ArrayList<TodoModal> todoModalArrayList;
    private Context context;

    public TodoAdaper(ArrayList<TodoModal> todoModalArrayList, Context context) {
        this.todoModalArrayList = todoModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);










    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TodoModal modal = todoModalArrayList.get(position);
        holder.detailTV.setText(modal.getdetail());
        holder.todoDateTV.setText(modal.gettodoDate());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,  TodoUpdate.class);
                i.putExtra("detail", modal.getdetail());
                i.putExtra("date", modal.gettodoDate());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView detailTV, todoDateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detailTV = itemView.findViewById(R.id.idTVDetail);
            todoDateTV = itemView.findViewById(R.id.idTVTodoDate);
        }
    }




}
