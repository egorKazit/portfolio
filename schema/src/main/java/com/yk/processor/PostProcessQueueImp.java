package com.yk.processor;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class PostProcessQueueImp implements PostProcessQueue {
    private static final BlockingQueue<Object> QUEUE = new LinkedBlockingQueue<>();

    public static void addToQueue(Object object) {
        QUEUE.add(object);
    }

    @Override
    public BlockingQueue<Object> getQueue() {
        return QUEUE;
    }

}
