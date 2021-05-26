package org.lumenk.object.webserver.util.questions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Question {
    protected int number;
    protected String title;
    protected boolean isRequired;

    public Question(int number, String title, boolean isRequired) {
        this.number = number;
        this.title = title;
        this.isRequired = isRequired;
    }

    public String getTitle() {
        return title;
    }

    public boolean isRequired() {
        return isRequired;
    }


    private static final Gson gson = new Gson();
    public static Question fromJson(String jsonString){
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        if(jsonObject.has("choices"))
            return gson.fromJson(jsonString, ChoiceTypeQuestion.class);
        else if(jsonObject.has("maxLength"))
            return gson.fromJson(jsonString, EssayTypeQuestion.class);
        else
            return null;

    }
}
