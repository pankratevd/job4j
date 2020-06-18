package design;

import design.report.ReportHR;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportHRTest {

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

        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());

        ReportHR reportHR = new ReportHR();
        assertThat(reportHR.execute((em -> true), store), is(expected.toString()));
    }
}
