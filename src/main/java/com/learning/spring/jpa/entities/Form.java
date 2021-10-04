package com.learning.spring.jpa.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Form {

    @Id
    @SequenceGenerator(
            name = "form_sequence",
            sequenceName = "form_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "form_sequence"
    )
    private long formId;
    private String version;
    private String label;
    private String description;

    @OneToMany(mappedBy = "form")
    private Set<Section> sectionList;

    public Form(long formId, String version, String label, String description, Set<Section> sectionList) {
        this.formId = formId;
        this.version = version;
        this.label = label;
        this.description = description;
        this.sectionList = sectionList;
    }

    public Form() {
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(Set<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
