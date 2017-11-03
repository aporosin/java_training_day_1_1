import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.BiFunction; // lambdy - funkcyjne inetrfejsy
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lambdas {
    // Lambda to anonimowa implementacja interfejsu

    public void testSort(){
        BiFunction<String, String, Integer> comparator = (e1, e2) -> e1.compareTo(e2);
        // using @FunctionalInterface -> wymusza w czasie kompilacji zeby interfejs mial tylko jedna metode (byl funkcyjny)

        Collections.sort(new ArrayList<String>(), comparator::apply);

        int[] i = new int[1];
        i[0] = 0;
        Comparator<String> comparator2 = (e1, e2) -> {
            i[0] ++;
            return e1.compareTo(e2);
        };
        Collections.sort(new ArrayList<String>(), comparator2);

        Collections.sort(new ArrayList<String>(), (e1, e2) -> e1.compareTo(e2));

        Collections.sort(new ArrayList<String>(), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });



    }

    public void lambdaTest(Predicate<String> test, Consumer<String> consumer)
    {

    }
}
