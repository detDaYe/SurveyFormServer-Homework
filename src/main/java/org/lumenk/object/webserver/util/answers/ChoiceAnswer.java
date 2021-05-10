package org.lumenk.object.webserver.util.answers;

public class ChoiceAnswer extends Answer{

    private final int choice;

    public ChoiceAnswer(int questionNumber, int choice) {
        super(questionNumber);
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
