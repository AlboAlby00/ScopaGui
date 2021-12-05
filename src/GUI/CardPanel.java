package GUI;

import ScopaLogic.GameBackEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPanel extends JPanel implements ActionListener {

    private final int WIDTH=1200;
    private final int HEIGHT=200;

    private JLabel[] cardLabel;
    private JButton[] cardButton;
    private GameBackEnd game;
    private TablePanel tablePanel;
    private AIPlayerPanel aiPlayerPanel;

    public CardPanel(GameBackEnd game,TablePanel tablePanel,AIPlayerPanel aiPlayerPanel){
        super();
        this.game=game;
        this.tablePanel=tablePanel;
        this.aiPlayerPanel=aiPlayerPanel;
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLayout(new FlowLayout());
        InitializeButtons();
        InitializeCards(game.getPlayer(0).getHand());

    }

    private void InitializeButtons(){
        this.cardButton = new JButton[10];
        for(int i=0; i<10; i++){
            JButton b =new JButton();
            b.setPreferredSize(new Dimension(225/2,30));
            b.addActionListener(this);
            this.cardButton[i]=b;
            add(b);
        }
    }

    private void InitializeCards(GuiCard[] cardArray){
        this.cardLabel = new JLabel[10];
        for(int i=0; i<10;i++){
            GuiCard c = cardArray[i];
            JLabel l = new JLabel();
            l.setIcon(c.getImage());
            this.cardLabel[i]=l;
            add(l);
        }
    }

    private void UpdateCards(GuiCard[] cardArray){
        for(int i=0; i<10;i++){
            remove(this.cardLabel[i]);
            GuiCard c = cardArray[i];
            JLabel l = new JLabel();
            l.setIcon(c.getImage());
            this.cardLabel[i]=l;
            add(l);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //List<GuiCard> table = this.game.getTable();
        for(int i=0; i<10; i++){
            JButton b = this.cardButton[i];
            if(e.getSource()==b&&game.getCurrentPlayer()==0){
                b.setEnabled(false);
                b.setOpaque(true);
                b.setIcon(GuiCard.createBlackCard().getImage());
                game.playerMove(game.getPlayer(0),i);
                //game.getMainPlayer().printHand();
                UpdateCards(game.getPlayer(0).getHand());
                tablePanel.UpdateTable();
                this.aiPlayerPanel.updateCurrentPlayer();
            }
        }
    }
}
