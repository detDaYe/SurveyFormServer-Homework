package org.lumenk.object.webserver.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Form {

    @Id
    private Long id;

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
