package GUI;

import ScopaLogic.Suit;
import ScopaLogic.Value;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GuiCard extends ScopaLogic.Card {

    private static int width = 225;
    private static int height = 315;

    private ImageIcon image;
    private boolean isBlack;
    private static BufferedImage bigImage;
    private static BufferedImage blackImage;

    public GuiCard(Value value, Suit suit, boolean hasLoadedImages){
        super(value,suit);
        this.isBlack=false;
        if(bigImage==null) bigImage=LoadImage("Images/cards.png");
        if(blackImage==null) blackImage=LoadImage("Images/black.png");
        if(hasLoadedImages){
            this.image = LoadCardImage(value,suit);}
        else this.image=null;
    }

    private void setImage(ImageIcon im){
        this.image=im;
    }
    public static BufferedImage LoadImage(String path){
        System.out.println("Loading "+path);
        BufferedImage loadedImage=null;
        try{
            loadedImage = ImageIO.read(new File(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return loadedImage;
    }
    private ImageIcon LoadCardImage(Value value, Suit suit){

        BufferedImage loadedImage = bigImage.getSubimage(width*(value.getVal()-1),height*suit.getN(),width,height);
        Image scaledImage =  loadedImage.getScaledInstance(width/2,height/2, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);

    }
    public ImageIcon getImage() {
        return image;
    }
    private void setCardBlack(){
        image=new ImageIcon(blackImage.getSubimage(0,0,width/2,height/2));

    }
    static public GuiCard createBlackCard(){
        GuiCard card = new GuiCard(Value.ACE,Suit.CLUB,false);
        card.setCardBlack();
        card.isBlack=true;
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuiCard guiCard = (GuiCard) o;
        return isBlack == guiCard.isBlack;
    }


}
