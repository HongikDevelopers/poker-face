package com.fourtrashes.pokerface.core.game;

public interface GameManager {
    void startGame();
    void endGame();
    void bet();
    void deal();
    void setTimer();
    void settle();
}
