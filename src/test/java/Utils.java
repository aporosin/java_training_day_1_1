import com.aptitude.training.day1.Employee;
import org.junit.Before;

import java.util.*;

public class Utils {
    public static List<Employee> getEmployees() {
        return employees;
    }

    private static List<Employee> employees;
    private static String[] positions = {"abc", "def", "ghi"};


    //before test
    @Before
    public static void setUp(int i1){

        employees = new ArrayList<>();
        for (int i = 0; i < i1; i++) {
            employees.add(new Employee("name " + i, positions[i % 3]));
        }

        Collections.shuffle(employees);
        Random r = new Random();
        employees.forEach(e -> e.setSalary(Math.abs(r.nextInt(100) * 100)));
    }

    public static void sortEmployessByName()
    {
        //employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        employees.sort(Comparator.comparing(Employee::getName));
    }

    public static void sortEmployeesByPosition()
    {
        //employees.sort((e1, e2) -> e1.getPosition().compareTo(e2.getPosition()));
        employees.sort(Comparator.comparing(Employee::getPosition));
    }
}
