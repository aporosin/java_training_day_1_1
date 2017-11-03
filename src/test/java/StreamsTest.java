import com.aptitude.training.day1.CEO;
import com.aptitude.training.day1.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTest {
    // mozna je utworzyc z elementow bedacych kolekcjami -> metoda stream()

    public void test()
    {
        Arrays.asList(1, 4, 6).stream();

        Stream.iterate(0, i -> i + 1). // generate infinite stream: does not end stream so use limit
                limit(10) // take 10 elements
                .collect(Collectors.toList()); // convert to list -> this triggers lazy iterate method

        List<Employee> employees = new ArrayList<>();

        employees.stream()
                .filter(Objects::nonNull) // filter out nulls == .filter(e -> e != null)
                .filter(el -> el.getPosition() != null)
                .peek( el -> el.setName("kakakakak")) // dziala jak foreach ale nie konczy strumienia (zwraca strumien)
                .map(el -> new CEO(el.getName())) // map mapuje obiekt na inny i zwraca strumien nowych obiektow
                .map(Employee::getName) // instead of map(el -> el.getName())
                .sorted((e,s) -> e.compareTo(s))
                .forEach(s -> System.out.println(s)); // operacja terminalna (konczy strumien) - use peek to print and now finish stream

        // Merging streams
        //Stream.concat(employees.stream(), new ArrayList<Employee>().stream().findFirst();
    }

    // Optional.ofNullable(...) - Nullable type constructor

    List<Employee> employees;

    @Before
    public void setup()
    {
        Utils.setUp(25);
        employees = Utils.getEmployees();
    }

    @Test
    public void getTop5SalaryTest()
    {

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed()) // compare desc

                .peek(e -> System.out.println("Peek " + e.getName() + " " + e.getSalary()))
                .limit(5)
                .forEach(e -> System.out.println(e.getName() + " " + e.getSalary()));
        //Grupowanie w Javie: uzyc collect(Collectors....)
        //.collect(Collectors);

    }

    @Test
    public void groupByPositionTest()
    {
        // how much salary per position
        // map<string,int>

        // ctrl + alt + v - introduce variable to generate type for return value from map
        // ctrl+alt +c - const a variable
        Map<String, Integer> result =  employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.summingInt(Employee::getSalary)));
        System.out.println(result);


        //Grupowanie w Javie: uzyc collect(Collectors....)
        //.collect(Collectors);

    }

    @Test
    public void COncatenateString()
    {
        // Quick creat ea stream : Stream.of(a,b,c,d,e)
        List<String> words = //Arrays.asList("a");
                Arrays.asList("This", "is", "a", "new", "better", "world");
        //Collectors.reducing()
        Optional<String> reduce = words.stream().reduce((a, b) -> a + " , " + b);
        String reduce2 = words.stream().collect(Collectors.joining(" , "));
        System.out.println(reduce.isPresent()? reduce.get(): null);
        System.out.println(reduce2);
    }

    @Test
    public void FizzBuzzTest()
    {
        // x % 3 - Fizz
        // x % 5 - Buzz
        // x % 3 and x% 5 - FizzBuzz
        Stream.iterate(1, i -> i + 1)
                .limit(50)
                //.collect(Collectors.toMap(i -> i, i -> i%3))
                .forEach(i -> {
                    if(i % 3 == 0)
                    {
                        if(i % 5 == 0)
                            System.out.println(" FizzBuzz");
                        else
                            System.out.println(" Fizz");
                    }
                    else if( i % 5 == 0)
                        System.out.println(" Buzz");
                    else
                        System.out.println(i);
                });

        // jak bez forEach?
        Stream.iterate(1, i -> i+1)
                .limit(50)
                .sorted() // always after limit
                .peek(System.out::print)
                .peek(i -> Optional.of(i).filter(x -> x%3==0)
                            .ifPresent(x -> System.out.print("fizz")))
                .peek(i -> Optional.of(i).filter(x -> x%5==0)
                        .ifPresent(x -> System.out.print("buzz")))
                .peek(__ -> System.out.println()) // new line
                .count(); // just for finishing and executing stream

    }

    @Test
    // stream of word - print all words with max length
    public void PrintMaxLengthWords()
    {
        //Stream<String> words = Stream.of("a", "abcc", "b", "abc", "6666", "n", "test");
        //Map<Integer, List<String>> collect =
        Stream.of("a", "abcc", "b", "abc", "6666", "n", "test")
                .collect(Collectors.groupingBy(w -> w.length()))
                .entrySet()
                .stream()
                //alternative: .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed())
                //.findFirst()
                .max(Comparator.comparing(Map.Entry::getKey))
                .ifPresent(x -> System.out.println(x.getValue()));

    }

    // unordered przyspiesza strumien (nie zachowuje kolejnosci)

    @Test
    public void ParallelStreams()
    {
        class ThreadWrapper{
            @Override
            public String toString() {
                return "ThreadWrapper{" +
                        "value=" + value +
                        ", startThread='" + startThread + '\'' +
                        ", finalThread='" + finalThread + '\'' +
                        '}';
            }

            public ThreadWrapper(int v, String n)
            {
                value = v;
                startThread = n;
            }

            int value;

            public String getStartThread() {
                return startThread;
            }

            public void setStartThread(String startThread) {
                this.startThread = startThread;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getFinalThread() {
                return finalThread;
            }

            public void setFinalThread(String finalThread) {
                this.finalThread = finalThread;
            }

            String startThread;
            String finalThread;
        }

        String name = Thread.currentThread().getName();

        Stream.iterate(1, i -> i+1)
                .limit(100)
                .map(i -> new ThreadWrapper(i, Thread.currentThread().getName())) //or: map(ThreadMapper::new)
                .parallel()
                .peek(p -> p.value = p.value * 100)
                .peek(p -> p.setFinalThread(Thread.currentThread().getName()))
                .forEach(System.out::println);
        //
        //Stream.of("a", "abcc", "b", "abc", "6666", "n", "test").parallel()
    }

}
