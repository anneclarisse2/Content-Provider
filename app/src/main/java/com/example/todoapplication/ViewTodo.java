package com.example.todoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewTodo extends AppCompatActivity {

    private ArrayList<TodoModal> todoModalArrayList;
    private DBHandler dbHandler;
    private TodoAdaper todoRVAdapter;
    private RecyclerView todoRV;
    FloatingActionButton addbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todo);



        addbtn = findViewById(R.id.floatingActionButton);




        todoModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewTodo.this);

        todoModalArrayList = dbHandler.readTodos();

        todoRVAdapter = new TodoAdaper(todoModalArrayList, ViewTodo.this);
        todoRV = findViewById(R.id.idRVTodo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewTodo.this, RecyclerView.VERTICAL, false);
        todoRV.setLayoutManager(linearLayoutManager);

        todoRV.setAdapter(todoRVAdapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(ViewTodo.this, Home.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewTodo.this);
                builder.setMessage("Do you want to exit?");
                builder.setCancelable(true);

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}