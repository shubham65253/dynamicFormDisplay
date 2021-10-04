package com.learning.spring.jpa.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Config {
    private String label;
    private String description;
    private boolean compulsory;
    private String placeholders;

    public Config(String label, String description, boolean compulsory, String placeholders) {
        this.label = label;
        this.description = description;
        this.compulsory = compulsory;
        this.placeholders = placeholders;
    }

    public Config() {
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

    public boolean isCompulsory() {
        return compulsory;
    }

    public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }

    public String getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(String placeholders) {
        this.placeholders = placeholders;
    }
}
