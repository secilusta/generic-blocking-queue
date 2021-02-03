package org.secil;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class DequeueTask<T> implements Callable<T> {

    Queue<T> queue;
    Semaphore semaphore;

    public DequeueTask(Queue<T> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public T call() throws Exception {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        T val = queue.Poll();
        System.out.println("Polled: " + val);
        semaphore.release();
        return val;
    }
}
