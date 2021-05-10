package org.lumenk.object.webserver.util.questions;

public class ChoiceTypeQuestion extends Question{
    private final int maxChoice;
    private final boolean hasEssayAnswer;
    private final String[] choices;

    public ChoiceTypeQuestion(int number, String title, boolean required, int maxChoice, boolean hasEssayAnswer, String[] choices) {
        super(number, title, required);
        this.maxChoice = maxChoice;
        this.hasEssayAnswer = hasEssayAnswer;
        this.choices = choices;
    }

    public int getMaxChoice() {
        return maxChoice;
    }

    public boolean isHasEssayAnswer() {
        return hasEssayAnswer;
    }

    public String[] getChoices() {
        return choices;
    }
}
