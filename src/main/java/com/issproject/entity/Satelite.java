package com.issproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issproject.dto.Position;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="satelite")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Satelite {
    @Id
    @Column(name = "satelite_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;
    @JsonProperty("iss_position")
    @Transient
    private Position position;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="satelite", cascade= {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Astronaut>astronauts;
    @Column(name = "timestamp")
    @JsonProperty("timestamp")
    private long timeStamp;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Satelite{");
        sb.append("id=").append(id);
        sb.append(", position=").append(position);
        sb.append(", astronauts=").append(astronauts);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append('}');
        return sb.toString();
    }
}
