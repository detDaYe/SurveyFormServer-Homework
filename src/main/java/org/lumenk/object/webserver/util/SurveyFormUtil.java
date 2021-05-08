package org.lumenk.object.webserver.util;

import com.google.gson.Gson;
import org.lumenk.object.webserver.util.questions.Question;

import java.util.ArrayList;

public class SurveyFormUtil {
    private Question[] questions;
    private MyQueue<Question> temp = new MyQueue<>();

    public void addQuestions(Question question){
        temp.enqueue(question);
    }

     public String makeJson(){
        int size = temp.size();
        questions = new Question[size];
        for(int i = 0; i < size; i++)
            questions[i] = temp.dequeue();


        Gson gson = new Gson();
        return gson.toJson(questions);
     }

     public static Question[] fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Question[].class);
     }
}
