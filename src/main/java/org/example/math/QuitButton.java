package org.example.math;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QuitButton extends JButton {

    // cords WH
    static int frameX = 637;
    static int frameY = 554; //1153
    // WH img what i am upload
    static int frameWidth = 603; //1900
    static int frameHeight = 127; //294

    BufferedImage idleUI;
    static BufferedImage heroUI;
    static ImageIcon tempImageUI;

  public static String[] models = {"quitbutton.png","quitbuttonstate.png"};

  public static String currentModelQuit = models[0];


        public QuitButton(){
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));

        tempImageUI = new ImageIcon("src/main/resources/textures/"+currentModelQuit);
        heroUI = toBufferedImage(tempImageUI);
    }

    public static void repaintFunction(String currentModel){
        tempImageUI = new ImageIcon("src/main/resources/textures/"+currentModel);
        heroUI = toBufferedImage(tempImageUI);

    }


    public static BufferedImage toBufferedImage(ImageIcon icon) {
        Image image = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        idleUI = heroUI.getSubimage(frameX, frameY, frameWidth, frameHeight);

        if (idleUI != null) {
            g.drawImage(idleUI, 0, 0, getWidth(), getHeight(), null);
        }
    }


}
