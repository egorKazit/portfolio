package com.yk.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
@Entity(name = "about")
@Table(name = "about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @Length(min = 5, message = "*Title should have length more then 5")
    @NotEmpty(message = "*Please provide title")
    private String title;
    @Column(name = "text")
    @NotEmpty(message = "*Please provide description")
    private String text;
    @Column(name = "picture")
    private String picture;
    @Column(name = "image", length = 16777215)
    private byte[] image;
    @Setter
    private boolean isHidden;
}
