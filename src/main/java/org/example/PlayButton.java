package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayButton extends JButton {


    // cords WH
    static int frameX = 651;
    static int frameY = 398; //1153
    // WH img what i am upload
    static int frameWidth = 605; //1900
    static int frameHeight = 138; //294

    BufferedImage idleUI;
    static BufferedImage heroUI;
    static ImageIcon tempImageUI;

    static String[] models = {"startbutton1.png","startbutton2.png"};

    static String currentModel = models[0];

     PlayButton() {
         setLayout(null);
         setPreferredSize(new Dimension(800, 1000));

         tempImageUI = new ImageIcon("src/main/resources/textures/"+currentModel);
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
