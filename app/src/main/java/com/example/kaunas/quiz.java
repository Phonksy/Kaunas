package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class quiz extends AppCompatActivity implements View.OnClickListener {
    TextView questionNumber;
    TextView question;
    Button ansA, ansB, ansC, ansD;
    Button submit;
    int totalQuestions = questionsAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        questionNumber = findViewById(R.id.questionNumber);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submit = findViewById(R.id.submit);
        submit.setEnabled(false);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        questionNumber.setText("Iš viso klausimų: "+totalQuestions);
        loadNewQuestion();

        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
        submit.setEnabled(false);
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        selectedAnswer = clickedButton.getText().toString();
        if (selectedAnswer.equals(questionsAnswer.correctAnswers[currentQuestionIndex]) ||
                !selectedAnswer.equals(questionsAnswer.correctAnswers[currentQuestionIndex])) {
            ansA.setBackgroundColor(Color.RED);
            ansB.setBackgroundColor(Color.RED);
            ansC.setBackgroundColor(Color.RED);
            ansD.setBackgroundColor(Color.RED);
            if (currentQuestionIndex == 0)
                ansC.setBackgroundColor(Color.GREEN);
            else if (currentQuestionIndex == 1)
                ansA.setBackgroundColor(Color.GREEN);
            else if (currentQuestionIndex == 2)
                ansA.setBackgroundColor(Color.GREEN);
            else if (currentQuestionIndex == 3)
                ansC.setBackgroundColor(Color.GREEN);
            else if (currentQuestionIndex == 4)
                ansB.setBackgroundColor(Color.GREEN);
            else if (currentQuestionIndex == 5)
                ansD.setBackgroundColor(Color.GREEN);
            else
                ansA.setBackgroundColor(Color.GREEN);
            ansA.setEnabled(false);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
            submit.setEnabled(true);
        }
        if (selectedAnswer.equals(questionsAnswer.correctAnswers[currentQuestionIndex]))
            score++;
        if (clickedButton.getId() == R.id.submit) {
            currentQuestionIndex++;
            loadNewQuestion();
        }
    }

    void finishQuiz() {

        new AlertDialog.Builder(this )
                .setTitle("Testas baigtas")
                .setMessage("Jūsų rezultatas: " + score + "/7")
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

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Intent intent = new Intent(quiz.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_food) {
            Intent intent2 = new Intent(quiz.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (id == R.id.nav_places) {
            Intent intent3 = new Intent(quiz.this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (id == R.id.nav_hotels) {
            Intent intent5 = new Intent(quiz.this, hotels.class);
            startActivity(intent5);
            return true;
        } else if (id == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}