package org.secil;

import java.util.concurrent.Semaphore;

public class EnqueueTask<V> implements Runnable {

    Queue<V> queue;
    V name;
    Semaphore semaphore;

    public EnqueueTask(Queue<V> queue, V name, Semaphore semaphore) {
        this.queue = queue;
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Added: " + name);
        queue.Add(name);
        semaphore.release();
    }
}
