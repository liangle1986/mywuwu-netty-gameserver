package com.mywuwu.gameserver.card.poker;

public enum PokerNumber {
    two,
    three,
    Four,
    Five,
    Six,
    Seven,
    Nine,
    eight,
    Ten,
    J,
    Q,
    K,
    A;

    public static int getNumberPoker(PokerNumber number) {

        switch (number) {
            case two:
                return 2;
            case three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case eight:
                return 8;
            case Nine:
                return 9;
            case A:
                return 1;
            default:
                return 10;
        }
    }

}
