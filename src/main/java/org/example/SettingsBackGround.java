package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsBackGround extends JPanel {
    BufferedImage main;
    ImageIcon tempMain;


     SettingsBackGround() {
         tempMain = new ImageIcon("src/main/resources/common/assets_task_01jxa9jf9eebx9ehmb719yx43s_1749471113_img_0.jpg");
         main = toBufferedImage(tempMain);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(main,0,0,getWidth(),getHeight(),null);
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
