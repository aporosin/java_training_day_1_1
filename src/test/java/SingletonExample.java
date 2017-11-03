import com.aptitude.training.day1.Employee;

// Best and tgread safe way to create singleton
public class SingletonExample {

    // private members are initialized when we first access them
    private static class SingletonHolder {
        private final static Employee INSTANCE = new Employee();
    }

    public static Employee getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
