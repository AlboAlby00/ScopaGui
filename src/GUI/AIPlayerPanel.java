package GUI;

import ScopaLogic.GameBackEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AIPlayerPanel extends JPanel {

    private final int LENGHT=1200;
    private final int HEIGHT=200;

    private GameBackEnd game;

    private JLabel currentPlayerLabel;
    private JLabel aiSelectedCard;
    private JButton aiActionButton;

    AIPlayerPanel(GameBackEnd game, ActionListener l){
        super();
        this.game=game;
        setPreferredSize(new Dimension(LENGHT,HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        InitializeAiSelectedCard();
        InitializeCurrentPlayerLabel();
        InitializeAiActionButton(l);

    }

    public void updateCurrentPlayer(){
        this.currentPlayerLabel.setText("Current Player: \n"+game.getCurrentPlayer());
    }

    private void InitializeCurrentPlayerLabel(){
        this.currentPlayerLabel=new JLabel("Current Player: "+game.getCurrentPlayer());
        this.currentPlayerLabel.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        this.currentPlayerLabel.setPreferredSize(new Dimension(400,200));
        this.currentPlayerLabel.setForeground(Color.WHITE);
        add(this.currentPlayerLabel);
    }

    private void InitializeAiSelectedCard(){
        this.aiSelectedCard = new JLabel();
        this.aiSelectedCard.setIcon( GuiCard.createBlackCard().getImage());
        add(this.aiSelectedCard);
    }

    public void setAiSelectedCard(GuiCard card){
        //remove(this.aiSelectedCard);
        this.aiSelectedCard.setIcon(card.getImage());
    }

    private void InitializeAiActionButton(ActionListener l){
        this.aiActionButton = new JButton();
        this.aiActionButton.setPreferredSize(new Dimension(200,50));
        this.aiActionButton.addActionListener(l);
        add(this.aiActionButton);
    }


}
