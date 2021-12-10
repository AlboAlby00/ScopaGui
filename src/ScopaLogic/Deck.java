package ScopaLogic;

import GUI.GuiCard;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    
    protected Stack<GuiCard> deckStack;

    public Deck(){
        deckStack=new Stack<>();
        //System.out.println(Integer.toString(this.deckStack.size()));
    }

    public Stack<GuiCard> getDeck() {
        return deckStack;
    }

    public void fill(boolean hasLoadedImages){
        for(Suit suit : Suit.values()){
            for(Value value : Value.values()){
                deckStack.push(new GuiCard(value,suit,true));
            }
        }
        shuffle();
    }

    public void clear(){
        this.deckStack.clear();
    }

    public GuiCard extract(){
        return this.deckStack.pop();
    }

    public void insert(GuiCard c){this.deckStack.push(c);}

    public void shuffle(){
        Collections.shuffle(this.deckStack);
    }

    boolean isEmpty(){
        return this.deckStack.size()==0;
    }

    void printDeck(){
        for(GuiCard c : this.deckStack){
            System.out.print(c.toString()+",");
        }
    }
}
