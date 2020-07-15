package isp;

public interface IMenuItem {

    IMenuItem getParent();

    int getLevel();

    String getTitle();

    void action();

}
