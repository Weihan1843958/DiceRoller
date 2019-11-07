package com.app.diceroller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int points = 0;
    private TextView textDice;
    private TextView textGuess;
    private TextView textPoints;
    private ArrayList<String> list = new ArrayList<>();
    public static final String[] questions = new String[]{"If you could go anywhere in the world, where would you go?",
            "If you were stranded on a desert island, what three things would you want to take with you?",
            "If you could eat only one food for the rest of your life, what would that be?",
            "If you won a million dollars, what is the first thing you would buy?",
            "If you could spaned the day with one fictional character, who would it be?",
            "If you found a magic lantern and a genie gave you three wishes, what would you wish?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRoll = findViewById(R.id.buttonRoll);
        Button buttonPlay = findViewById(R.id.buttonPlay);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        textDice = findViewById(R.id.textDice);
        textGuess = findViewById(R.id.textGuess);
        textPoints = findViewById(R.id.textPoints);
        buttonRoll.setOnClickListener(this::roll_the_dice);
        buttonPlay.setOnClickListener(this::roll_the_dice);
        buttonAdd.setOnClickListener(e -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, 1);
        });

        for(String q : questions){
            list.add(q);
        }
    }

    private void roll_the_dice(View v) {
        Button btn = (Button) v;
        Random rand = new Random();
        if (btn.getText().toString().startsWith("I")) {
            int value = rand.nextInt(6);
            textDice.setText(String.valueOf(value + 1));
            try {
                int guess = Integer.parseInt(textGuess.getText().toString());
                if (guess == value+1) {
                    Toast.makeText(this, "Congratulations", Toast.LENGTH_SHORT).show();
                    points++;
                    textPoints.setText("Points: " + points);
                }
            } catch (Exception e) {
                Toast.makeText(this, "No guess", Toast.LENGTH_SHORT).show();
            }
        } else {
            int index = rand.nextInt(list.size());
            textDice.setText(list.get(index));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null && resultCode==0) {
            String q = data.getStringExtra("question");
            list.add(q);
        }
    }
}
