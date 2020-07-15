package isp;

public class ConsoleOutput implements IMenuOutput {

    @Override
    public void output(IBaseMenu menu) {
        for (IMenuItem item : menu.getItems()) {
            for (int i = 0; i < item.getLevel(); i++) {
                System.out.print("--");
                if (i == item.getLevel() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(item.getTitle());
        }
    }
}
