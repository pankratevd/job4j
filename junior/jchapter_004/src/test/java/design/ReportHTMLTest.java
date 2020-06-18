package design;

import design.report.ReportHTML;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportHTMLTest {

    @Test
    public void executeTet() {
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
        ReportHTML reportHTML = new ReportHTML();
        assertThat(reportHTML.execute((em -> true), store), is(expected));
    }
}
