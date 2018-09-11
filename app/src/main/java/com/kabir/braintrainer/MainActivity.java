package com.kabir.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    int score=0;
    TextView pointsTextView;
    int numbeOfQuestions = 0;
    Button btn;
    TextView sumTextView;
    Button btn1;
    Button btn2;
    RelativeLayout gamelayout;
    Button btn3;
    TextView timerTextView;

    Button playAgainButton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    public void playAgain(View view)
    {
        score=0;
        numbeOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQestions();
        new CountDownTimer(30100, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                //resultTextView.setText("Your score : "+Integer.toString(score)+"/"+ Integer.toString(numbeOfQuestions));
                resultTextView.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(numbeOfQuestions));
            }
        }.start();

    }
    public void generateQestions()
    {
        Random rand = new Random();

        int a = rand.nextInt(21);

        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ " + "+ Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        int InCorrectAnswer;

        for(int i =0; i<4; i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answer.add(a+b);
            }
            else
            {
                InCorrectAnswer=rand.nextInt(41);
                while (InCorrectAnswer == a+b)
                {

                     InCorrectAnswer=rand.nextInt(41);
                }
                answer.add(InCorrectAnswer);
            }
        }
        btn.setText(Integer.toString(answer.get(0)));

        btn1.setText(Integer.toString(answer.get(1)));
        btn2.setText(Integer.toString(answer.get(2)));
        btn3.setText(Integer.toString(answer.get(3)));
    }
    public void ChooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct");
        }
        else
        {
            resultTextView.setText("Wrong");
        }
        numbeOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+ Integer.toString(numbeOfQuestions));
        generateQestions();
    }

    public void start (View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(gamelayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton =(Button) findViewById(R.id.startButton);
         sumTextView =(TextView) findViewById(R.id.sumTextView);
          btn =(Button) findViewById(R.id.btn);
        btn1 =(Button) findViewById(R.id.btn1);
         btn2 =(Button) findViewById(R.id.btn2);
         btn3 =(Button) findViewById(R.id.btn3);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gamelayout = (RelativeLayout) findViewById(R.id.gamelayout);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);



    }
}
