package com.fourtrashes.pokerface.core.game;

public interface GameManager {
    void startGame();
    void endGame();
    void ready(String sessionId);
    void bet();
    void firstDeal();
    void deal();
    void lastDeal();
    void settle();
    void getUserTurn();
    void setUser(String sessionId);
}
