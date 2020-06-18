package design.report;

import design.Employee;
import design.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class to generate Report in format according to request HR.
 * There is list of Employees with name and salary in descending order.
 */
public class ReportHR implements ReportStrategy {
    /**
     * Returns String with content in CSV
     * @param filter a predicate to filter Employees
     * @param store
     * @return String with Employee in CSV format
     */
    @Override
    public String execute(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        list.sort(Comparator.comparing(Employee::getSalary).reversed());
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
