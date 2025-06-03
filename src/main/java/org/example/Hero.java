package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Hero extends JLabel {
    MyThread walkThread = null;

    int[] Inner =
            {1,2,3,
            4,5,6};

    BufferedImage idle;
    BufferedImage hero;
    ImageIcon tempImage;
     int xStep = 32;

     // cords WH
    static int frameX = 6;
    static int frameY = 6;
     // WH img what i am upload
    static int frameWidth = 17;
    static int frameHeight = 22;

    int countInner = 0;

    int speed = 10;

    int posX = 100;
    int posY = 400;

    boolean isBack = false;

    Hero() {
        tempImage = new ImageIcon("src/main/resources/textures/knight.png");
        hero = toBufferedImage(tempImage);

        setPreferredSize(new Dimension(140, 170));

//        MyThread myThread = new MyThread();
//        myThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        idle = hero.getSubimage(frameX, frameY, frameWidth, frameHeight);
        if (idle != null) {
            BufferedImage imageToDraw = isBack ? flipImageHorizontally(idle) : idle;
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

    boolean isStanding = true;
    public void idleAnimation(){



        frameX = 5;
        frameY = 6;
        frameWidth = 17;
        frameHeight = 22;
        isStanding = true;


        repaint();
    }

    public void walkAnimationRight(){
        if (walkThread != null)return;
        walkThread = new MyThread();

            frameX = 5;
            frameY = 71;
            frameWidth = 18;
            frameHeight = 21;
            UI.isWalk = true;
            isBack = false;
            walkThread.start();

    }


    public void walkAnimationLeft(){
        if (walkThread != null)return;
        walkThread = new MyThread();

        frameX = 5;
        frameY = 71;
        frameWidth = 18;
        frameHeight = 21;
        UI.isWalk = true;
        isBack = true;
        walkThread.start();
        repaint();
    }

    public void stopWalkAnimation(){
        UI.isWalk = false;

        if (walkThread != null){
            walkThread.interrupt();
        }

        walkThread = null;

    }

    public void runAnimation(){
        if (speed != 20){
            speed+=10;
        }
    }
    public void stopRunningAnimation(){
        if (speed != 10){
            speed -= 10;
            InnerThread innerThread = new InnerThread();
            innerThread.start();
        }
    }






   public class MyThread extends Thread{
       @Override
       public void run() {
           super.run();
           while (UI.isWalk) {
               try {
                   sleep(75);

                  if (!isBack) {
                      posX += speed;
                      frameX += xStep;

                      setLocation(posX, posY);
                      if (frameX > 256) {
                          frameX = 5;
                      }
                      repaint();
                  }
                  else {
                      posX -= speed;
                      frameX += xStep;

                      setLocation(posX, posY);
                      if (frameX > 256) {
                          frameX = 5;
                      }
                      repaint();
                  }

//                   if (Touch.isTouching(getX() + getWidth() / 2, getY() + getHeight() / 2,
//                           UI.anotherHero.getX() + UI.anotherHero.getWidth() / 2, UI.anotherHero.getY() + UI.anotherHero.getHeight() / 2,
//                           50)) {
//                       System.out.println("Lovely Jably");
//                   }


               } catch (InterruptedException e) {

                   return;
               }
           }
       }
    }



    public class InnerThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                if (!isBack) {
                    sleep(25);
                    for (int i = 0; i < Inner.length; i++) {
                        posX += Inner[i];
                        setLocation(posX, posY);
                        sleep(25);
                    }
                }
                else {
                    sleep(25);
                    for (int i = 0; i < Inner.length; i++) {
                        posX -= Inner[i];
                        setLocation(posX, posY);
                        sleep(25);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


