package com.fourtrashes.pokerface.core.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatus {

    public UserStatus(){
        this.turn = -1;
        this.isDead = false;
        this.isReady = false;
    }

    private int turn;
    private boolean isDead;
    private boolean isReady;
}
