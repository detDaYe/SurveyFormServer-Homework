package org.lumenk.object.webserver.util.questions;

public class Question {
    protected String title;
    protected boolean isRequired;

    public Question(String title, boolean isRequired) {
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
