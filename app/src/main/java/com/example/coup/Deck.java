package com.example.coup;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Deck {
    private Queue<Card> deck = new LinkedList<>();
    public Deck(){
        Card[] all = new Card[15];
        int random[] = new int[15];
        for(int i = 0; i<14; i++){
            Random rand = new Random();
            int rand1 = rand.nextInt(15);
            while(inRandom(random,rand1)){
                rand1 = rand.nextInt(15);
            }
            random[i] = rand1;
        }
        fillAll(all,random);
        addCards(all);
    }

    private void fillAll(Card[] all, int[] random) {
        for (int i = 0;i<15;i++){
                if (i<=3) {
                    all[random[i]] = new Captain();
                }
                else if (i<= 6) {
                    all[random[i]] = new Ambassador();
                }
                else if( i<= 9){
                    all[random[i]] = new Contessa();
                }
                else if(i<= 12) {
                    all[random[i]] = new Duke();
                }
                else if( i<= 15){
                    all[random[i]] = new Assassin();
                }
        }
    }

    private boolean inRandom(int[] random, int rand1) {
        for(int i = 0; i<15; i++){
            if(random[i] == rand1){
                return true;
            }
        }
        return false;
    }

    private void addCards(Card[] all){
        for(int i = 0 ;i<15 ; i++) {
            deck.add(all[i]);
        }
    }
    public Card[] TakeTwoCards(){
        Card[] cards = new Card[2];
        cards[0] = deck.poll();
        cards[1] = deck.poll();
        return cards;
    }

    //TODO: check with the debugger that deck function works.
    public void returnTwoCards(Card card1, Card card2){
        deck.add(card1);
        deck.add(card2);

    }
}
