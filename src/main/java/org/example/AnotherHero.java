package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class AnotherHero extends JLabel {
    BufferedImage idle;
    BufferedImage hero;
    ImageIcon tempImage;

    // cords WH
   static int frameX = 4;
   static int frameY = 34;
    // WH img what i am upload
   static int frameWidth = 16;
   static int frameHeight = 14;

   boolean isHeroInSight = false;
   boolean totalPOV;
    AnotherHero(){
        tempImage = new ImageIcon("src/main/resources/textures/slime_green.png");
        hero = toBufferedImage(tempImage);

        setPreferredSize(new Dimension(140, 170));

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        totalPOV = checkViewPoint();
        idle = hero.getSubimage(frameX, frameY, frameWidth, frameHeight);
        if (idle != null) {
            BufferedImage imageToDraw = totalPOV ? flipImageHorizontally(idle) : idle;
            g.drawImage(imageToDraw, 0,0 , getWidth(), getHeight(), null);
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

    public BufferedImage flipImageHorizontally(BufferedImage image) {
        BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();

        g.transform(AffineTransform.getScaleInstance(-1, 1));
        g.drawImage(image, -image.getWidth(), 0, null);

        g.dispose();
        return flipped;
    }


    public void idleAnimation(){
        frameX = 4;
        frameY = 33;
        frameWidth = 16;
        frameHeight = 15;
        IdleThread idleThread = new IdleThread();
        idleThread.start();
        repaint();
    }

    public boolean checkViewPoint(){
        if (Hero.posX<getX()){
            isHeroInSight = true;
        }
        else {
            isHeroInSight = false;
        }
        return isHeroInSight;
    }


    public class IdleThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true) {
                try {


                        frameX += 24;
                        repaint();

                        if (frameX > 96) {
                            frameX = 4;
                        }


                        repaint();
                        sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
