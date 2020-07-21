package xo.view;

import xo.model.interfaces.IFrame;
import xo.view.interfaces.IField;

public class ConsoleFiled implements IField {

    /**
     * Вывод на экран игрового поле frame
     * @param frame
     */
    @Override
    public void draw(IFrame frame) {
        for (int i = 0; i < frame.getFigures().length; i++) {
            for (int j = 0; j < frame.getFigures().length; j++) {
                if (frame.getFigures()[i][j] == null) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + frame.getFigures()[i][j]);
                }
            }
            System.out.println();
        }
    }
}
