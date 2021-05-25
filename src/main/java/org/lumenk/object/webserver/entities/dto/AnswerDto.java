package org.lumenk.object.webserver.entities.dto;

import org.lumenk.object.webserver.entities.Answer;

public class AnswerDto {

    private Long answer_id;
    private Long question_id;

    public AnswerDto(Long answer_id, Long question_id) {
        this.answer_id = answer_id;
        this.question_id = question_id;
    }

    public AnswerDto(Answer answer){
        this.answer_id = answer.getId();
        this.question_id = answer.getQuestion().getId();
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }
}
