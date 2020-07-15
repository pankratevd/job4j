package isp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleMenu implements IBaseMenu {

    private List<IMenuItem> items = new ArrayList<>();
    private final IMenuOutput output;
    private final IMenuInput input;

    public SimpleMenu(IMenuInput input, IMenuOutput output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void addMenuItem(IMenuItem item) {
        if (item.getParent() == null) {
            items.add(item);
        } else {
            int start = -1;
            start = items.indexOf(item.getParent());

            if (start == -1) {
                throw new NoSuchElementException();
            }

            for (int i = start; i < items.size(); i++) {
                if (items.get(i).getParent() != item.getParent()) {
                    items.add(++i, item);
                    break;
                }
            }
        }
    }

    @Override
    public String input() {
        return this.input.input();
    }

    @Override
    public void printMenu() {
        this.output.output(this);
    }

    @Override
    public IMenuItem getItem(String str) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (str.equals(items.get(i).getTitle())) {
                index = i;
                break;
            }
        }
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public List<IMenuItem> getItems() {
        return items;
    }
}
