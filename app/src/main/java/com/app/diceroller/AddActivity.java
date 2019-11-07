package com.app.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText textQuestion = findViewById(R.id.textQuestion);
        Button buttonOK = findViewById(R.id.buttonOk);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        buttonOK.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("question", textQuestion.getText().toString());
            setResult(0, intent);
            finish();
        });

        buttonCancel.setOnClickListener(v->{
            finish();
        });

    }
}
