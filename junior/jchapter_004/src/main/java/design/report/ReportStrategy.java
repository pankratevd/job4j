package design.report;

import design.Employee;
import design.Store;

import java.util.function.Predicate;

public interface ReportStrategy {
    String execute(Predicate<Employee> filter, Store store);
}
