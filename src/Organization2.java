import java.util.*;

public class Organization2 {

    Map<Integer, Employee> employees = new LinkedHashMap<>();
    //StringBuilder orgStructure = new StringBuilder();

    public void addEmployees(List<Employee> le) {
        for (Employee e : le) {
            employees.put(e.getId(), e);
        }
    }

    public void removeEmployees(List<Integer> ids) {
        for (Integer id : ids) {
            employees.remove(id);
        }
    }

    public void moveEmployees(int id, int newId) throws IllegalArgumentException {
        if (!employees.containsKey(id)) {
            throw new IllegalArgumentException("Employee does not exist");
        } else {
            employees.get(id).setManagerId(newId);
        }
    }

    public int count(int managerId) {
        // if the manager does not exist
        if(!employees.containsKey(managerId)) return -1;

        // initialized visited and reportedEmployees
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Employee>> reportedEmployees = new LinkedHashMap<>();

        // create a hash table of all the employees that reported to their manager
        for(Employee employee : employees.values()) {
            int currManagerId = employee.getManagerId();

            if (reportedEmployees.containsKey(currManagerId)) {
                List<Employee> listOfReportedEmployees = reportedEmployees.get(currManagerId);
                listOfReportedEmployees.add(employee);
                reportedEmployees.put(currManagerId, listOfReportedEmployees);
            } else {
                List<Employee> startingList = new ArrayList<>();
                startingList.add(employee);
                reportedEmployees.put(currManagerId, startingList);
            }
        }

        return dfs(reportedEmployees, visited, managerId);
    }

    private int dfs(Map<Integer, List<Employee>> reportedEmployees, Set<Integer> visited, int managerId) {
        int res = 0;

        for(Employee employee : reportedEmployees.getOrDefault(managerId, new ArrayList<>())) {
            // if we did not visit this employee
            if(!visited.contains(employee.getId())) {
                // we add it to the list of visited employees
                visited.add(employee.getId());
                res += (1 + dfs(reportedEmployees, visited, employee.getId()));
            }
        }
        return res;
    }

    public String print() {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Employee>> reportedEmployees = new LinkedHashMap<>();

        // loop through each of the employee
        for(Employee employee : employees.values()) {
            int currManagerId = employee.getManagerId();

            if (reportedEmployees.containsKey(currManagerId)) {
                List<Employee> listOfReportedEmployees = reportedEmployees.get(currManagerId);
                listOfReportedEmployees.add(employee);
                reportedEmployees.put(currManagerId, listOfReportedEmployees);
            } else {
                List<Employee> startingList = new ArrayList<>();
                startingList.add(employee);
                reportedEmployees.put(currManagerId, startingList);
            }
        }
        StringBuilder orgStructure = new StringBuilder();
        dfs(reportedEmployees, visited, -1, "", orgStructure);
        return orgStructure.substring(0, orgStructure.length() - 1);
    }

    private void dfs(Map<Integer, List<Employee>> reportedEmployees, Set<Integer> visited, int managerId, String s, StringBuilder orgStructure) {
        List<Employee> listEmployees =  reportedEmployees.getOrDefault(managerId, new ArrayList<>());
        // loop through each of the employee that reported to this manager
        for(Employee employee : listEmployees) {
            // if we did not look at this employee
            if(!visited.contains(employee)) {
                orgStructure.append(s + employee);
                visited.add(employee.getId());
                dfs(reportedEmployees, visited, employee.getId(), s + " ", orgStructure);
            }
        }
    }
}
