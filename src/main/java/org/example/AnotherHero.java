package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnotherHero extends JLabel {
    BufferedImage idle;
    BufferedImage hero;
    ImageIcon tempImage;

    // cords WH
   static int frameX = 158;
   static int frameY = 35;
    // WH img what i am upload
   static int frameWidth = 46;
   static int frameHeight = 85;

    AnotherHero(){
        tempImage = new ImageIcon("src/main/resources/textures/original-d1e95a0c9c33c0f65821c2e7aa6c22d7.jpg");
        hero = toBufferedImage(tempImage);

        setPreferredSize(new Dimension(140, 170));

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        idle = hero.getSubimage(frameX, frameY, frameWidth, frameHeight);
        if (idle != null) {
            g.drawImage(idle, 0,0 , getWidth(), getHeight(), null);
        }
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

}
