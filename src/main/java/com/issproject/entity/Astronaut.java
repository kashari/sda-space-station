package com.issproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "astronaut")
@Data
public class Astronaut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "astronaut_id")
    private int id;
    @JsonProperty("name")
    @Column(name = "astronaut_name")
    private String name;
    @JsonProperty("craft")
    private String craft;
//    @ManyToOne(cascade= {
//            CascadeType.PERSIST,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.REFRESH
//    })
//    @JoinColumn(name="satelite_id")
//    @JsonProperty("craft")
//    private Satelite satelite;
}