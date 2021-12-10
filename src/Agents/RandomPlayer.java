package Agents;

import GUI.GuiCard;

import java.util.ArrayList;


public class RandomPlayer extends Player{


    public RandomPlayer(Team team){
        super(team);
    }

    @Override
    public int move() {
       return super.randomMove();
    }
}
