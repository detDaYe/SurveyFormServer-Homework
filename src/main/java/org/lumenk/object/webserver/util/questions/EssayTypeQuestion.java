package org.lumenk.object.webserver.util.questions;

public class EssayTypeQuestion extends Question{

    public EssayTypeQuestion(String title, boolean isRequired, int maxLength) {
        super(title, isRequired);
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    private final int maxLength;
}
