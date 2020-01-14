import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);

        Worker first = new Worker(1000, latch, "WORKER 1 EMAIL AUTOSAVING");
        Worker second = new Worker(2000, latch, "WORKER 2 EMAIL ATTACHING");
        Worker third = new Worker(3000, latch, "WORKER 3 EMAIL SENDING");
        Worker fourth = new Worker(4000, latch, "WORKER 4 FINISHING");

        first.start();
        second.start();
        third.start();
        fourth.start();
        latch.await();

        System.out.println(Thread.currentThread().getName() + " is finished");
    }
}

// extends thread
// implements runnable

class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}