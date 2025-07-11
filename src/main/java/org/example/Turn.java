package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Turn extends JPanel {

    // cords WH
    static int frameX = 0;
    static int frameY = 767; //1153
    // WH img what i am upload
    static int frameWidth = 1898; //1900
    static int frameHeight = 678; //294

    BufferedImage idleUI;
    BufferedImage heroUI;
    ImageIcon tempImageUI;

    static boolean isLeft = false;


    public Turn() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));
        setBackground(Color.WHITE);

        tempImageUI = new ImageIcon("src/main/resources/common/RightTrack.png");
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
            BufferedImage imageToDraw = isLeft ? flipImageHorizontally(heroUI) : heroUI;
            g.drawImage(imageToDraw, 0, 0, getWidth(), getHeight(), null);
        }
    }


    public BufferedImage flipImageHorizontally(BufferedImage image) {
        BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();

        g.transform(AffineTransform.getScaleInstance(-1, 1));
        g.drawImage(image, -image.getWidth(), 0, null);

        g.dispose();
        return flipped;
    }




}



