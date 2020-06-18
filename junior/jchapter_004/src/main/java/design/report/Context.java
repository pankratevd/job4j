package design.report;

import design.Employee;
import design.Store;

import java.util.function.Predicate;

public class Context {
    private ReportStrategy reportStrategy;

    public void setStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public String executeStrategy(Predicate<Employee> filter, Store store) {
        return reportStrategy.execute(filter, store);
    }
}
