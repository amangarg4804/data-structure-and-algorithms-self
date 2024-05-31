package poc;

public class EnumTest {
    public static void main(String[] args) {
        String a = "DEV";
        EmployeeType type = EmployeeType.valueOf(a);
        switch (type) {
            case DEV: System.out.println("I am dev");
        }
    }
}
