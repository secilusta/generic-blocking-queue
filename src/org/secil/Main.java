package org.secil;

import java.util.concurrent.*;

public class Main {

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Queue queue = new Queue<String>();

        EnqueueTask<String> enqueueTask = new EnqueueTask<>(queue, "a", semaphore);
        EnqueueTask<String> enqueueTask2 = new EnqueueTask<>(queue, "b", semaphore);
        EnqueueTask<String> enqueueTask3 = new EnqueueTask<>(queue, "c", semaphore);

        Thread t = new Thread(enqueueTask);
        Thread t2 = new Thread(enqueueTask2);
        Thread t3 = new Thread(enqueueTask3);

        t.start();
        t2.start();
        t3.start();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<String> dequeueTask = executor.submit(new DequeueTask(queue, semaphore));
        Future<String> dequeueTask2 = executor.submit(new DequeueTask(queue, semaphore));
        Future<String> dequeueTask3 = executor.submit(new DequeueTask(queue, semaphore));
        Future<String> dequeueTask4 = executor.submit(new DequeueTask(queue, semaphore));

        String result = dequeueTask.get();
        result = dequeueTask2.get();
        result = dequeueTask3.get();
        result = dequeueTask4.get();

        executor.shutdown();

    }
}
