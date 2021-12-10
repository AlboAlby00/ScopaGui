package GUI;

import ScopaLogic.GameBackEnd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel{

    private final int WIDTH=1200;
    private final int HEIGHT=600;

    private List<JLabel> tableLabel;
    private GameBackEnd game;

    public TablePanel(GameBackEnd game){
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        this.game=game;
        InitializeTable();
    }

    private void InitializeTable(){
        tableLabel = new ArrayList<>();
        for(GuiCard c : this.game.getTable()){
            JLabel l = new JLabel();
            l.setIcon(c.getImage());
            tableLabel.add(l);
            add(l);
        }
    }

    private void ClearTable(){
        removeAll();
        repaint();
        this.tableLabel.clear();
    }

    public void UpdateTable(){
        //System.out.println("update Table");
        ClearTable();
        InitializeTable();
    }


}
