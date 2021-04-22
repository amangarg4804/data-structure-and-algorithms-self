package poc;

import java.util.List;
import java.util.Optional;

public class OptionalWithStreams {

  public static void main(String[] args) {
    Employee employee = new Employee();
    doProcessing(Optional.of(employee));
  }

  private static void doProcessing(Optional<Employee> employee) {
    employee.ifPresent(employee1 -> employee1.getPhoneNumbers()
        .forEach(System.out::println));
  }
}


class Employee {

  private List<String> phoneNumbers;

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public Employee setPhoneNumbers(List<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
    return this;
  }
}