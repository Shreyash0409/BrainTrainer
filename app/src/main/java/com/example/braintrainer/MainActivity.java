package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score;
    int numberOfQuestions;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    boolean gameActive = false;
    ConstraintLayout gameLayout;
    TextView demoTextView;

    public void playAgain(View view){
        gameActive = false;
        playAgainButton.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "+" + Integer.toString(numberOfQuestions));
        timerTextView.setText("30s");
        newQuestion();
        resultTextView.setText("");

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                if (l/1000 <= 9 ){
                    timerTextView.setText("0" + String.valueOf(l/1000) +"s");
                }
                else {
                    timerTextView.setText(String.valueOf(l/1000) + "s");
                }
            }
            @Override
            public void onFinish() {
                    resultTextView.setText("Done!");
                    timerTextView.setText("00s");
                    gameActive = false;
                    playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void chooseAnswer(View view) {
        if (gameActive) {
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            newQuestion();
        }
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(31);
        int b = rand.nextInt(31);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
        int i=0;
        while (i<4){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
            i++;
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void startButton(View view){

        startButton.setVisibility(View.INVISIBLE);
        demoTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(view);
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         startButton = findViewById(R.id.startButton);
         sumTextView = findViewById(R.id.sumTextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         resultTextView = findViewById(R.id.resultTextView);
         scoreTextView = findViewById(R.id.scoreTextView);
         timerTextView = findViewById(R.id.timerTextView);
         playAgainButton = findViewById(R.id.playAgainButton);
         gameLayout = findViewById(R.id.gameLayout);
         demoTextView = findViewById(R.id.demoTextView);

         demoTextView.setVisibility(View.VISIBLE);
         startButton.setVisibility(View.VISIBLE);
         gameLayout.setVisibility(View.INVISIBLE);

         answers = new ArrayList<Integer>();

    }
}