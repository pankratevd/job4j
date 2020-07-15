package isp;

import java.util.Objects;

public class SimpleItem implements IMenuItem, IActionItem {
    private String title;
    private IMenuItem parent;
    private IActionItem action;
    private int level;

    public SimpleItem(String title, IActionItem action) {
        this.title = title;
        this.action = action;
        this.level = 0;
    }

    public SimpleItem(String title, IMenuItem parent, IActionItem action) {
        this.title = title;
        this.parent = parent;
        this.level = parent.getLevel() + 1;
        this.action = action;
    }

    @Override
    public void action() {
    action.action();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public IMenuItem getParent() {
        return this.parent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleItem menuItem = (SimpleItem) o;
        return level == menuItem.level
                && title.equals(menuItem.title)
                && parent.equals(menuItem.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, parent, level);
    }
}
