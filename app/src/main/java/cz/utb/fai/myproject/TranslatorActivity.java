package cz.utb.fai.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TranslatorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_activity);
        Button eng_cze_btn = findViewById(R.id.eng_cze_btn);
        Button cze_eng_btn = findViewById(R.id.cze_eng_btn);
        Button back_btn = findViewById(R.id.back_btn);
        eng_cze_btn.setOnClickListener(this);
        cze_eng_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button clickedBtn = (Button) view;

        if (clickedBtn.getId() == R.id.back_btn) {
            Intent intent = new Intent(TranslatorActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
