package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivityB extends AppCompatActivity {
    public static DatabaseHandlerB databaseHandlerB;
    private static AdapterView<ListAdapter> listView;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7b);
        databaseHandlerB = new DatabaseHandlerB(this);
        context = this;


        listView = findViewById(R.id.listView);
        EditText  name = findViewById(R.id.editTextTextPersonName);
        Button addBtn = findViewById(R.id.save);

        loadListView();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandlerB.addPerson(name.getText().toString());
                loadListView();
            }
        });



    }
    public static void loadListView(){
        List<DiaDiem> diaDiems = databaseHandlerB.getPersons();
        ListAdapter listAdapter = new ListAdapter(diaDiems, R.layout.list_view_b,context);
        listView.setAdapter(listAdapter);

    }
}
