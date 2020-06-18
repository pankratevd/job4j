package design.report;

import design.Employee;
import design.Store;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.function.Predicate;

public class ReportAccounting implements ReportStrategy {
    @Override
    public String execute(Predicate<Employee> filter, Store store) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("ru-RU"));
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(formatDate(employee.getHired())).append(";")
                    .append(formatDate(employee.getFired())).append(";")
                    .append(numberFormat.format(employee.getSalary())).append(";")
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
