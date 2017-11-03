import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class PrimeNumbersOnThreads {

    public static void main(String[] args) {

//        Executors.
        //boolean a = new BigInteger("263463856983476895").isProbablePrime(4);
        ArrayList<Callable<Integer>> tasks = new ArrayList<>();
        Random r = new Random();

        for(int i = 1; i< 1000; i++)
        {
            int finalI = r.nextInt();
            tasks.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    boolean result =  BigInteger.valueOf(finalI).isProbablePrime(5);
                    System.out.println("Checking value: " + finalI + " - isPrime? " + result + " - " + Thread.currentThread().getName());

                    if(!result)
                        throw new ArithmeticException("Not a prime");

                    return finalI;
                }
            });

        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            Integer result = executorService.invokeAny(tasks);
            System.out.println();
            System.out.println(" Result prime is: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
}
