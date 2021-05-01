package org.lumenk.object.webserver.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Form {

    @Id
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User owner;

    public Form(){}

    public Form(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
