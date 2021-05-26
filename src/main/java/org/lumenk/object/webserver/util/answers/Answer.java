package org.lumenk.object.webserver.util.answers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Answer {
    public Answer(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    private final int questionNumber;

    public int getQuestionNumber() {
        return questionNumber;
    }

    private static final Gson gson = new Gson();
    public static Answer fromJson(String jsonString){
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        if(jsonObject.has("choice"))
            return gson.fromJson(jsonString, ChoiceAnswer.class);
        else if(jsonObject.has("value"))
            return gson.fromJson(jsonString, EssayAnswer.class);
        else
            return null;
    }
}
