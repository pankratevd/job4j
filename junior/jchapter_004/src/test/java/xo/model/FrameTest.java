package xo.model;

import org.junit.Test;
import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void acceptTest() {
        IFrame frame = new Frame(3);
        IPoint point = new Point(0, 0);

        assertTrue(frame.accept(point));

        point = new Point(3, 1);
        assertFalse(frame.accept(point));

    }

    @Test
    public void setFigureTest() {
        IFrame frame = new Frame(3);
        IPoint point = new Point(0, 0);
        Figure figure = Figure.O;

        frame.setFigure(figure, point);
        assertThat(frame.getFigures()[0][0], is(figure));

    }

}