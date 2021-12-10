package ScopaLogic;

import GUI.GuiCard;

public class PlayerDeck extends Deck{
    private int nScope;
    public PlayerDeck(){
        super();
        nScope=0;
    }

    public int getnScope() {
        return nScope;
    }
    public void scopa(){
        nScope++;
    }
    void printDeck(){
        for(GuiCard c : this.deckStack){
            System.out.print(c.toString()+",");
        }
        System.out.print("\n");
        System.out.println("n of scope: "+this.nScope);
    }
}
