package cz.utb.fai.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class CityFinder extends AppCompatActivity implements View.OnClickListener {

    final String API_KEY = "c09cc2ee80bfbb4f33dac7ea05138845";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    DecimalFormat df = new DecimalFormat("#.##");


    EditText cityName;
    TextView temperature;
    TextView feelingTemperature;
    Button searchBtn;
    TextView currentInfoCity;
    Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_weather);
        cityName = findViewById(R.id.location);
        temperature = findViewById(R.id.weather_celsius);
        searchBtn = findViewById(R.id.search_city_btn);
        feelingTemperature = findViewById(R.id.feels_like);
        currentInfoCity = findViewById(R.id.current_city_info);
        backBtn = findViewById(R.id.back_btn_weather_city);
        backBtn.setOnClickListener(this);
    }

    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = cityName.getText().toString().trim();

        tempUrl = WEATHER_URL + "?q=" + city + "&appid=" + API_KEY;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    String cityName = jsonResponse.getString("name");
                    currentInfoCity.setText("Weather in " + cityName);
                    temperature.setText(df.format(temp) + " °C");
                    feelingTemperature.setText("Feels like " + df.format(feelsLike) + " °C");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CityFinder.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        Button clickedBtn = (Button) view;
        if (clickedBtn.getId() == backBtn.getId()) {
            Intent intent = new Intent(CityFinder.this, MainActivity.class);
            startActivity(intent);
        }
    }
}