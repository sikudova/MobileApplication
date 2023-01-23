package cz.utb.fai.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    EditText citySearch;
    Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        citySearch = findViewById(R.id.city_input);
        backBtn = findViewById(R.id.back_btn_weather);

        backBtn.setOnClickListener(this);

        citySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String city = String.valueOf(citySearch.getText());
                Intent intent = new Intent(WeatherActivity.this, CityFinder.class);
                intent.putExtra("City", city);
                startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        Button clickedBtn = (Button) view;

        if (clickedBtn.getId() == R.id.back_btn_translator) {
            Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (clickedBtn.getId() == R.id.search_btn) {
            Intent intent = new Intent(WeatherActivity.this, CityFinder.class);
            startActivity(intent);
        }
    }
}
