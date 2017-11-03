import com.aptitude.training.day1.CEO;
import com.aptitude.training.day1.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ReflectionsTest {

    @Test
    public void TestMethodsANdFIelds() throws ClassNotFoundException {
        Employee e = new Employee();
        Class<Employee> employeeClass = Employee.class;
        Class<? extends Employee> aClass = e.getClass();

        Class<?> aClass1 = Class.forName("com.aptitude.training.day1.CEO");
        System.out.println("=========== Employee methods except for Object ==========");
        Arrays.stream(Employee.class.getMethods()).filter(m -> m.getDeclaringClass() != Object.class).forEach(System.out::println);
        System.out.println("============ Employee constructors ======");
        Arrays.stream(Employee.class.getConstructors()).forEach(System.out::println);
        System.out.println("===============CEO ================= public - includes inherited but not private");
        Arrays.stream(CEO.class.getMethods()).forEach(System.out::println);
        System.out.println("======== CEO declared =========== - includes private");
        Arrays.stream(CEO.class.getDeclaredMethods()).forEach(System.out::println);

        // call constructor via reference
        try {
            Employee createdByRef = Employee.class.getConstructor(String.class, String.class, int.class)
                                    .newInstance("test1", "test2", 5);

            Assert.assertNotNull(createdByRef);
            Assert.assertEquals("test1", createdByRef.getName());
            Assert.assertEquals("test2", createdByRef.getPosition());
            Assert.assertEquals(5, createdByRef.getSalary());
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }

        // call private method of ceo
        CEO ceo = new CEO("hhh");
        try {
            Method sayHello = ceo.getClass().getDeclaredMethod("sayHello");
            sayHello.setAccessible(true);
            sayHello.invoke(ceo);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }
}
