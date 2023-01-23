package cz.utb.fai.myproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.Objects;


public class TranslatingActivity extends AppCompatActivity {

    TextView output_text;
    EditText input_text;
    String lang_pair;
    Button translateBtn;

    private Translator translatorEnglish;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_cze_eng_activity);
        translateBtn = (Button) findViewById(R.id.translate_btn);

        lang_pair = getIntent().getExtras().getString("langpair");
        output_text = findViewById(R.id.output_text);
        input_text = findViewById(R.id.input_text);

        TranslatorOptions translatorOptions = new TranslatorOptions.Builder()
                .setSourceLanguage(Objects.requireNonNull(TranslateLanguage.fromLanguageTag(lang_pair.substring(0, 2))))
                .setTargetLanguage(Objects.requireNonNull(TranslateLanguage.fromLanguageTag(lang_pair.substring(3))))
                .build();

        translatorEnglish = Translation.getClient(translatorOptions);

        downloadModel();
    }

    private void downloadModel() {
        DownloadConditions downloadConditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        translatorEnglish.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(unused -> {
                })
                .addOnFailureListener(e -> {
                });
    }

    public void getTranslation(View view) {
        downloadModel();
        String input = String.valueOf(input_text.getText());
        translatorEnglish.translate(input)
                .addOnSuccessListener(s -> output_text.setText(s))
                .addOnFailureListener(e -> output_text.setText(e.toString()));
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}
