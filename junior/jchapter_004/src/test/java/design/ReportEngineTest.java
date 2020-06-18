package design;

import design.report.ReportAccounting;
import design.report.ReportCSV;
import design.report.ReportHR;
import design.report.ReportHTML;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenCSVReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Serg", now, now, 200);
        Employee worker2 = new Employee("Alex", now, now, 50);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);

        ReportEngine reportEngine = new ReportEngine(store);
        reportEngine.generate(em -> true, new ReportCSV());

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
        assertThat(reportEngine.generate(em -> true, new ReportCSV()), is(expect.toString()));
    }

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Serg", now, now, 200);
        Employee worker2 = new Employee("Alex", now, now, 50);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        String expected = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
                + "<html><body><h1>Name&nbsp;&nbsp;&nbsp;Hired&nbsp;&nbsp;Fired&nbsp;&nbsp;Salary&nbsp;&nbsp;</h1>"
                + "<body>Ivan&nbsp&nbsp&nbsp2020-06-18&nbsp2020-06-18&nbsp100.0<br>"
                + "Serg&nbsp&nbsp&nbsp2020-06-18&nbsp2020-06-18&nbsp200.0<br>"
                + "Alex&nbsp&nbsp&nbsp2020-06-18&nbsp2020-06-18&nbsp50.0<br></body>"
                + "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><html>";
        ReportEngine reportEngine = new ReportEngine(store);
        assertThat(reportEngine.generate((em -> true), new ReportHTML()), is(expected));
    }

    @Test
    public void whenAccountReport() {
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

        ReportEngine reportEngine = new ReportEngine(store);
        assertThat(reportEngine.generate((em -> true), new ReportAccounting()), is(expect.toString()));
    }

    @Test
    public void whenHRReport() {
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

        ReportEngine reportEngine = new ReportEngine(store);
        assertThat(reportEngine.generate((em -> true), new ReportHR()), is(expected.toString()));
    }


    private String formatDate(Calendar cal) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        result = df.format(cal.getTime());
        return result;
    }
}
