package com.example.notepadappsharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddEditNote extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.notepadappsharedpreferences.NEW_ID";
    public static final String EXTRA_TITLE =
            "com.example.notepadappsharedpreferences.NEW_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.notepadappsharedpreferences.NEW_DESCRIPTION";

    private EditText editTextTitle;
    private EditText editTextDescription;

    Button buttonAddNote;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.title);
        editTextDescription = findViewById(R.id.desc);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEditNote.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();

        buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();

                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, title);
                data.putExtra(EXTRA_DESCRIPTION, description);

                int id = getIntent().getIntExtra(EXTRA_ID,-1);
                if (id != -1){
                    data.putExtra(EXTRA_ID,id);
                }

                setResult(RESULT_OK, data);
                finish();

            }
        });

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }else {
            setTitle("Add Note");
        }
    }

}