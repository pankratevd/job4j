package design;

import design.report.Context;
import design.report.ReportStrategy;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Returns String of Employees in CSV format (delimiter is semicolon) according to filter
     * @param filter a predicate to filter Employees
     * @return String of Employees in CSV format
     */
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        text.append(System.lineSeparator());
        return text.toString();
    }

    /**
     * Returns filtered String of Employees in specific format defined in parameters type
     * @param filter a predicate to filter Employees
     * @param type of Report a class implements interface ReportStrategy
     * @return String according to type of Report and condition in filter
     */
    public String generate(Predicate<Employee> filter, ReportStrategy type) {
        Context context = new Context();
        context.setStrategy(type);
        return context.executeStrategy(filter, store);
    }
}
