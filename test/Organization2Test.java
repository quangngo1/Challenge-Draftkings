import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class Organization2Test {
    Organization2 org2 = new Organization2();
    Employee ceo = new Employee(0, "CEO", -1);

    Employee productionManager = new Employee(1, "Production Manager", 0);
    Employee marketingManager = new Employee(2, "Marketing Manager", 0);

    Employee fabrication = new Employee(3, "Fabrication Foreman", 1);
    Employee assembly = new Employee(4, "Assembly Foreman", 1);
    Employee worker = new Employee(5, "Common worker", 1);

    Employee saleA = new Employee(6, "Sale Person A", 2);
    Employee saleB = new Employee(7, "Sale Person B", 2);
    Employee salePerson = new Employee(8, "Sale PERSONS", 2);

    @Before
    public void addEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(ceo);
        employees.add(productionManager);
        employees.add(marketingManager);
        employees.add(fabrication);
        employees.add(assembly);
        employees.add(worker);
        employees.add(saleA);
        employees.add(saleB);
        employees.add(salePerson);
        org2.addEmployees(employees);
    }

    @Test
    public void testCount() {
        assertEquals(org2.count(0), 8);
        assertEquals(org2.count(1), 3);
        assertEquals(org2.count(2), 3);
        assertEquals(org2.count(3), 0);
        assertEquals(org2.count(6), 0);
    }

    @Test
    public void testPrint() {
        assertEquals("CEO [0]\n" +
                " Production Manager [1]\n" +
                "  Fabrication Foreman [3]\n" +
                "  Assembly Foreman [4]\n" +
                "  Common worker [5]\n" +
                " Marketing Manager [2]\n" +
                "  Sale Person A [6]\n" +
                "  Sale Person B [7]\n" +
                "  Sale PERSONS [8]", org2.print());
    }
}