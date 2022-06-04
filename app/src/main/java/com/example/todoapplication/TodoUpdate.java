package com.example.todoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class TodoUpdate extends AppCompatActivity {

    public EditText detailEdt, todoDateEdt;
    public DBHandler dbHandler;
    String detail, todoDate ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_update);

        detailEdt = findViewById(R.id.idEdtDetail);
        todoDateEdt = findViewById(R.id.idEdtTodoDate);



        dbHandler = new DBHandler(TodoUpdate.this);

        detail = getIntent().getStringExtra("detail");
        todoDate = getIntent().getStringExtra("date");


        detailEdt.setText(detail);
        todoDateEdt.setText(todoDate);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.update_and_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {

            case R.id.update:

                dbHandler.updateTodo(detail, todoDateEdt.getText().toString(),
                        todoDateEdt.getText().toString());
                Toast.makeText(TodoUpdate.this, "Task Updated.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(TodoUpdate.this, ViewTodo.class);
                startActivity(i);
                break;

            case R.id.delete:
                dbHandler.deleteTodo(detail);
                Toast.makeText(TodoUpdate.this, "ToDo Task Successfully Deleted.", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(TodoUpdate.this, ViewTodo.class);
                startActivity(j);
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return false;

    }

}