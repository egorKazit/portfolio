package com.yk.processor;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.stereotype.Component;

@Component
public class PostProcessQueueImp implements PostProcessQueue {
    private static final Queue<Object> QUEUE = new LinkedBlockingQueue<>();

    public static void addToQueue(Object object) {
        QUEUE.add(object);
    }

    @Override
    public Queue<Object> getQueue() {
        return QUEUE;
    }

}
