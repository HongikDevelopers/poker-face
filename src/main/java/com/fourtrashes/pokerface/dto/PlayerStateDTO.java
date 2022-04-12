package com.fourtrashes.pokerface.dto;

import com.fourtrashes.pokerface.core.game.Card;
import com.fourtrashes.pokerface.core.game.Player;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PlayerStateDTO {
    private ArrayList<Card> cardList;
    private int turn;
    private boolean isDead;

    public PlayerStateDTO(Player player){
        this.cardList = player.getCardList();
        this.turn = player.getTurn();
        this.isDead = player.isDead();
    }

}
