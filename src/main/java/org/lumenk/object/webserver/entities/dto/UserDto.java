package org.lumenk.object.webserver.entities.dto;

import org.lumenk.object.webserver.entities.User;

import java.util.ArrayList;

public class UserDto {
    private String name;
    private String id;
    private ArrayList<Long> forms;

    public UserDto(String name, String id, ArrayList<Long> forms) {
        this.name = name;
        this.id = id;
        this.forms = forms;
    }

    public UserDto(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.forms = new ArrayList<>();
        user.getForms().forEach(e -> forms.add(e.getId()));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Long> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Long> forms) {
        this.forms = forms;
    }
}
