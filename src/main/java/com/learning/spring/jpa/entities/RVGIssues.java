package com.learning.spring.jpa.entities;

import javax.persistence.Embeddable;

@Embeddable
public class RVGIssues {
    private String issues;
    private String mitigants;
    private String condition;
    private String conditionImposed;
    private String rvgMembers;

    public RVGIssues(String issues, String mitigants, String condition, String conditionImposed, String rvgMembers) {
        this.issues = issues;
        this.mitigants = mitigants;
        this.condition = condition;
        this.conditionImposed = conditionImposed;
        this.rvgMembers = rvgMembers;
    }

    public RVGIssues() {
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getMitigants() {
        return mitigants;
    }

    public void setMitigants(String mitigants) {
        this.mitigants = mitigants;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionImposed() {
        return conditionImposed;
    }

    public void setConditionImposed(String conditionImposed) {
        this.conditionImposed = conditionImposed;
    }

    public String getRvgMembers() {
        return rvgMembers;
    }

    public void setRvgMembers(String rvgMembers) {
        this.rvgMembers = rvgMembers;
    }
}
