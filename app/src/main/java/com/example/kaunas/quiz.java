package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class quiz extends AppCompatActivity implements View.OnClickListener {

    ImageView back4;
    TextView questionNumber;
    TextView question;
    Button ansA, ansB, ansC, ansD;
    Button submit;
    int totalQuestions = questionsAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        back4 = findViewById(R.id.back4);

        back4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });

        questionNumber = findViewById(R.id.questionNumber);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submit = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        questionNumber.setText("Iš viso klausimų: "+totalQuestions);
        loadNewQuestion();
    }

    void loadNewQuestion() {

        if(currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }

        question.setText(questionsAnswer.question[currentQuestionIndex]);
        ansA.setText(questionsAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(questionsAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(questionsAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(questionsAnswer.choices[currentQuestionIndex][3]);
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);

        Button clickedButton = (Button) view;

        selectedAnswer = clickedButton.getText().toString();
        if (selectedAnswer.equals(questionsAnswer.correctAnswers[currentQuestionIndex])) {
            clickedButton.setBackgroundColor(Color.GREEN);
            ansA.setEnabled(false);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
        } else if (clickedButton.getId() == R.id.submit) {
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            clickedButton.setBackgroundColor(Color.RED);
        }
    }

    void finishQuiz() {

        new AlertDialog.Builder(this )
                .setTitle("Testas baigtas")
                .setPositiveButton("Kartoti testą", (dialogInterface, i) -> restartQuiz() )
                .setNegativeButton("Grįžti į pagrindinį meniu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(quiz.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    void backToMenu() {

    }
}