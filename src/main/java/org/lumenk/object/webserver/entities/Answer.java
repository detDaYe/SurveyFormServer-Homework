package org.lumenk.object.webserver.entities;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Form question;

    public Answer(Long id, Form question) {
        this.id = id;
        this.question = question;
    }

    public Answer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Form getQuestion() {
        return question;
    }

    public void setQuestion(Form question) {
        this.question = question;
    }
}
