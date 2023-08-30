package com.lyurkan.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест выявил ошибку в классе DiceImpl, метод roll() генерирует не только значения, которые входят в диапазон 1-6
 */
class DiceImplTest {

    @Test
    void whenRollAndResultIsNotInDiapason() {
        Dice dice = new DiceImpl();

        int rollNumber = dice.roll();
        boolean isInDiapason = rollNumber > 0 && rollNumber < 7;

        if (!isInDiapason) {
            System.err.println("Полученное число не входит в диапазон от 1 до 6");
        }

        assertFalse(isInDiapason);
    }

}
