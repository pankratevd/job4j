package design.report;

import design.Employee;
import design.Store;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

/**
 * Class to generate Report in CSV format (decimeter is semicolon).
 */
public class ReportCSV implements ReportStrategy {

    /**
     * Returns String of filtered Employees in CSV format
     * @param filter a predicat to filter Employees
     * @param store
     * @return String with Employee in CSV format
     */
    @Override
    public String execute(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(formatDate(employee.getHired())).append(";")
                    .append(formatDate(employee.getFired())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());

        }
        return text.toString();
    }

    /**
     * Returns date in format "YYYY-mm-dd"
     * @param cal variable with type Calendar to format
     * @return String with formatted date.
     */
    private String formatDate(Calendar cal) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        result = df.format(cal.getTime());
        return result;
    }
}
