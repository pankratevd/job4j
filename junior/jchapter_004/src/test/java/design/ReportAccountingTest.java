package design;

import design.report.ReportAccounting;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportAccountingTest {
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

        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(formatDate(worker.getHired())).append(";")
                .append(formatDate(worker.getFired())).append(";")
                .append("100,00 ₽").append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(formatDate(worker1.getHired())).append(";")
                .append(formatDate(worker1.getFired())).append(";")
                .append("200,00 ₽").append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(formatDate(worker2.getHired())).append(";")
                .append(formatDate(worker2.getFired())).append(";")
                .append("50,00 ₽").append(";")
                .append(System.lineSeparator());

        ReportAccounting reportAccounting = new ReportAccounting();
        assertThat(reportAccounting.execute((em -> true), store), is(expect.toString()));
    }
    private String formatDate(Calendar cal) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        result = df.format(cal.getTime());
        return result;
    }
}
