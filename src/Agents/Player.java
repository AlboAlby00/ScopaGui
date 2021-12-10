package Agents;

import GUI.GuiCard;
import ScopaLogic.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {

    protected final Team team;
    protected final GuiCard[] hand;


    public Player(Team team){
        this.team=team;
        this.hand=new GuiCard[10];
    }

    public GuiCard[] getHand() {
        return hand;
    }

    public void fillHand(Deck dk){
        for(int i=0; i<10; i++){
            this.hand[i]=dk.extract();
        }
        orderHand();
    }

    public void printHand(){
        for(int i=0; i<10;i++){
            System.out.print(this.hand[i].toString()+", ");
        }
    }

    public GuiCard dropCard(int pos){
        GuiCard c = this.hand[pos];
        this.hand[pos]= GuiCard.createBlackCard();
        return c;

    }

    public GuiCard selectCard(int pos){
        GuiCard c = this.hand[pos];
        return c;

    }


    public int move(){
        return 0;
    }

    public int randomMove(){
        Random generator = new Random();
        ArrayList<GuiCard> actualCards = new ArrayList<>();
        GuiCard blackCard= GuiCard.createBlackCard();
        for(int i = 0; i< 10; i++){
            if(!this.hand[i].equals(blackCard)){
                actualCards.add(this.hand[i]);
            }
        }
        GuiCard c = actualCards.get(generator.nextInt(actualCards.size()));
        for(int i=0; i<10;i++){
            if(this.hand[i].equals(c))
                return i;
        }
        return 0;
    }

    public void orderHand(){
        Arrays.sort(this.hand);
    }

    public Team getTeam() {
        return team;
    }
}