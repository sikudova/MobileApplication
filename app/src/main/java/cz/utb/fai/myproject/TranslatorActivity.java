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
        Button engCzeBtn = findViewById(R.id.eng_cze_btn);
        Button czeEngBtn = findViewById(R.id.cze_eng_btn);
        Button backBtn = findViewById(R.id.back_btn_translator);
        engCzeBtn.setOnClickListener(this);
        czeEngBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button clickedBtn = (Button) view;

        if (clickedBtn.getId() == R.id.back_btn_translator) {
            Intent intent = new Intent(TranslatorActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (clickedBtn.getId() == R.id.eng_cze_btn) {
            Intent intent = new Intent(TranslatorActivity.this, TranslatingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("langpair", "en|cs");
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Intent intent = new Intent(TranslatorActivity.this, TranslatingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("langpair", "cs|en");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
