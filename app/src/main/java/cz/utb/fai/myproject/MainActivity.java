package cz.utb.fai.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button quizBtn;
    Button translatorBtn;
    Button weatherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_MyProject);

        setContentView(R.layout.activity_main);
        quizBtn = (Button) findViewById(R.id.quiz_btn);
        translatorBtn = (Button) findViewById(R.id.translator_btn);
        weatherBtn = (Button) findViewById(R.id.weather_btn);

        quizBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        translatorBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TranslatorActivity.class);
            startActivity(intent);
        });

        weatherBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CityFinder.class);
            startActivity(intent);
        });
    }

}
