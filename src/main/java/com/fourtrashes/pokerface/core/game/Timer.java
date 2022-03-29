package com.fourtrashes.pokerface.core.game;

import java.time.LocalDateTime;

public interface Timer {
    LocalDateTime setTime();
    LocalDateTime getCurrentTime();
    void reset();
    void syncTime();
}
