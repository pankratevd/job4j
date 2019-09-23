package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.chess.firuges.Cell.*;

public class BishopBlackTest {
    @Test
    public void checkInitBishopBlackPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A2);
        assertThat(bishopBlack.position(), is(Cell.A2));
    }

    @Test
    public void checkCopyBishopBlackPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A2);
        BishopBlack copyBishopBlack = (BishopBlack) bishopBlack.copy(Cell.A3);
        assertThat(copyBishopBlack.position(), is(Cell.A3));
    }

    @Test
    public void checkWayBishopBlackPosition() {
       BishopBlack bishopBlack = new BishopBlack(Cell.C1);
       assertArrayEquals(bishopBlack.way(bishopBlack.position(), Cell.G5), new Cell[] {D2, E3, F4, G5});
    }

    @Test
    public void checkThatDiagonalTrue() {
        BishopBlack bishopBlack  = new BishopBlack(A2);
        assertTrue(bishopBlack.isDiagonal(bishopBlack.position(), D5));
    }

    @Test
    public void checkThatDiagonalFalse() {
        BishopBlack bishopBlack  = new BishopBlack(A2);
        assertFalse(bishopBlack.isDiagonal(bishopBlack.position(), Cell.A1));
    }
}
