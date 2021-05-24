package org.lumenk.object.webserver.entities.dto;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;

public class FormDto {
    private Long id;
    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public FormDto(Long id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    public FormDto(Form form){
        this.id = form.getId();
        this.owner = form.getOwner().getId();
    }
}
