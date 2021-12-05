package ScopaLogic;

import Agents.Player;
import Agents.RandomPlayer;
import Agents.Team;
import GUI.GuiCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBackEnd {

    private Deck scopaDeck;
    private Player[] playerArray;
    private List<GuiCard> table;
    private final Map<Team,PlayerDeck> teamDeck;
    private int currentPlayer;


    public GameBackEnd(){
        //initialize deck
        this.scopaDeck = new Deck();
        this.scopaDeck.fill(true);
        //initialize players
        this.playerArray= new Player[4];
        this.playerArray[0]=new Player(Team.TEAM1);
        this.playerArray[1]=new RandomPlayer(Team.TEAM2);
        this.playerArray[2]=new RandomPlayer(Team.TEAM1);
        this.playerArray[3]=new RandomPlayer(Team.TEAM2);
        this.currentPlayer=0;
        for(Player p : this.playerArray){
            p.fillHand(this.scopaDeck);
        }
        //initialize table
        table= new ArrayList<>();
        //initialize team decks
        teamDeck= new HashMap<>();
        for(Team t : Team.values()){
            teamDeck.put(t,new PlayerDeck());
        }
    }

    public Player getPlayer(int i){
        return this.playerArray[i];
    }

    public boolean isOver(){
        GuiCard blackCard= GuiCard.createBlackCard();
        for(GuiCard c : getPlayer(1).getHand()){
            if(!c.equals(blackCard)) return false;
        }
        return true;
    }

    public GuiCard selectCard(Player p, int index){
        return p.selectCard(index);
    }

    public List<GuiCard> getTable() {
        return table;
    }

    public void playerMove(Player pl, int pos) {
        PlayerDeck deck = teamDeck.get(pl.getTeam());
        GuiCard cardFromHand = pl.dropCard(pos);
        boolean cardFound=false;
        //System.out.println("move");
        if(cardFromHand.getValue()==Value.ACE){
            cardFound=true;
            for(GuiCard cardFromTable : this.table){
                deck.insert(cardFromTable);
            }
            deck.insert(cardFromHand);
            table.clear();
        }
        else{
            for(GuiCard cardFromTable : this.table){
                if(cardFromHand.getValue()==cardFromTable.getValue()) {
                    cardFound=true;
                    deck.insert(cardFromTable);
                    deck.insert(cardFromHand);
                    this.table.remove(cardFromTable);
                    break;
                }
            }
        }
        if(!cardFound){
            table.add(cardFromHand);
        }
        this.togglePlayer();
    }

    private void togglePlayer(){
        this.currentPlayer=(this.currentPlayer+1)%4;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void calculatePoints(){
        int scoreTeam1=ScopaTeamDeckRanker.punteggio(this.teamDeck.get(Team.TEAM1));
        int scoreTeam2=ScopaTeamDeckRanker.punteggio(this.teamDeck.get(Team.TEAM2));
        if(scoreTeam2<scoreTeam1){
            System.out.println("Team 1 is winner "+"\nTeam 1 score: "+scoreTeam1+"\nTeam 2 score: "+scoreTeam1);
        }
        else System.out.println("Team 2 is winner "+"\nTeam 1 score: "+scoreTeam1+"\nTeam 2 score: "+scoreTeam1);
    }
}
