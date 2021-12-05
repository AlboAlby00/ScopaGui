package ScopaLogic;

public class PlayerDeck extends Deck{
    private int nScope;
    public PlayerDeck(){
        super();
        nScope=0;
    }

    public int getnScope() {
        return nScope;
    }
    public void addScopa(){
        nScope++;
    }
}
