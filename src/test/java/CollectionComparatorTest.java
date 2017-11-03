import com.aptitude.training.day1.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CollectionComparatorTest {

    private List<Employee> employees;
    String[] positions = {"abc", "def", "ghi"};

    //before test
    @Before
    public void setUp(){

        employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            employees.add(new Employee("name " + i, positions[i % 3]));
        }

        Collections.shuffle(employees);
    }

    private void sortEmployessByName()
    {
        //employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        employees.sort(Comparator.comparing(Employee::getName));
    }

    private void sortEmployessByPosition()
    {
        //employees.sort((e1, e2) -> e1.getPosition().compareTo(e2.getPosition()));
        employees.sort(Comparator.comparing(Employee::getPosition));
    }

    @After
    public void tearDown(){

    }

    @Test
    public void TestEmployees()
    {
        Assert.assertEquals(10, employees.size());
    }

    @Test
    public void TestSortByName()
    {
        Assert.assertEquals(10, employees.size());
        sortEmployessByName();
        for (int i = 0; i < 10; i++) {
            Employee e = employees.get(i);
            Assert.assertEquals(e.getName(), "name " + i);
        }
    }

    @Test
    public void TestSortByPosition()
    {
        Assert.assertEquals(10, employees.size());
        sortEmployessByPosition();
        Employee prev = employees.get(0);
        Assert.assertEquals("abc", prev.getPosition());
        Employee next;

        for (int i = 1; i < 10; i++) {
            next = employees.get(i);
            Assert.assertTrue(prev.getPosition().compareTo(next.getPosition()) <= 0);
        }
    }
}
