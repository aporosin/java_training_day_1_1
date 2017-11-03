import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

public class ThreadingTest {

    @Test
    public void threadTest(){
        // todo: deamon thread: are finished when JVM stops process (finally blocks are not executed, thread is not allowed to clean up)
        // other user threads: jvm closes when they all finish

        Thread t = new Thread(() -> System.out.println("ttt"));
        t.setDaemon(true); // if this is not set than thread inherits this setting from parent thread
        t.start(); // zaczyna sie wykonywac
        t.interrupt(); // - than we need to chekc isInterrupted in thread code
                        // wait , sleep will get InterruptedException if someone has interrupred thread

        t.yield(); // watek zglasza do systemu ze zwalnia - system moze wtedy przelaczyc watki (nei musi)
        try {
            t.join(); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock1)
        {
            // only one thread cna enter here

        } // or mark method synchronized
    }

    private Object lock1 = new Object();

    private synchronized void test()
    {
        new Object().notify(); // zwalnia tylko jeden wait

    }
    // todo: to check : Java memory model
    // todo: how much empty Object space take (overhead for extra fields in JVM)
    // todo: wątki systemowe i neisystemowe (zielone)
    // FOrk Join fRamework

    // demo
    public Queue<String> queue = new ArrayDeque<>();
    public Object producerDone = new Object();
    public Object consumerDone = new Object();

    public class Producer implements Runnable
    {

        @Override
        public void run() {
            Stream.iterate(0, i -> i + 1)
                    .limit(200)
                    .forEach(i -> {
                        synchronized (queue) {

                            queue.add("E " + i);
                            producerDone.notify();
                            //queue.notify();
                            System.out.println("Added to queue: " + i);
                        }
                    });
        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {
            //System.out.println(queue.remove());
            Stream.iterate(0, i -> i + 1)
                    .limit(200)
                    .forEach(i -> {
                        synchronized (queue) {
                            try {
                                //if(queue.isEmpty())
                                    //queue.wait();
                                producerDone.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(queue.remove());
                            consumerDone.notify();
                        }
                    });
        }
    }

    @Test
    public void TestProducerCOnsumer() {
        Thread a = new Thread(new Producer());
        a.setDaemon(false);
        a.start();
        Thread b = new Thread(new Consumer());
        b.setDaemon(false);
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
