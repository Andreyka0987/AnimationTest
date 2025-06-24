package org.example;

import org.example.math.Touch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AnotherHero extends JLabel {
    BufferedImage idle;
    BufferedImage hero;
    ImageIcon tempImage;

    private boolean paused = false;
    private final Object lock = new Object();

    String model = "Williams.png";

    // cords WH
   static int frameX = 592;
   static int frameY = 15;
    // WH img what i am upload
   static int frameWidth = 485;
   static int frameHeight = 903;

   static int posX = 500;
   static int posY = -400;

    int[] enemyXCord = {200,260,500,550};

   String[] models = {
           "McLaren.png","Ferrari.png",
           "Haas.png","KICK.png",
           "Mercedes.png","Raceing_Bulls.png",
           "Williams.png"
   };

   Random randomModel = new Random();
   Random randomX = new Random();

   boolean isHeroInSight = false;

    AnotherHero(){

        model = models[randomModel.nextInt(6)];
        tempImage = new ImageIcon("src/main/resources/enemyteams/"+model);
        hero = toBufferedImage(tempImage);

        setPreferredSize(new Dimension(140, 170));

        moveFunction();

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

    public BufferedImage flipImageHorizontally(BufferedImage image) {
        BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();

        g.transform(AffineTransform.getScaleInstance(-1, 1));
        g.drawImage(image, -image.getWidth(), 0, null);

        g.dispose();
        return flipped;
    }

    public void moveFunction(){
        MoveThread moveThread = new MoveThread();
        moveThread.start();

    }


    public void pause(){
        synchronized (lock) {
            paused = true;
        }
    }
    public void resume(){
        synchronized (lock){
            paused = false;
            lock.notifyAll();
        }
    }


    public class MoveThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lock){
                    while (paused){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                try {
                    if (!UI.isTurnStarted && UI.timerDelay > 5000){
                        show();
                    posY += 10;
                    setLocation(posX, posY);
                    if (Touch.isTouching(posX,posY,Hero.posX,Hero.posY,50)){
                        System.exit(0);
                    }

                    sleep(15);
                    repaint();
                    if (posY == 1100) {
                        int temp = randomModel.nextInt(6);
                        int tempX = randomX.nextInt(3);
                        model = models[temp];
                        tempImage = new ImageIcon("src/main/resources/enemyteams/" + model);
                        hero = toBufferedImage(tempImage);
                        UI.position -= 1;
                        UI.points.setText("P"+UI.position);
                        UI.points.revalidate();
                        UI.points.repaint();
                        repaint();
                        posY = -400;
                        posX = enemyXCord[tempX];

                        if (UI.position == 1){
                            UI.isWinner = true;
                        }

                    }
                    }
                    else {
                        pause();
                        hide();
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }



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
