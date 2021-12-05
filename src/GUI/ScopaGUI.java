package GUI;

import Agents.Player;
import ScopaLogic.GameBackEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScopaGUI extends JFrame {

    private CardPanel cardPanel;
    private TablePanel tablePanel;
    private AIPlayerPanel aiPlayerPanel;
    private GameBackEnd game;
    private Timer timer;





    public ScopaGUI(){
        super();
        this.game=new GameBackEnd();
        this.tablePanel=new TablePanel(this.game);
        this.aiPlayerPanel= new AIPlayerPanel(this.game, getAiPlayerButtonListener());
        this.cardPanel=new CardPanel(this.game,this.tablePanel,this.aiPlayerPanel);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0,0,1200,800);
        add(this.cardPanel,BorderLayout.NORTH);
        add(this.tablePanel,BorderLayout.CENTER);
        add(this.aiPlayerPanel,BorderLayout.SOUTH);
        setVisible(true);
        setTimer();
    }


    private void setTimer(){
        this.timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=3; i>0; i--){
                    Player p = game.getPlayer(i);

                    if(game.getCurrentPlayer()==i){
                        aiAction(p,i);
                        /*try{
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException ex){
                            ex.printStackTrace();
                        }*/
                    }
                }
                }
        });
        this.timer.start();
    }

    private void aiAction(Player p, int i){
        System.out.println("Player "+i+" is playing");
        int cardIndex = p.move();
        GuiCard selectedCard = game.selectCard(p,cardIndex);
        game.playerMove(p,cardIndex);
        tablePanel.UpdateTable();
        aiPlayerPanel.updateCurrentPlayer();
        aiPlayerPanel.setAiSelectedCard(selectedCard);
    }

    private ActionListener getAiPlayerButtonListener(){
        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed");
                if(game.isOver()){
                    game.calculatePoints();
                    return;
                }
                if(game.getCurrentPlayer()!=0){
                    Player p = game.getPlayer(game.getCurrentPlayer());
                    int cardIndex = p.move();
                    aiAction(p,cardIndex);}

            }
        };
        return l;
    }



}
