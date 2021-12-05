package ScopaLogic;


import GUI.GuiCard;

public class Card implements Comparable<Card> {

    private Value value;
    private Suit suit;


    public Card(Value value, Suit suit){

        this.value = value;
        this.suit = suit;

    }

    public Suit getSuit() {
        return suit;
    }


    public Value getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value.getSymbol()+suit.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && suit == card.suit;
    }

    @Override
    public int compareTo(Card card) {
        if(card==null) return 0;
        else return this.getValue().compareTo(card.getValue());
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
