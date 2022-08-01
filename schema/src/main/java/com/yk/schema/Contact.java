package com.yk.schema;

import com.yk.processor.BotMessage;
import com.yk.processor.PostProcessQueueImp;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
@Entity(name = "contact")
@Table(name = "contact")
public class Contact implements Serializable, BotMessage {

    private static final long serialVersionUID = 6529685098267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide name")
    private String name;
    @Column(name = "number")
    @NotEmpty(message = "*Please provide title")
    private String number;
    @Column(name = "email")
    @NotEmpty(message = "*Please provide email")
    private String email;
    @Column(name = "message")
    @NotEmpty(message = "*Please provide message")
    private String message;

    @PostPersist
    private void putIntoKafkaIfNeeded() {
        PostProcessQueueImp.addToQueue(this);
    }

    @Override
    public String buildMessage() {
        return String.format("New message%nfrom: %s%nnumber: %s%nemail: %s%nmessage: %s", name, number, email, message);
    }
}
