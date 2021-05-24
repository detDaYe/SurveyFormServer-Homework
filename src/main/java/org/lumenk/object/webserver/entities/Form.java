package org.lumenk.object.webserver.entities;

import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class, optional = false ,fetch = FetchType.EAGER)
    private User owner;

    public Form(){}

    public Form(Long id, User owner) {
        this.id = id;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        owner.getForms().add(this);
    }
}
