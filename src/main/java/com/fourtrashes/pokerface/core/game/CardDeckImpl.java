package com.fourtrashes.pokerface.core.game;

import java.util.ArrayList;

public class CardDeckImpl implements CardDeck{

    final ArrayList<Integer> cardDeck = new ArrayList<Integer>() ;

    @Override
    public void setCards() {
        for(int i=1; i<= 52; i++){
            cardDeck.add(i);
        }
    }

    @Override
    public void shuffle() {
        setCards();
        /*
            TODO 카드 섞는 로직
                - 카드를 랜덤으로 섞는 로직 개발해야함
        */
    }

    @Override
    public Integer getTop() {
        Integer lastIndex = cardDeck.size()-1;
        Integer ret = cardDeck.get(lastIndex);
        cardDeck.remove(cardDeck.get(lastIndex));
        return ret;
    }
}
