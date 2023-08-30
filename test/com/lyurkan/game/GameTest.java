package com.lyurkan.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты выявили ошибку в работе класса Game, когда происходит ничья выигрывает только 2 игрок
 */
class GameTest {

    private final Player player1 = new Player("Ivan");
    private final Player player2 = new Player("Petr");

    @Test
    void whenPlayGameAndFirstPlayerIsWinner() {
        GameWinnerPrinter printer = winner -> assertEquals(player1.getName(), winner.getName());

        Dice dice = new Dice() {
            private int count = 0;

            @Override
            public int roll() {
                if (count++ == 0) {
                    return 6;
                }
                return 2;
            }
        };

        Game game = new Game(dice, printer);
        game.playGame(player1, player2);

        System.out.println("Тест прошел успешно, победил 1 игрок");
    }

    @Test
    void whenPlayGameAndSecondPlayerIsWinner() {
        GameWinnerPrinter printer = winner -> assertEquals(player2.getName(), winner.getName());

        Dice dice = new Dice() {
            private int count = 0;

            @Override
            public int roll() {
                if (count++ == 0) {
                    return 1;
                }
                return 2;
            }
        };

        Game game = new Game(dice, printer);
        game.playGame(player1, player2);

        System.out.println("Тест прошел успешно, победил 2 игрок");
    }

    @Test
    void whenPlayGameAndFirstAndSecondIsWinnersThenSecondPlayerIsWinner() {
        GameWinnerPrinter printer = winner -> {
            assertNotEquals(player1.getName(), winner.getName());
            assertEquals(player2.getName(), winner.getName());
        };
        Dice dice = () -> 1;

        Game game = new Game(dice, printer);
        game.playGame(player1, player2);

        System.err.println("Тест провален, результат - ничья, победитель 2 игрок");
    }

}
