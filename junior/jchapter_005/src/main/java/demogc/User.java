package demogc;

public class User {
    private String name;
    private int age;


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalized");
    }
}
