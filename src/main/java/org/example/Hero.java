package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends JLabel {
    MyThread walkThread = null;

    BufferedImage idle;
    BufferedImage hero;
    ImageIcon tempImage;
     int xStep = 110;

     // cords WH
    static int frameX = 158;
    static int frameY = 35;
     // WH img what i am upload
    static int frameWidth = 46;
    static int frameHeight = 85;

    int posX = 100;
    int posY = 400;

    int speed = 10;

    Hero() {
        tempImage = new ImageIcon("src/main/resources/textures/original-d1e95a0c9c33c0f65821c2e7aa6c22d7.jpg");
        hero = toBufferedImage(tempImage);

        setPreferredSize(new Dimension(100, 120));

//        MyThread myThread = new MyThread();
//        myThread.start();
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


    public void defenceAnimation() {
        frameWidth = 55;
        frameHeight = 78;
        frameX = 160;
        frameY = 539;

        frameX += xStep;
        if (frameX>376){
            frameX= 269;
        }
        repaint();
    }

    public void idleAnimation(){
        frameX = 158;
        frameY = 35;
        frameWidth = 46;
        frameHeight = 85;
        frameX+=xStep;
        if (frameX>1024){
            frameX = 158;
        }
        repaint();
    }

    public void walkAnimation(){




        if (walkThread != null)return;

        walkThread = new MyThread();

            frameX = 164;
            frameY = 290;
            frameWidth = 41;
            frameHeight = 85;
            UI.isWalk = true;
            walkThread.start();


    }
    public void stopWalkAnimation(){
        UI.isWalk = false;

        if (walkThread != null){
            walkThread.interrupt();
        }

        walkThread = null;
    }
    public void runAnimation(){
        if (walkThread.isAlive() && frameY != 434){
            frameX = 173;
            frameY = 434;
            frameWidth = 41;
            frameHeight = 85;
        }
    }
    public void stopRunningAnimation(){
        frameX = 164;
        frameY = 290;
        frameWidth = 41;
        frameHeight = 85;
        repaint();
    }






   public class MyThread extends Thread{
       @Override
       public void run() {
           super.run();
           while (UI.isWalk) {
               try {
                   sleep(100);

                    posX+=10;
                   frameX+=xStep;

                   setLocation(posX,posY);

                   if (frameX>1024){
                       frameX=164;
                   }
                   repaint();

                   if (Touch.isTouching(getX()+getWidth()/2,getY()+getHeight()/2,
                           UI.anotherHero.getX()+UI.anotherHero.getWidth()/2,UI.anotherHero.getY()+UI.anotherHero.getHeight()/2,
                           50)){
                       System.out.println("Lovely Jably");
                   }


               } catch (InterruptedException e) {

                   return;
               }
           }
       }
    }


}
