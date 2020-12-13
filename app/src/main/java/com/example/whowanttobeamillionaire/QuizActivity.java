package com.example.whowanttobeamillionaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity
{
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewNumberQuestion;
    private RadioGroup rdGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button buttonNext;
    private Button buttonFifth;

    private ArrayList<Question> questionList;
    private ColorStateList textColorDefaultCh;
    private int questionCounter;
    private int questionCountTotal;
    private int questionCountTotalSize = 15;
    private Question currentQuestion;
    private int score;
    private boolean answered;
    Dialog dialog;

    public static final String EXTRA_SCORE = "extraScore";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewNumberQuestion = findViewById(R.id.text_view_number_question);
        textViewQuestion = findViewById(R.id.text_view_questions);
        rdGroup = findViewById(R.id.radio_group);
        radioButton1 = findViewById(R.id.radio_button1);
        radioButton2 = findViewById(R.id.radio_button2);
        radioButton3 = findViewById(R.id.radio_button3);
        radioButton4 = findViewById(R.id.radio_button4);
        buttonNext = findViewById(R.id.button_next_question);
        buttonFifth = findViewById(R.id.button_fifth);

        textColorDefaultCh = radioButton1.getTextColors();

        dialog = new Dialog(this);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.radio_button1:
                        radioButton1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_select_background));
                        radioButton2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        break;
                    case R.id.radio_button2:
                        radioButton2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_select_background));
                        radioButton4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        break;
                    case R.id.radio_button3:
                        radioButton3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_select_background));
                        radioButton2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        break;
                    case R.id.radio_button4:
                        radioButton4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_select_background));
                        radioButton2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        radioButton1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
                        break;
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!answered)
                {
                    if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked())
                    {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this, "Please select answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }
            }
        });

        buttonFifth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = 0;
                int previousNumber = 0;
                while (true)
                {
                    Random random = new Random();
                    int number = random.nextInt(4 - 1) + 1;
                    if (number != currentQuestion.getAnswerNr() && previousNumber != number)
                    {
                        i++;
                        previousNumber = number;
                        switch (number)
                        {
                            case 1:
                                radioButton1.setTextColor(Color.RED);
                                break;
                            case 2:
                                radioButton2.setTextColor(Color.RED);
                                break;
                            case 3:
                                radioButton3.setTextColor(Color.RED);
                                break;
                            case 4:
                                radioButton4.setTextColor(Color.RED);
                                break;
                        }
                        if (i >= 2)
                        {
                            break;
                        }
                    }
                }
            }
        });
    }

    private void showNextQuestion()
    {
        radioButton1.setTextColor(textColorDefaultCh);
        radioButton2.setTextColor(textColorDefaultCh);
        radioButton3.setTextColor(textColorDefaultCh);
        radioButton4.setTextColor(textColorDefaultCh);
        rdGroup.clearCheck();
        radioButton1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
        radioButton2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
        radioButton3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
        radioButton4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.buttons_background));
        if (questionCounter < questionCountTotalSize)
        {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getOption1());
            radioButton2.setText(currentQuestion.getOption2());
            radioButton3.setText(currentQuestion.getOption3());
            radioButton4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Вопрос: " + questionCounter + "/" + questionCountTotalSize);
            textViewNumberQuestion.setText("Вопрос: " + questionCounter);
            answered = false;
            buttonNext.setText("Next");
        }
        else
        {
            finishQuiz();
        }
    }

    private void checkAnswer()
    {
        answered = true;
        RadioButton chipSelected = findViewById(rdGroup.getCheckedRadioButtonId());
        int answerNr = rdGroup.indexOfChild(chipSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNr())
        {
            score++;
            textViewScore.setText("Счет:" + score);
        }
        if (answerNr != currentQuestion.getAnswerNr())
        {
            finish();
            openLoseDialog();
        }
        showSolution();
    }

    private void openWinDialog()
    {
        dialog.setContentView(R.layout.win_layout_dialog);
        dialog.show();
    }

    private void openLoseDialog()
    {
        dialog.setContentView(R.layout.lose_layout_dialog);
        dialog.show();
    }

    private void showSolution()
    {
        radioButton1.setTextColor(Color.RED);
        radioButton2.setTextColor(Color.RED);
        radioButton3.setTextColor(Color.RED);
        radioButton4.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNr())
        {
            case 1:
                radioButton1.setTextColor(Color.GREEN);
                break;
            case 2:
                radioButton2.setTextColor(Color.GREEN);
                break;
            case 3:
                radioButton3.setTextColor(Color.GREEN);
                break;
            case 4:
                radioButton4.setTextColor(Color.GREEN);
                break;
        }
        if (questionCounter < questionCountTotal)
        {
            buttonNext.setText("Next");
        }
        else
        {
            buttonNext.setText("Finish");
        }
    }

    private void finishQuiz()
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        openWinDialog();
        finish();
    }
}
