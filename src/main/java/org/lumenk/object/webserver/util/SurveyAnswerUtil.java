package org.lumenk.object.webserver.util;

import com.google.gson.Gson;
import org.lumenk.object.webserver.util.answers.Answer;

import java.util.ArrayList;

public class SurveyAnswerUtil {
    private Answer[] answers;
    private MyQueue<Answer> answerMyQueue = new MyQueue<>();

    public SurveyAnswerUtil(MyQueue<Answer> answerMyQueue) {
        this.answerMyQueue = answerMyQueue;
    }

    public String makeJson(){
        int size = answerMyQueue.size();
        answers = new Answer[size];
        for(int i = 0; i < size; i++)
            answers[i] = answerMyQueue.dequeue();

        Gson gson = new Gson();
        return gson.toJson(answers);
    }

    public Answer[] fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Answer[].class);
    }
}
