package com.learning.spring.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Row {
    @Id
    @SequenceGenerator(
            name = "row_sequence",
            sequenceName = "row_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "row_sequence"
    )
    private Long rowId;

    @OneToMany(mappedBy = "row")
    private Set<Component> componentList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "sectionId")
    private Section section;

    public Row(Long rowId, Set<Component> componentList) {
        this.rowId = rowId;
        this.componentList = componentList;
    }

    public Row() {
    }

    public Section getSection() {
        return section;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Set<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(Set<Component> componentList) {
        this.componentList = componentList;
    }
}
