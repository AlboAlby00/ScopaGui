package Agents;

import GUI.GuiCard;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player{

    private Random generator;
    public RandomPlayer(Team team){
        super(team);
        this.generator = new Random();
    }

    @Override
    public int move() {
        ArrayList<GuiCard> actualCards = new ArrayList<>();
        GuiCard blackCard= GuiCard.createBlackCard();
        for(int i = 0; i< 10; i++){
            if(!this.hand[i].equals(blackCard)){
                actualCards.add(this.hand[i]);
            }
        }
        GuiCard c = actualCards.get(this.generator.nextInt(actualCards.size()));
        for(int i=0; i<10;i++){
            if(this.hand[i].equals(c))
                return i;
        }
        return 0;
    }
}
