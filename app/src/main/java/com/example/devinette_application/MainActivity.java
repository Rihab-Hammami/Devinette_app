package com.example.devinette_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTextViewCountDown;


        Button b1, b2;
        EditText edit;
        TextView textView2, res, ValeurScore, txtnbafficher;
        int valeur;
        int count = 0;
        String nbsaisie = "";
        int score = 100;
        long timeleft;




        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            ValeurScore = findViewById(R.id.tentative);
            mTextViewCountDown = findViewById(R.id.text_view_countdown);
            b1 = findViewById(R.id.btn1);
            b2 = findViewById(R.id.btn2);
            edit = findViewById(R.id.ed_nb);
            res = findViewById(R.id.resultat);
            txtnbafficher = findViewById(R.id.nbaffiché);


            valeur = (int) (Math.random() * 1000);


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edit.getText().toString().equals(""))

                        edit.getText().clear();
                    res.setText("");
                    ValeurScore.setText("0");
                    txtnbafficher.setText("");
                    score = 0;

                    mTimeLeftInMillis = START_TIME_IN_MILLIS;
                    //updateCountDownText();
                    int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                    int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                    String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                    mTextViewCountDown.setText(timeLeftFormatted);
                    mCountDownTimer.cancel();


                }
            });


            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            mTimeLeftInMillis = millisUntilFinished;
                            timeleft = millisUntilFinished;
                            //updateCountDownText();
                            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                            mTextViewCountDown.setText(timeLeftFormatted);

                        }

                        @Override
                        public void onFinish() {

                            res.setText("game over");
                           // mTextViewCountDown.setText((int) 00.00);
                            mTimerRunning = false;

                        }
                    }.start();

                    if (edit.getText().toString().isEmpty()) {
                        res.setText("Veuillez entrer un nombre entre 1 et 1000");
                        score = 0;
                        mCountDownTimer.cancel();

                    } else {
                        int valeurSaisie = Integer.parseInt(edit.getText().toString());

                        if (valeurSaisie > 1000 || valeurSaisie < 1) {
                            res.setText("Veuillez entrer un nombre entre 1 et 1000");

                        }
                        if (valeur > valeurSaisie) {
                            res.setText("tentative perdu,veuillez entrer une valeur plus grande");
                            nbsaisie += edit.getText().toString() + " ";
                            txtnbafficher.setText(nbsaisie);
                            count++;
                            score--;

                        }

                        if (valeur < valeurSaisie) {
                            res.setText("tentative perdu,veuillez entrer une valeur plus petite");
                            nbsaisie += edit.getText().toString() + " ";
                            txtnbafficher.setText(nbsaisie);
                            count++;
                            score--;

                        }

                        if (valeur == valeurSaisie) {
                            mCountDownTimer.onFinish();
                            res.setText("Bravo");

                            //mCountDownTimer.cancel();
                             //mTimeLeftInMillis=START_TIME_IN_MILLIS;
                            //mTimerRunning=false;



                        }

                    }




                    ValeurScore.setText(String.valueOf(score));


                }
            });


        }


    }
