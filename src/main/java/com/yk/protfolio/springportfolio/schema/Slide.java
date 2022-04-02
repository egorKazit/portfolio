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
@Entity(name = "slide")
@Table(name = "slide")
public class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    @NotEmpty(message = "*Please provide description")
    private String description;
    @Column(name = "general_word")
    private String generalWord;
    @Column(name = "picture")
    private String picture;
    @Column(name = "image", length = 16777215)
    private byte[] image;
}
