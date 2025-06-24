package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WinnerPanel extends JPanel {

    BufferedImage heroUI;
    ImageIcon tempImageUI;

    WinnerPanel(){
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));
        setBackground(Color.WHITE);

        tempImageUI = new ImageIcon("src/main/resources/common/assets_task_01jy6rv6eff7wv84g4c6z32wx7_1750426690_img_0.jpg");
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
        g.drawImage(heroUI, 0, 0, getWidth(), getHeight(), null);;
    }

}
