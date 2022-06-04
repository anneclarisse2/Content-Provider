package com.example.todoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    public EditText detailEdt, todoDateEdt;
    public DBHandler dbHandler;
    public CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detailEdt = findViewById(R.id.idEdtDetail);
        todoDateEdt = findViewById(R.id.idEdtTodoDate);


        dbHandler = new DBHandler(Home.this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        detailEdt = findViewById(R.id.idEdtDetail);
        todoDateEdt = findViewById(R.id.idEdtTodoDate);




        switch (item.getItemId()) {

            case R.id.save:
                String detail = detailEdt.getText().toString();
                String todoDate = todoDateEdt.getText().toString();

                if (detail.isEmpty() && todoDate.isEmpty()) {
                    Toast.makeText(Home.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();

                }else {


                    dbHandler.addNewTodo(detail, todoDate);
                    Toast.makeText(Home.this, "New todo task has been added.", Toast.LENGTH_SHORT).show();
                    detailEdt.setText("");
                    todoDateEdt.setText("");

                    Intent i = new Intent(Home.this, ViewTodo.class);
                    startActivity(i);
                }
            case R.id.list:
                Intent j = new Intent(Home.this, ViewTodo.class);
                startActivity(j);


            default:
                return super.onOptionsItemSelected(item);
        }


    }

}