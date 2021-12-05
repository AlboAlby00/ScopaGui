package ScopaLogic;

public enum Suit {
    CLUB(3,"C"),
    SPADE(1,"S"),
    HART(0,"H"),
    DIAMOND(2,"D");
    private final int n;
    private final String symbol;
    Suit(int n, String symbol){
        this.n=n;
        this.symbol=symbol;
    }

    public int getN() {
        return n;
    }

    public String getSymbol() {
        return symbol;
    }
}
