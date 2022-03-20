package com.yk.protfolio.springportfolio.schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
@Entity(name = "skill")
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    @NotEmpty(message = "*Please provide description")
    private String description;

    public String[] getDescriptions(){
        return description.split("\n");
    }

}
