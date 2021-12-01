package com.learning.spring.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rvg_response")
public class RVGResponse {
    @Id
    @SequenceGenerator(
            name = "rvg_sequence",
            sequenceName = "rvg_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rvg_sequence"
    )
    private long rvgId;
    @ElementCollection
    @CollectionTable(
            name = "rvg_issues_reported",
            joinColumns = @JoinColumn(name="rvg_id")
    )
    @Column(name="issues")
    private List<String> issues;
}
