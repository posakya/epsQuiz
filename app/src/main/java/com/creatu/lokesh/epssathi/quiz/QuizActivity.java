package com.creatu.lokesh.epssathi.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.HttpHandler.HttpHandler;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.QuizModel;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    Toolbar toolbar;
    String title,lessonId;

    private AVLoadingIndicatorView avi;

    private TextView quizQuestion,quizQuestionNumber;

    private RadioGroup radioGroup;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private RadioButton optionFour;
    private Button btnSubmit;

    private int currentQuizQuestion = 0;
    private int quizCount;

    private QuizModel model;


    private ArrayList<QuizModel> quizModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizQuestion = (TextView)findViewById(R.id.quiz_question_desc);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        optionOne = (RadioButton)findViewById(R.id.radio0);
        optionTwo = (RadioButton)findViewById(R.id.radio1);
        optionThree = (RadioButton)findViewById(R.id.radio2);
        optionFour = (RadioButton)findViewById(R.id.radio3);
        quizQuestionNumber = (TextView)findViewById(R.id.quiz_question);
        avi = (AVLoadingIndicatorView)findViewById(R.id.avi);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);


        toolbar = (Toolbar)findViewById(R.id.toolbar);

        quizModels = new ArrayList<>();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lessonId = getIntent().getExtras().getString("id");
        title = getIntent().getExtras().getString("name");

        toolbar.setTitle(title);

//        btnSubmit.setVisibility(View.GONE);
//        radioGroup.setVisibility(View.GONE);



        new getQuestions().execute();



    }


    @SuppressLint("StaticFieldLeak")
    class getQuestions extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            avi.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            HttpHandler httpHandler=new HttpHandler();
            String jsonStr=httpHandler.makeServiceCall("http://epssathi.com/api/api/question/"+lessonId);

            return jsonStr;
        }


        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            String question = "";
            String option = "";
            int is_correct = 0;

            avi.hide();

            radioGroup.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.VISIBLE);

            if (jsonStr != null){
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String status = jsonObject.optString("status");


                        JSONArray results = jsonObject.getJSONArray("results");

                        QuizModel quizModel = new QuizModel();

                        for (int i = 0; i<results.length(); i++){

                            JSONObject c = results.getJSONObject(i);


                            question  =   (c.optString("question").replaceFirst("[.]",";"));
                            is_correct = (c.optInt("is_correct"));


                            JSONArray options = c.getJSONArray("options");

                            for (int j=0; j<options.length(); j++){


                                JSONObject jsonObject1 = options.getJSONObject(j);

                                option += jsonObject1.optString("option").replaceAll("[1)]","").replaceAll("[2)]",",").replaceAll("[3)]",",").replaceAll("[4)]",",");

                            }

                            quizModels.add(new QuizModel(question,option,option,option,option,is_correct));


                        }

                    quizCount = quizModels.size();

                    model = quizModels.get(currentQuizQuestion);

                    String[] questionSeparated = model.getQuestion().split(";");
                    quizQuestionNumber.setText("Question "+questionSeparated[0]);
                    quizQuestion.setText(questionSeparated[1].replaceAll("\n","  "));



                    String option1 = model.getOption1();

                    String[] separated = option1.split(",");

                    optionOne.setText(separated[0]);
                    optionTwo.setText( separated[1]);
                    optionThree.setText( separated[2]);
                    optionFour.setText( separated[3]);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (optionOne.isChecked() || optionTwo.isChecked() || optionThree.isChecked() || optionFour.isChecked()) {

                            int radioSelected = radioGroup.getCheckedRadioButtonId();
                            int userSelection = getSelectedAnswer(radioSelected);

                            int correctAnswerForQuestion = model.getIs_correct();

                            if (userSelection == correctAnswerForQuestion) {

                                final Dialog dialog1 = new Dialog(QuizActivity.this);
                                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog1.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                                dialog1.setContentView(R.layout.correct_answer);
                                dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                                dialog1.setCanceledOnTouchOutside(true);


                                currentQuizQuestion++;
//                                uncheckedRadioButton();

                                TextView correct = dialog1.findViewById(R.id.txt_question);
                                TextView proceed = dialog1.findViewById(R.id.txt_proceed);

                                correct.setText(quizQuestionNumber.getText());

                                proceed.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if (currentQuizQuestion >= quizCount) {
                                            dialog1.dismiss();
                                            Toast.makeText(QuizActivity.this, "End of the Quiz Questions", Toast.LENGTH_LONG).show();
                                            finish();
                                            return;
                                        } else {

                                            dialog1.dismiss();
                                            model = quizModels.get(currentQuizQuestion);

                                            String[] questionSeparated = model.getQuestion().split(";");
                                            quizQuestionNumber.setText("Question " + questionSeparated[0]);
                                            quizQuestion.setText(questionSeparated[1].replaceAll("\n", "  "));


                                            String option1 = model.getOption1();

                                            String[] separated = option1.split(",");
                                            radioGroup.clearCheck();
                                            optionOne.setText(separated[0]);
                                            optionTwo.setText(separated[1]);
                                            optionThree.setText(separated[2]);
                                            optionFour.setText(separated[3]);

                                        }


                                    }
                                });

                                dialog1.show();


                            } else {
                                // failed question
                                final Dialog dialog1 = new Dialog(QuizActivity.this);
                                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog1.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                                dialog1.setContentView(R.layout.incorrect_answer);
                                dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                                dialog1.setCanceledOnTouchOutside(true);

                                TextView correct = dialog1.findViewById(R.id.txt_question);
                                TextView answerAgain = dialog1.findViewById(R.id.txt_again);
                                //currentQuizQuestion++;
                                correct.setText(quizQuestionNumber.getText());

                                answerAgain.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog1.dismiss();
                                    }
                                });

                                dialog1.show();

                            }

                        } else {

                            Toast.makeText(getApplicationContext(), "Please select option", Toast.LENGTH_SHORT).show();
                        }

                    }


                });


            }

        }
    }

    private int getSelectedAnswer(int radioSelected){

        int answerSelected = 5;

        if(radioSelected == R.id.radio0){
            answerSelected = 0;
        }
        if(radioSelected == R.id.radio1){
            answerSelected = 1;
        }
        if(radioSelected == R.id.radio2){
            answerSelected = 2;
        }
        if(radioSelected == R.id.radio3){
            answerSelected = 3;
        }
        return answerSelected;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                invalidateOptionsMenu();
                return super.onOptionsItemSelected(item);
        }
    }
}
