package Agents;

public enum Team {

    TEAM1("Team 1"),
    TEAM2("Team 2"),
    TIE("Tie");

    private final String teamString;

    Team(String teamString){
        this.teamString= teamString;
    }

    public String getTeamString() {
        return teamString;
    }
}
