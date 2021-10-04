package com.learning.spring.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Section {

    @Id
    @SequenceGenerator(
            name = "section_sequence",
            sequenceName = "section_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "section_sequence"
    )
    private long sectionId;
    private String sectionName;
    private String label;

    @OneToMany(mappedBy = "section")
    private Set<Row> rowList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id", referencedColumnName = "formId")
    private Form form;

    public Section(long sectionId, String sectionName, String label, Set<Row> rowList) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.label = label;
        this.rowList = rowList;
    }

    public Section() {
    }

    public Form getForm() {
        return form;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Row> getRowList() {
        return rowList;
    }

    public void setRowList(Set<Row> rowList) {
        this.rowList = rowList;
    }
}
