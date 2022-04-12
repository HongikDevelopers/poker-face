package com.fourtrashes.pokerface.core.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

@Getter
public class PokerGameManager implements GameManager {

    private ArrayList<Player> players;
    private CardDeck cardDeck = new CardDeckImpl();
    private int counter = 0;

    // 로직변경 : 모든 유저가 레디상태가 되면 GameManager의 startGame(유저 목록)을 불러주세요.
    @Override
    public void startGame(ConcurrentMap<String, Object> users) {
        cardDeck.shuffle();

        Iterator<String> sessionIds = users.keySet().iterator();
        int turn = 0;
        while (sessionIds.hasNext()) {
            String sessionId = sessionIds.next();
            Player player = new Player(sessionId);
            player.setTurn(turn++);
            players.add(player);
        }

    }

    public void firstDeal() {
        int turn = getUserTurn();
        // 카드 4장 배분
        for(int i=0;i<players.size() * 4;i++){
            int card = cardDeck.getTop();
            players.get(turn).putInCard(card, false);
            turn = (turn + 1) % players.size();
        }

    }

    public void openAndDump(String sessionId, int openCard, int dumpCard){
        for(Player player : players){
            if(player.getSessionId().equals(sessionId)){
                player.setReady(true);
                player.openCard(openCard);
                player.dumpCard(dumpCard);
                return;
            }
        }
    }

    public void lastDeal(){

    }

    public void startBet(){
        setPlayerReady();
    }

    public String getNextBetPlayer(){
        int turn = getUserTurn();
        return players.get(turn).getSessionId();
    }

    public void bet(String sessionId, String type, Long amount) {

    }

    public void deal() {

    }

    @Override
    public void settle() {

    }

    private int getUserTurn(){
        /*
            4명의 플레이어중에 가장 높은 패를 들고있는 플레이어의 index를 return 해야함.
            자리 순서대로 카드가 분배될 것이므로 인원수만큼 반복 돌리면 됌.
         */
        return 0;
    }

    public boolean isAllPlayerReady(){
        for(Player player : players){
            if(!player.isReady()){
                return false;
            }
        }
        return true;
    }

    private void setPlayerReady(){
        for(Player player : players) {
            player.setReady(false);
        }
    }
}
