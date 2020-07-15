package isp;

import java.util.List;

public interface IBaseMenu {

    void addMenuItem(IMenuItem item);

    List<IMenuItem> getItems();

    IMenuItem getItem(String str);

    void printMenu();

    String input();

}
