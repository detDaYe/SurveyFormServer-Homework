package org.lumenk.object.webserver.util.questions;

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
}
