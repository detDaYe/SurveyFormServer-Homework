package org.lumenk.object.webserver.entities.dto;

import org.lumenk.object.webserver.entities.Form;
import org.lumenk.object.webserver.entities.User;

public class FormDto {
    private Long id;
    private String owner;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FormDto(Long id, String owner, String title) {
        this.id = id;
        this.owner = owner;
        this.title = title;
    }

    public FormDto(Form form){
        this.id = form.getId();
        this.owner = form.getOwner().getId();
        this.title = form.getTitle();
    }
}
