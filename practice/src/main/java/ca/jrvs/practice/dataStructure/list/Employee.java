package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Employee {

  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    Employee compareEmployee = (Employee) obj;
    return compareEmployee.getId() == this.id && compareEmployee.getAge() == this.age
        && compareEmployee.getSalary() == this.salary && compareEmployee.getName()
        .equals(this.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary);
  }


  public static void main(String[] args) {
    Map<Employee, List<String>> empStrMap = new HashMap<>();

    //Amy
    Employee amy = new Employee(1, "Amy", 25, 45000);
    List<String> amyPreviousCompanies = Arrays.asList("TD", "RBC", "CIBC");
    empStrMap.put(amy, amyPreviousCompanies);
    //Bob
    Employee bob = new Employee(2, "Bob", 25, 40000);
    List<String> bobPreviousCompanies = Arrays.asList("A&W", "Superstore");
    empStrMap.put(bob, bobPreviousCompanies);

    System.out.println("Bob hashcode:" + bob.hashCode());
    System.out.println("Bob value:" + empStrMap.get(bob).toString());
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public long getSalary() {
    return salary;
  }
}
