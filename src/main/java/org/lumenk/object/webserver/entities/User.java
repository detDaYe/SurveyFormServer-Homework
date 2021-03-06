package org.lumenk.object.webserver.entities;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
public class User {

    @Id
    private String id;
    private String name;

    @OneToMany(targetEntity = Form.class)
    private List<Form> forms;

    public User(){}

    public User(String id, String name, List<Form> forms) {
        this.id = id;
        this.name = name;
        this.forms = forms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
}
