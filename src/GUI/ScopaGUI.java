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

    private JMenuBar menuBar;
    private JMenu menu;

    private JMenuItem newGame;
    private JMenuItem simulateGame;


    public ScopaGUI(){
        super();
        InitializeMenu();
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
        //setTimer();
    }

    private void setTimer(){
        this.timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!game.isOver()){
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
                }
        });
        this.timer.start();
    }

    private void aiAction(Player p, int i){
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
                //System.out.println("pressed");
                if(game.isOver()){
                    game.emptyRemainingCardsFromTable();
                    game.calculatePoints();
                    tablePanel.UpdateTable();
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

    private void InitializeMenu(){
        this.menuBar= new JMenuBar();
        this.menu=new JMenu("Options");
        this.newGame = new JMenuItem("New Game");
        this.simulateGame = new JMenuItem("Simulate Game");
        this.simulateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=0;
                cardPanel.disableAllButtons();
                while(i<40){
                    game.randomTurn();
                    i++;
                }
                game.emptyRemainingCardsFromTable();
                cardPanel.Update();
                aiPlayerPanel.updateCurrentPlayer();
                tablePanel.UpdateTable();
                revalidate();
                game.calculatePoints();
                EndGameFrame ef = new EndGameFrame(game);
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        this.menu.add(this.newGame);
        this.menu.add(this.simulateGame);
        this.menuBar.add(this.menu);
        setJMenuBar(this.menuBar);
    }



    private void restartGame(){
        game.resetGame();
        tablePanel.UpdateTable();
        cardPanel.Update();
        cardPanel.resetButtons();
        aiPlayerPanel.updateCurrentPlayer();
        revalidate();
    }



}
