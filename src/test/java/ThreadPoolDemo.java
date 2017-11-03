import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    // schedules action to print every 1 s
    // schedule shutdown action after 10 s will also cancel first scheduled action
    // will not work on unit test

    // todo: po co thread pool?
    @Test
    public void testThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.shutdown(); // czeka az wszytskie zadania sie wykonaja
        executorService.shutdownNow(); // dokonczy biezacy task ale nie skocyz wszytskich
//        executorService.awaitTermination()
        executorService.execute(new Runnable() { // nic nie zwraca (submit zwraca Future
            @Override
            public void run() {
                System.out.println("erjkfhekrjf");
            }
        });

        Object o = executorService.submit(new Runnable() { // submit allows to return sth from runnable
            @Override
            public void run() {

            }
        }).get();

        // todo: read about Future<T>

    }

        //    @Test
        //    public void TheradPoolTest(){
        public static void main(String[] args) {


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        scheduledExecutorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                int i = 1;
//                try {
//                    while(true) {
//                        Thread.sleep(1000);
//                        System.out.println("tatatatatat: " + i);
//                        i++;
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                try {
                        Thread.sleep(1000);
                        System.out.println("Fixed rate : " + i + " " + Thread.currentThread().getName() );
                        i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledExecutorService.shutdown();
            }
        },
        10000, TimeUnit.MILLISECONDS);
    }


        @Test
        public void ThreadPoolTest() throws InterruptedException {


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                try {
                    Thread.sleep(1000);
                    System.out.println("Fixed rate : " + i + " " + Thread.currentThread().getName() );
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

        scheduledExecutorService.schedule(new Runnable() {
                                              @Override
                                              public void run() {
                                                  scheduledExecutorService.shutdown();
                                              }
                                          },
                10000, TimeUnit.MILLISECONDS);

        scheduledExecutorService.awaitTermination(200000, TimeUnit.MILLISECONDS);
    }
}
