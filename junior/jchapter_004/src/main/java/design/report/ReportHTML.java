package design.report;

import design.Employee;
import design.Store;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

/**
 * Generate report with Employees in HTML format.
 */

public class ReportHTML implements ReportStrategy {
    private String tableTitle = "Name&nbsp;&nbsp;&nbsp;Hired&nbsp;&nbsp;Fired&nbsp;&nbsp;Salary&nbsp;&nbsp;";

    /**
     * Returns String with content in HTML
     * @param filter a predicate to filter Employees
     * @param store
     * @return String in HTML format
     */
    @Override
    public String execute(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append(htmlOpen())
                .append(htmlOpenBody(tableTitle));
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append("&nbsp&nbsp&nbsp")
                    .append(formatDate(employee.getHired())).append("&nbsp")
                    .append(formatDate(employee.getFired())).append("&nbsp")
                    .append(employee.getSalary()).append("<br>");
        }
        text.append("</body>")
                .append(htmlClose());
        return text.toString();
    }

    private String htmlOpen() {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><html>";
    }

    private String htmlClose() {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><html>";
    }

    /**
     * Returns String with opened html tags and set title with tag h1.
     * @param title set in h1 tag
     * @return String with title in h1 tag and opened tag body.
     */
    private String htmlOpenBody(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("<body>")
                .append("<h1>")
                .append(title)
                .append("</h1>")
                .append("<body>");
        return sb.toString();
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
