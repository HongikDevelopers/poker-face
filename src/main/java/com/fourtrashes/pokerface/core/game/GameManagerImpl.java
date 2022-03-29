package com.fourtrashes.pokerface.core.game;

public class GameManagerImpl implements GameManager {

    // CardDeck은 singleton으로 관리되면 안됌
    CardDeck cardDeck = new CardDeckImpl();

    @Override
    public void startGame() {

        checkReady();

        cardDeck.shuffle();

        // 카드 4장씩 나누어주고 한장 버리는 로직
        firstDeal();

        // 배팅
        bet();

        // 4번째 카드
        deal();

        // 배팅
        bet();

        // 5번째 카드
        deal();

        // 배팅
        bet();

        // 6번째 카드
        deal();

        // 배팅
        bet();

        // 히든 카드
        lastDeal();

        // 배팅
        bet();

        endGame();
    }

    public void checkReady() {

    }

    public void firstDeal() {

    }

    public void lastDeal(){

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
