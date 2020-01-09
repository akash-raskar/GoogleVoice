package com.example.googlevoice;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    TextView t2;
    private final int ReQ_CODE_SPEAK_INPUT = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==ReQ_CODE_SPEAK_INPUT){

            if (resultCode==RESULT_OK && null !=data){

                ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                t1.setText(result.get(0));
                //save Speech to text file

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.voiceInput);
        t2 = findViewById(R.id.btnSpeak);

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implicit intent to trigger speech recognizer
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //we are passing putting the value of extra language model in language model free form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Akash \n Speak Something");

                startActivityForResult(intent, ReQ_CODE_SPEAK_INPUT);

            }
        });

    }
}
