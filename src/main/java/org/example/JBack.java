package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JBack extends JPanel {

    // cords WH
    static int frameX = 0;
    static int frameY = 767; //1153
    // WH img what i am upload
    static int frameWidth = 1898; //1900
    static int frameHeight = 678; //294

    BufferedImage idleUI;
    BufferedImage heroUI;
    ImageIcon tempImageUI;

    BufferedImage background;
    ImageIcon backgroundTemp;
    BufferedImage totalBackground;


    public JBack() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));
        setBackground(Color.WHITE);

        tempImageUI = new ImageIcon("src/main/resources/common/2dtrack.png");
        heroUI = toBufferedImage(tempImageUI);

        backgroundTemp = new ImageIcon("src/main/resources/common/grass.png");
        background = toBufferedImage(backgroundTemp);

        DriveForward driveForward = new DriveForward();
        driveForward.start();

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


        totalBackground = background.getSubimage(600,frameY,1001,940);

        g.drawImage(totalBackground,0,0,null);

        idleUI = heroUI.getSubimage(frameX, frameY, frameWidth, frameHeight);

        if (idleUI != null) {
//            int drawWidth = idleUI.getWidth();
//            int drawHeight = idleUI.getHeight();

//            double scale = getWidth() / (double) drawWidth;
//            drawWidth =  (int) (getWidth() * 1.4);
//            drawHeight = (int) (idleUI.getHeight()*scale);

            g.drawImage(idleUI, 0, 0, getWidth(), getHeight(), null);
        }
    }




    public synchronized void driveAnimationUp(){
        if (frameY > 197){frameY-= 30;}
        else {frameY = 767;}
    }
    public class DriveForward extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {

                    driveAnimationUp();
                    sleep(20);
                    repaint();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
