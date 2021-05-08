package org.lumenk.object.webserver.util;

import org.lumenk.object.webserver.util.questions.Question;

import java.util.ArrayList;

public class SurveyFormJsonGenerator {
    private Question[] questions;
    private ArrayList<Question> temp = new ArrayList<>();

    public void addQuestions(Question question){
        temp.add(question);
    }

     public void makeJson(){
        questions = (Question[]) temp.toArray();
     }
}
