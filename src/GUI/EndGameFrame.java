package GUI;

import Agents.Team;
import ScopaLogic.GameBackEnd;
import ScopaLogic.ScopaTeamDeckRanker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameFrame extends JFrame {

    private JLabel winnerLabel;
    private JLabel team1Label;
    private JLabel team2Label;
    private GameBackEnd game;
    private JButton button;

    public EndGameFrame(GameBackEnd game){
        super();
        this.winnerLabel= new JLabel();
        this.team1Label= new JLabel();
        this.team2Label= new JLabel();
        this.game=game;
        this.button= new JButton("Show team decks");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(new Rectangle(200,400));
        getContentPane().setBackground(Color.BLACK);
        setOpacity(1);
        setResizable(false);
        this.winnerLabel.setText("Team winner: "+game.getWinnerTeam().getTeamString());
        this.team1Label.setText("Team 1 points: "+ ScopaTeamDeckRanker.punteggio(game.getTeamDeck(Team.TEAM1)));
        this.team2Label.setText( "Team 2 points: "+ ScopaTeamDeckRanker.punteggio(game.getTeamDeck(Team.TEAM2)));
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PlayerDeckGUI pd = new PlayerDeckGUI(game);
            }
        });
        add(winnerLabel);
        add(team1Label);
        add(team2Label);
        add(button);
        setVisible(true);
    }
}
