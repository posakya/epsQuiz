package com.creatu.lokesh.epssathi.model_class;

/**
 * Created by lokesh on 4/12/18.
 */

public class QuizModel {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int is_correct;

    public int getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(int is_correct) {
        this.is_correct = is_correct;
    }

    public QuizModel(String question, String option1, String option2, String option3, String option4, int is_correct) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.is_correct = is_correct;
    }

    public QuizModel(){

        //empty constructor

    }

    public String getQuestion() {
        return question;
    }

    public String setQuestion(String question) {
        this.question = question;
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String setOption1(String option1) {
        this.option1 = option1;
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String setOption2(String option2) {
        this.option2 = option2;
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String setOption3(String option3) {
        this.option3 = option3;
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String setOption4(String option4) {
        this.option4 = option4;
        return option4;
    }




}
