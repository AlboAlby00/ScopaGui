package ScopaLogic;

public enum Value {

    ACE(16,"1",1),
    TWO(12,"2",2),
    THREE(13,"3",3),
    FOUR(14,"4",4),
    FIVE(15,"5",5),
    SIX(18,"6",6),
    SEVEN(21,"7",7),
    JACK(10,"J",11),
    QUEEN(10,"Q",12),
    KING(10,"K",13);

    private final int valPrimiera;
    private final String symbol;
    private final int val;

    Value(int valPrimiera, String symbol, int val){
        this.valPrimiera=valPrimiera;
        this.symbol = symbol;
        this.val=val;
    }

    public int getValPrimiera() {
        return valPrimiera;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getVal() {
        return val;
    }
}
