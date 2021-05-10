package org.lumenk.object.webserver.util.answers;

public class EssayAnswer extends Answer{

    private String value;

    public EssayAnswer(int questionNumber, String value) {
        super(questionNumber);
        this.value = value;
    }
}
