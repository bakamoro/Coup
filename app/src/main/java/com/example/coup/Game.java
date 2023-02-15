package com.example.coup;

public class Game {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Deck deck;
    private int num_of_players,require_player_num;
    public Game(Player player1, Player player2, Player player3, Player player4, Deck deck){
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.deck = deck;
    }
    public Game(){
        this.deck = new Deck();
        Card[] cards1 = deck.TakeTwoCards()
                ,cards2 = deck.TakeTwoCards()
                ,cards3 = deck.TakeTwoCards()
                ,cards4 = deck.TakeTwoCards();
        PersonalBank personalBank1 = new PersonalBank()
                ,personalBank2 = new PersonalBank()
                ,personalBank3 = new PersonalBank()
                ,personalBank4 = new PersonalBank();

        this.player1  = new Player(personalBank1,cards1[0],cards1[0], true);
        this.player2  = new Player(personalBank2,cards2[0],cards2[0], false);
        this.player3  = new Player(personalBank3,cards3[0],cards3[0], false);
        this.player4  = new Player(personalBank4,cards4[0],cards4[0], false);
    }

    public int getNum_of_players() {
        return num_of_players;
    }

    public void setNum_of_players(int num_of_players) {
        this.num_of_players = num_of_players;
    }

    public int getRequire_player_num() {
        return require_player_num;
    }

    public void setRequire_player_num(int require_player_num) {
        this.require_player_num = require_player_num;
    }
    public Player getMyPlayer(int myPlayerNumber) {
        switch (myPlayerNumber){
            case 1:{
                return player1;
            }
            case 2:{
                return player2;
            }
            case 3:{
                return player3;
            }
            case 4:{
                return player4;
            }
        }
        return player1;
    }
}
