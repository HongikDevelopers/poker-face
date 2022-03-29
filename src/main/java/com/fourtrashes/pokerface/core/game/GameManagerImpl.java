package com.fourtrashes.pokerface.core.game;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GameManagerImpl implements GameManager {

    ConcurrentMap<String, UserStatus> players = new ConcurrentHashMap<>();
    CardDeck cardDeck = new CardDeckImpl();

    public GameManagerImpl(ConcurrentMap<String, Object> userList){
        Iterator<String> sessionIds = userList.keySet().iterator();
        while (sessionIds.hasNext()) {
            String sessionId = sessionIds.next();
            players.put(sessionId, new UserStatus());
        }
    }

    @Override
    public void startGame() {

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

    @Override
    public void ready(String sessionId) {
        players.get(sessionId).setReady(true);
        if(isAllPlayerReady()){
            startGame();
        }
    }

    private boolean isAllPlayerReady(){
        Iterator<String> sessionIds = players.keySet().iterator();
        while (sessionIds.hasNext()) {
            String sessionId = sessionIds.next();
            if(!players.get(sessionId).isReady()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void firstDeal() {

    }

    @Override
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

    @Override
    public void getUserTurn(){

    }
}
