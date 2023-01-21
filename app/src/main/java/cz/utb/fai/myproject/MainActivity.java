package cz.utb.fai.myproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
    }

    @Override
    public void onClick(View view) {
        Button clickedBtn = (Button) view;

        if (clickedBtn.getId() == R.id.quiz_btn) {
            setContentView(R.layout.activity_main);
        }

    }
}
