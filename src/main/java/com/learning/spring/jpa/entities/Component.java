package com.learning.spring.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.ObjectInputFilter;
import java.util.List;

@Entity
@Table(name = "questions")
public class Component {

    @Id
    @SequenceGenerator(
            name = "component_sequence",
            sequenceName = "component_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "component_sequence"
    )
    private Long componentId;
    private String type;
    private int colSize;
    @Embedded
    private Config config;
    private String options;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "row_id", referencedColumnName = "rowId")
    private Row row;

    public Component(Long componentId, String type, int colSize, Config config, String options) {
        this.componentId = componentId;
        this.type = type;
        this.colSize = colSize;
        this.config = config;
        this.options = options;
    }

    public Component() {
    }

    public Row getRow() {
        return row;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
