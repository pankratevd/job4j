package xo.controller;

import org.junit.Test;
import xo.controller.interfaces.IComputerMoveLogic;
import xo.controller.interfaces.IGameLogic;
import xo.model.Figure;
import xo.model.Frame;
import xo.model.Point;
import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ComputerMoveTest {
    @Test
    public void getPoint() {
        IComputerMoveLogic cml = new ComputerMove();
        Set<IPoint> points = new HashSet<>();
        IFrame frame = new Frame(3);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                points.add(new Point(i, j));
            }
        }
        IGameLogic logic = new SimpleLogic();

        for (int i = 0; i < 100; i++) {
            assertTrue(points.contains(cml.getPoint(frame, logic, Figure.X)));
        }

    }

}