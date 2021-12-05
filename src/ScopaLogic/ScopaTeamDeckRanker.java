package ScopaLogic;

import GUI.GuiCard;

import java.util.Arrays;
import java.util.Stack;

public class ScopaTeamDeckRanker {

    private static int scoreDiamonds(PlayerDeck deck){
        int nDiamonds =0;
        for(GuiCard c: deck.getDeck() ){
            if(c.getSuit()==Suit.DIAMOND) {
                nDiamonds++;
                if(nDiamonds>5) return 1;
            }
        }
        return 0;
    }
    private static int scoreNumberCards(PlayerDeck deck){
        if(deck.getDeck().size()>20) return 1;
        else return 0;
    }
    private static int score7Diamonds(PlayerDeck deck){
        if(deck.getDeck().contains(new GuiCard(Value.SEVEN,Suit.DIAMOND,false)))
            return 1;
        else return 0;
    }
    private static int napola(PlayerDeck deck){
        int count=0;
        Stack<GuiCard> stackDeck = deck.getDeck();
        for(Value v : Value.values()){
            if(stackDeck.contains(new GuiCard(v,Suit.DIAMOND,false))) count++;
            else break;
        }
        if(count<3) return 0;
        else return count;

    }
    private static int primiera(PlayerDeck deck){

        if(primieraValue(deck)>primieraValue(complementaryDeck(deck))) return 1;
        else return 0;

    }
    private static int primieraValue(PlayerDeck deck){
        int[] maxSuit = new int[4];
        Stack<GuiCard> stackDeck=deck.getDeck();
        for(GuiCard c : stackDeck){
            maxSuit[c.getSuit().getN()]=Math.max(maxSuit[c.getSuit().getN()],c.getValue().getValPrimiera());
        }
        return Arrays.stream(maxSuit).sum();
    }
    private static PlayerDeck complementaryDeck(PlayerDeck deck){
        PlayerDeck fullDeck = new PlayerDeck();
        PlayerDeck complDeck = new PlayerDeck();
        fullDeck.fill(false);
        for( GuiCard c : fullDeck.getDeck()){
            if(!deck.getDeck().contains(c)) complDeck.getDeck().add(c);
        }
        return complDeck;
    }
    public static int punteggio(PlayerDeck deck){
        return score7Diamonds(deck)+scoreDiamonds(deck)+deck.getnScope()+scoreNumberCards(deck)+napola(deck)+primiera(deck);
    }

}
