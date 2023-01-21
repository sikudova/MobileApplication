package cz.utb.fai.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button answerA, answerB, answerC, answerD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        totalQuestionsTextView = findViewById(R.id.total_queries);
        questionTextView = findViewById(R.id.question);
        answerA = findViewById(R.id.answer_A);
        answerB = findViewById(R.id.answer_B);
        answerC = findViewById(R.id.answer_C);
        answerD = findViewById(R.id.answer_D);
        submitBtn = findViewById(R.id.submit_btn);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions: " + totalQuestion);

        loadNewQuestion();
    }

    public void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submitBtn.setEnabled(false);
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        answerA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        answerB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        answerC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        answerD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    public void finishQuiz() {
        String status = "";
        if (score > totalQuestion * 0.60) {
            status = "You passed!";
        } else {
            status = "You failed!";
        }

        new AlertDialog.Builder(this)
                .setTitle(status)
                .setMessage("Your score is " + score + " out of  " + totalQuestion)
                .setPositiveButton("Restart the Quiz", (dialogInterface, i) -> restartQuiz())
                .setNeutralButton("Back to the Menu", (dialogInterface, i) -> backToMenu())
                .setCancelable(false)
                .show();
    }

    public void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    public void backToMenu() {
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        answerA.setBackgroundColor(Color.WHITE);
        answerB.setBackgroundColor(Color.WHITE);
        answerC.setBackgroundColor(Color.WHITE);
        answerD.setBackgroundColor(Color.WHITE);

        Button clickedBtn = (Button) view;

        if (clickedBtn.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score += 1;
            }
            currentQuestionIndex += 1;
            loadNewQuestion();
        } else {
            submitBtn.setEnabled(true);
            selectedAnswer = clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(Color.MAGENTA);
        }
    }
}