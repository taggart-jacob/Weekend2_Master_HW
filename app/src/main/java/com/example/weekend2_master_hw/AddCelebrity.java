package com.example.weekend2_master_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

public class AddCelebrity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    EditText etProfession;
    DatabaseHelper dbHelper;
    Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celebrity);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etProfession = findViewById(R.id.etProfession);
        dbHelper = new DatabaseHelper(this);
        returnIntent = getIntent();
    }

    public void onClick(View view) {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String profession = etProfession.getText().toString();
        //the all fields are filled out
        if ((name.length() != 0) && (age.length() != 0) && (profession.length() != 0)) {
            //creates new celebrity
            Celebrity celeb = new Celebrity(name, age, profession);
            //calls addCeleb
            addCeleb(celeb);
            etName.setText("");
            etAge.setText("");
            etProfession.setText("");
        } else {
            Toast.makeText(this, "FUCK YOU! YOU FUCKED UP!", Toast.LENGTH_SHORT).show();

        }
    }

    public void addCeleb(Celebrity celeb) {

        dbHelper.insertCeleb(celeb);
        returnIntent.putExtra("result", celeb);

        for (Celebrity music : dbHelper.queryForAllCelebRecords()) {
            Log.d("TAG", String.format(Locale.US, "%s %s %s %s", music.getName(), music.getAge()
                    , music.getProfession()));

        }

        setResult(RESULT_OK, returnIntent);
        finish();


    }

}
