package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JBack extends JPanel {

    // cords WH
    static int frameX = 6;
    static int frameY = 6;
    // WH img what i am upload
    static int frameWidth = 17;
    static int frameHeight = 22;

    BufferedImage idleUI;
    BufferedImage heroUI;
    ImageIcon tempImageUI;



    public JBack() {
        setLayout(null);
        setPreferredSize(new Dimension(2000, 1000));
        setBackground(Color.WHITE);

        tempImageUI = new ImageIcon("src/main/resources/textures/pixel-art-forest-background-seamless-260nw-2501937379 (1).jpg");
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
        idleUI = heroUI.getSubimage(frameX, 340, getWidth(), getHeight());

        if (idleUI != null) {
            g.drawImage(idleUI, 0, 0, getWidth(), getHeight(), null);
        }
    }


    public void walkAnimationRight(){

        if (frameX < 600 && Hero.posX >= 200){
            frameX+=20;
        }
        else {
            frameX = 0;
        }


        repaint();
    }

}
