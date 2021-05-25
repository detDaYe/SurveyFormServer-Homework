package org.lumenk.object.webserver.util.answers;

public class Answer {
    public Answer(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    private final int questionNumber;

    public int getQuestionNumber() {
        return questionNumber;
    }
}
