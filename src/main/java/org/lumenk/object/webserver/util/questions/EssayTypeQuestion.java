package org.lumenk.object.webserver.util.questions;

public class EssayTypeQuestion extends Question{

    public EssayTypeQuestion(int number, String title, boolean isRequired, int maxLength) {
        super(number, title, isRequired);
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    private final int maxLength;
}
