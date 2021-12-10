package GUI;

import Agents.Team;
import ScopaLogic.GameBackEnd;
import ScopaLogic.PlayerDeck;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Stack;

public class PlayerDeckGUI extends JFrame {

    private GameBackEnd game;
    private JLabel team1Label;
    private JLabel team2Label;
    private JPanel deck1Panel;
    private JPanel deck2Panel;

    PlayerDeckGUI(GameBackEnd game){
        super();
        this.game=game;
        setBounds(new Rectangle(1200,800));
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setVisible(true);

        add(Box.createRigidArea(new Dimension(1200,50)));

        this.team1Label= new JLabel("Team 1 Deck:");
        this.team1Label.setFont(new Font("SansSerif",Font.PLAIN,20));
        this.team1Label.setForeground(Color.WHITE);
        add(this.team1Label,CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(1200,50)));

        deck1Panel= new JPanel();
        deck1Panel.setBackground(Color.BLACK);
        deck1Panel.setSize(new Dimension(1200,400));
        deck1Panel.setLayout(new FlowLayout());
        Stack<GuiCard> deck = this.game.getTeamDeck(Team.TEAM1).getDeck();
        Collections.sort(deck);
        for(GuiCard c : deck){
            JLabel l = new JLabel();
            ImageIcon i = c.getImage();
            l.setIcon(new ImageIcon(i.getImage().getScaledInstance(225/4,315/4,Image.SCALE_DEFAULT)));
            deck1Panel.add(l);
        }
        add(deck1Panel);

        add(Box.createRigidArea(new Dimension(1200,100)));

        this.team2Label= new JLabel("Team 2 Deck:");
        this.team2Label.setFont(new Font("SansSerif",Font.PLAIN,20));
        this.team2Label.setForeground(Color.WHITE);
        add(this.team2Label,CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(1200,50)));

        deck = this.game.getTeamDeck(Team.TEAM2).getDeck();
        Collections.sort(deck);
        deck2Panel= new JPanel();
        deck2Panel.setBackground(Color.BLACK);
        deck2Panel.setSize(new Dimension(1200,400));
        deck2Panel.setLayout(new FlowLayout());
        for(GuiCard c : deck){
            JLabel l = new JLabel();
            ImageIcon i = c.getImage();
            l.setIcon(new ImageIcon(i.getImage().getScaledInstance(225/4,315/4,Image.SCALE_DEFAULT)));
            deck2Panel.add(l);
        }
        add(deck2Panel);



    }

}
