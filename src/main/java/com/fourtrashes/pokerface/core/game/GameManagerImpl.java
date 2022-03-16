package com.fourtrashes.pokerface.core.game;

public class GameManagerImpl implements GameManager{

    // CardDeck은 singleton으로 관리되면 안됌
    CardDeck cardDeck = new CardDeckImpl();

    @Override
    public void startGame() {
        cardDeck.shuffle();
        int i = 0;
        while(i < 7){
        /*
            TODO 게임로직 개발
                - 게임이 끝난 이후 endGame 호출
        */
            setTimer();
            deal();
            bet();
            i++;
        }
        endGame();
    }

    @Override
    public void endGame() {
        settle();
    }

    @Override
    public void bet() {

    }

    @Override
    public void deal() {

    }

    @Override
    public void setTimer() {

    }

    @Override
    public void settle() {

    }
}
