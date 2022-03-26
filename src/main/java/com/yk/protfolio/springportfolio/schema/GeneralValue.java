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
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
@Entity(name = "general")
@Table(name = "general")
public class GeneralValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "internal_name")
    @Length(min = 5, message = "*Title should have length more then 5")
    @NotEmpty(message = "*Please provide name")
    private String internalName;
    @Column(name = "external_name")
    @NotEmpty(message = "*Please provide description")
    private String externalName;
}
