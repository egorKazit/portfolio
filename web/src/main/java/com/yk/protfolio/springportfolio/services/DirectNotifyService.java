package com.yk.protfolio.springportfolio.services;

import com.yk.processor.PostProcessQueue;
import com.yk.protfolio.springportfolio.persistence.UserTopicDAO;
import com.yk.schema.Contact;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class DirectNotifyService {

    private final MessageService messageService;
    private final UserTopicDAO userTopicDAO;
    private final PostProcessQueue postProcessQueue;

    @PostConstruct
    public void init() {

        var executors = Executors.newFixedThreadPool(5);

        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                try {
                    var contact = (Contact) postProcessQueue.getQueue().take();
                    var userTopicAssignments = userTopicDAO.getUserTopicAssignments();
                    userTopicAssignments.forEach(assignment -> executors.submit(() -> messageService.execute(assignment, contact)));
                } catch (InterruptedException e) {
                    System.out.println("Restarting application...");
                    System.exit(0); // Trigger shutdown hook and restart
                }
            }
        });

    }

}
