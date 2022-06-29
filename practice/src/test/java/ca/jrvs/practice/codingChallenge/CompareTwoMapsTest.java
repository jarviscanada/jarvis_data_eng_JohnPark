package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructure.list.Employee;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CompareTwoMapsTest {
  CompareTwoMaps compareTwoMaps = new CompareTwoMaps();

  @Test
  public void compareMapsA1() {
    Map<String, String> asiaCapital1 = new HashMap<String, String>();
    asiaCapital1.put("Japan", "Tokyo");
    asiaCapital1.put("South Korea", "Seoul");

    Map<String, String> asiaCapital2 = new HashMap<String, String>();
    asiaCapital2.put("South Korea", "Seoul");
    asiaCapital2.put("Japan", "Tokyo");

    Map<String, String> asiaCapital3 = new HashMap<String, String>();
    asiaCapital3.put("Japan", "Tokyo");
    asiaCapital3.put("China", "Beijing");

    assertTrue(compareTwoMaps.compareMapsA1(asiaCapital1, asiaCapital2));
    assertFalse(compareTwoMaps.compareMapsA1(asiaCapital1, asiaCapital3));

    Map<Employee, List<String>> empStrMap1 = new HashMap<>();
    Map<Employee, List<String>> empStrMap2 = new HashMap<>();
    Map<Employee, List<String>> empStrMap3 = new HashMap<>();


    //Amy
    Employee amy = new Employee(1, "Amy", 25, 45000);
    List<String> amyPreviousCompanies = Arrays.asList("TD", "RBC", "CIBC");
    empStrMap1.put(amy, amyPreviousCompanies);
    empStrMap2.put(amy, amyPreviousCompanies);

    //Bob
    Employee bob = new Employee(2, "Bob", 25, 40000);
    List<String> bobPreviousCompanies = Arrays.asList("A&W", "Superstore");
    empStrMap1.put(bob, bobPreviousCompanies);
    empStrMap2.put(bob, bobPreviousCompanies);

    //Jacob
    Employee Jacob = new Employee(3, "Jacob", 23, 40000);
    List<String> jacobPreviousCompanies = Arrays.asList("McDonalds", "TimHortons");
    empStrMap3.put(amy, amyPreviousCompanies);
    empStrMap3.put(Jacob, jacobPreviousCompanies);


    assertTrue(compareTwoMaps.compareMapsA1(empStrMap1, empStrMap2));
    assertFalse(compareTwoMaps.compareMapsA1(empStrMap1, empStrMap3));

    }

  @Test
  public void compareMapsA2() {
    Map<String, String> asiaCapital1 = new HashMap<String, String>();
    asiaCapital1.put("Japan", "Tokyo");
    asiaCapital1.put("South Korea", "Seoul");

    Map<String, String> asiaCapital2 = new HashMap<String, String>();
    asiaCapital2.put("South Korea", "Seoul");
    asiaCapital2.put("Japan", "Tokyo");

    Map<String, String> asiaCapital3 = new HashMap<String, String>();
    asiaCapital3.put("Japan", "Tokyo");
    asiaCapital3.put("China", "Beijing");

    assertTrue(compareTwoMaps.compareMapsA2(asiaCapital1, asiaCapital2));
    assertFalse(compareTwoMaps.compareMapsA2(asiaCapital1, asiaCapital3));

    Map<Employee, List<String>> empStrMap1 = new HashMap<>();
    Map<Employee, List<String>> empStrMap2 = new HashMap<>();
    Map<Employee, List<String>> empStrMap3 = new HashMap<>();


    //Amy
    Employee amy = new Employee(1, "Amy", 25, 45000);
    List<String> amyPreviousCompanies = Arrays.asList("TD", "RBC", "CIBC");
    empStrMap1.put(amy, amyPreviousCompanies);
    empStrMap2.put(amy, amyPreviousCompanies);

    //Bob
    Employee bob = new Employee(2, "Bob", 25, 40000);
    List<String> bobPreviousCompanies = Arrays.asList("A&W", "Superstore");
    empStrMap1.put(bob, bobPreviousCompanies);
    empStrMap2.put(bob, bobPreviousCompanies);

    //Jacob
    Employee Jacob = new Employee(3, "Jacob", 23, 40000);
    List<String> jacobPreviousCompanies = Arrays.asList("McDonalds", "TimHortons");
    empStrMap3.put(amy, amyPreviousCompanies);
    empStrMap3.put(Jacob, jacobPreviousCompanies);


    assertTrue(compareTwoMaps.compareMapsA2(empStrMap1, empStrMap2));
    assertFalse(compareTwoMaps.compareMapsA2(empStrMap1, empStrMap3));
  }
}