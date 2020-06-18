package design;

import design.report.ReportCSV;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportCSVTest {
    @Test
    public void executeTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Serg", now, now, 200);
        Employee worker2 = new Employee("Alex", now, now, 50);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);

        ReportCSV reportCSV = new ReportCSV();

        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(formatDate(worker.getHired())).append(";")
                .append(formatDate(worker.getFired())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(formatDate(worker1.getHired())).append(";")
                .append(formatDate(worker1.getFired())).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(formatDate(worker2.getHired())).append(";")
                .append(formatDate(worker2.getFired())).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportCSV.execute(em -> true, store), is(expect.toString()));
    }

    private String formatDate(Calendar cal) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        result = df.format(cal.getTime());
        return result;
    }
}
