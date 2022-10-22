import java.util.*;

public class Organization {
    Map<Integer, Employee> employees = new HashMap<>();
    Map<Integer, List<Integer>> reportedEmployees = new HashMap<>();

    public void addEmployees(List<Employee> le) {
        for (Employee e : le) {
            employees.put(e.getId(), e);
            int currEmployeeId = e.getId();
            int currManagerId = e.getManagerId();

            if (reportedEmployees.containsKey(currManagerId)) {
                List<Integer> listOfReportedEmployees = reportedEmployees.get(currManagerId);
                listOfReportedEmployees.add(currEmployeeId);
                reportedEmployees.put(currManagerId, listOfReportedEmployees);
            } else {
                List<Integer> startingList = new ArrayList<>();
                startingList.add(currEmployeeId);
                reportedEmployees.put(currManagerId, startingList);
            }
        }
    }

    public void removeEmployees(List<Employee> le) {
        for (Employee e : le) {

        }
    }
}
