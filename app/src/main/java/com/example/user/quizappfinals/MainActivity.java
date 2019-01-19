package com.example.user.quizappfinals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdit;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArrayList;

    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdit = findViewById(R.id.answer);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);

        questionModelArrayList = new ArrayList<>();

        setUpQuestion();

        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();
            }
        });

    }


    public void checkAnswer(){

        String answerString = answerEdit.getText().toString().trim();

        if (answerString.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getAnswer())) {

            numberOfCorrectAnswer++;
            Log.e("answer", "right");
            currentPosition++;
            setData();
            answerEdit.setText("");

        }else{

            Log.e("answer", "wrong");
            currentPosition++;
            setData();
            answerEdit.setText("");

        }


        int x = ((currentPosition+1)) * 100 / questionModelArrayList.size();

        progressBar.setProgress(x);

    }



    public void setUpQuestion(){


        questionModelArrayList.add(new QuestionModel("What is 1+2 ? ","3"));
        questionModelArrayList.add(new QuestionModel("What is 8*8 ? ","64"));
        questionModelArrayList.add(new QuestionModel("What is 9*12 ? ","108"));
        questionModelArrayList.add(new QuestionModel("What is 6*8 ? ","48"));
        questionModelArrayList.add(new QuestionModel("What is 12/3 ? ","4"));



    }

    public void setData(){


        questionLabel.setText(questionModelArrayList.get(currentPosition).getQuestionString());
        questionCountLabel.setText("Question No : " + (currentPosition+1));
        scoreLabel.setText("Score : " + numberOfCorrectAnswer +"/" + questionModelArrayList.size());

    }

}
