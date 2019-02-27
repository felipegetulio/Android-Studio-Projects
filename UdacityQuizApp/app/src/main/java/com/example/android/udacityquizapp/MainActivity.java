package com.example.android.udacityquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_SCORE = 10;

    SparseArray<Object> answers;
    SparseArray<RadioGroup> radioGroups;
    SparseBooleanArray checkboxAnswers;
    Toast currentToast, finishToast;
    Button finishButton;
    Button showAnswersButton;
    EditText question5EditText;
    @BindView(R.id.name_text_input) EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();

    }

    private void initialize() {
        checkboxAnswers = new SparseBooleanArray();
        answers = new SparseArray<>();
        radioGroups = new SparseArray<>();
        question5EditText = findViewById(R.id.question_5_et);
        finishButton = findViewById(R.id.finish_button);
        showAnswersButton = findViewById(R.id.show_answer_button);
        currentToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        finishToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

        fillAnswers();
    }

    private void fillAnswers() {
        checkboxAnswers.append(R.id.option_a_question_10_cb, false);
        checkboxAnswers.append(R.id.option_b_question_10_cb, false);
        checkboxAnswers.append(R.id.option_c_question_10_cb, true);
        checkboxAnswers.append(R.id.option_d_question_10_cb, true);
        checkboxAnswers.append(R.id.option_e_question_10_cb, true);
        checkboxAnswers.append(R.id.option_f_question_10_cb, true);
        checkboxAnswers.append(R.id.option_g_question_10_cb, true);
        checkboxAnswers.append(R.id.option_h_question_10_cb, true);

        answers.append(1, R.id.option_c_question_1_rb);
        radioGroups.append(1, (RadioGroup) findViewById(R.id.radio_group1));

        answers.append(2, R.id.option_b_question_2_rb);
        radioGroups.append(2, (RadioGroup) findViewById(R.id.radio_group2));

        answers.append(3, R.id.option_d_question_3_rb);
        radioGroups.append(3, (RadioGroup) findViewById(R.id.radio_group3));

        answers.append(4, R.id.option_a_question_4_rb);
        radioGroups.append(4, (RadioGroup) findViewById(R.id.radio_group4));

        answers.append(5, "3.7");

        answers.append(6, R.id.option_c_question_6_rb);
        radioGroups.append(6, (RadioGroup) findViewById(R.id.radio_group6));

        answers.append(7, R.id.option_c_question_7_rb);
        radioGroups.append(7, (RadioGroup) findViewById(R.id.radio_group7));

        answers.append(8, R.id.option_d_question_8_rb);
        radioGroups.append(8, (RadioGroup) findViewById(R.id.radio_group8));

        answers.append(9, R.id.option_c_question_9_rb);
        radioGroups.append(9, (RadioGroup) findViewById(R.id.radio_group9));
    }

    private boolean testEmptyAnswers() {

        RadioGroup radioGroup;

        for (int i = 0, key; i < radioGroups.size(); ++i) {
            key = radioGroups.keyAt(i);
            radioGroup = radioGroups.get(key);

            if (radioGroup.getCheckedRadioButtonId() == -1) {
                return false;
            }
        }

        if (question5EditText.getText().toString().trim().length() == 0) {
            return false;
        }

        return true;
    }

    private int calculateScore() {
        RadioGroup radioGroup;
        CheckBox checkBox;
        int score = 0, rightCheckBoxes = 0;

        if (question5EditText.getText().toString().equals(answers.get(5))) {
            ++score;
        }

        for (int i = 0, key; i < radioGroups.size(); ++i) {
            key = radioGroups.keyAt(i);
            radioGroup = radioGroups.get(key);

            if (radioGroup.getCheckedRadioButtonId() == (int) answers.get(key)) {
                ++score;
            }
        }

        for (int i = 0, key; i < checkboxAnswers.size(); ++i) {
            key = checkboxAnswers.keyAt(i);
            checkBox = findViewById(key);

            if (checkBox.isChecked() == checkboxAnswers.get(key)) {
                ++rightCheckBoxes;
            }
        }

        if (rightCheckBoxes == checkboxAnswers.size()) {
            ++score;
        }

        return score;
    }

    public void onClickFinishButton(View view) {

        if (testEmptyAnswers()) {
            showAnswersButton.setEnabled(true);
            int totalScore = calculateScore();
            if (totalScore == MAX_SCORE) {
                showToast(finishToast, getString(R.string.maximum_score_message, nameEditText.getText().toString()));
            } else {
                showToast(finishToast, getString(R.string.show_score_message, nameEditText.getText().toString(), totalScore));
            }
        } else {
            showToast(currentToast, getString(R.string.not_all_questions_answered));
        }
    }

    public void showAnswers(View view) {
        CheckBox checkBox;
        RadioButton radioButton;

        question5EditText.setText((String) answers.get(5));
        question5EditText.setEnabled(false);

        finishButton.setEnabled(false);
        showAnswersButton.setEnabled(false);

        for (int i = 0, key; i < radioGroups.size(); ++i) {
            key = radioGroups.keyAt(i);
            radioButton = findViewById((int) answers.get(key));
            radioButton.setTextColor(getResources().getColor(R.color.right_answer));
        }

        for (int i = 0, key; i < checkboxAnswers.size(); ++i) {
            key = checkboxAnswers.keyAt(i);
            checkBox = findViewById(key);
            checkBox.setChecked(checkboxAnswers.get(key));
        }
    }

    private void showToast(Toast toast, String message) {
        toast.setText(message);
        toast.show();
    }
}
