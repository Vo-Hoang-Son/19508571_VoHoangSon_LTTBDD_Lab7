package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_name);
        DiaDiem diaDiem = (DiaDiem) getIntent().getSerializableExtra("person");
        EditText editText = findViewById(R.id.nameEdit);
        Button button = findViewById(R.id.update);
        editText.setText(diaDiem.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaDiem.setName(editText.getText().toString());
                MainActivityB.databaseHandlerB.updatePerson(diaDiem);
                onBackPressed();
                MainActivityB.loadListView();
            }
        });

    }
}