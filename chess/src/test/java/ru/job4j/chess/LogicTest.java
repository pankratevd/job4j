package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.white.BishopWhite;

import static org.junit.Assert.*;
import static ru.job4j.chess.firuges.Cell.*;

public class LogicTest {

    @Test
    public void checkWayWhenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(D5));
        logic.add(new BishopWhite(C3));
        assertTrue(logic.move(D5, C4));

    }

    @Test
    public void checkWayWhenFigureInMiddleThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(D5));
        logic.add(new BishopWhite(C4));
        assertFalse(logic.move(D5, B2));
    }

    @Test
    public void checkWayWhenFigureAtEndFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(D5));
        logic.add(new BishopWhite(B2));
        assertFalse(logic.move(D5, B2));
    }
}
