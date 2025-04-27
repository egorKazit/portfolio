package com.yk.processor;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public interface PostProcessQueue {

    BlockingQueue<Object> getQueue();

}
