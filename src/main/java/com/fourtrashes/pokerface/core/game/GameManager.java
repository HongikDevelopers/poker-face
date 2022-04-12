package com.fourtrashes.pokerface.core.game;

import java.util.concurrent.ConcurrentMap;

public interface GameManager {
    void startGame(ConcurrentMap<String, Object> users);
    void settle();
}
