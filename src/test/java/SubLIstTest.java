import com.aptitude.training.day1.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubLIstTest {
    private List<Employee> employees;

    @Before
    public void setUp(){

        Utils.setUp(10);
        employees = Utils.getEmployees();

    }

    @Test
    public void SubListList()
    {
        Utils.sortEmployessByName();
        List<Employee> sublist = employees.subList(3,6); // should be 3,4,5
        Assert.assertEquals(3, sublist.size());
        int i = 3;

        for (int j = 3; j < 6; j++) {
            Assert.assertEquals(sublist.get(j-3).getName(), "name " + j);
        }


    }

    @Test
    public void SubListModifySourceList()
    {
        Utils.sortEmployessByName();
        List<Employee> sublist = employees.subList(3,6); // should be 3,4,5
        Assert.assertEquals(3, sublist.size());
        sublist.get(0).setName("newName 33");

        Assert.assertEquals("newName 33", employees.get(3).getName());
    }

    @Test
    public void CopiedListModifySourceList()
    {
        Utils.sortEmployessByName();
        List<Employee> sublist = new ArrayList<>();
        Collections.copy(sublist, employees);

        sublist.get(0).setName("newName 33");

        Assert.assertEquals("newName 33", sublist.get(0).getName());
        Assert.assertEquals("name 0", employees.get(0).getName());
    }
}
