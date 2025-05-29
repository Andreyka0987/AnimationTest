package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI extends JFrame implements MouseListener, KeyListener {
    Hero hero = new Hero();
    static boolean isWalk = false;
     UI() {
         setLayout(null);
         setPreferredSize(new Dimension(400, 400));

         hero.setBounds(0,0,140, 170);

         addMouseListener(this);

         addKeyListener(this);
         setFocusable(true);
         requestFocusInWindow();

         add(hero);
         pack();
         setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button == 1){
            hero.defenceAnimation();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         hero.idleAnimation();
    }


    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
        char button = e.getKeyChar();
        if (button == 'w'){
            hero.walkAnimation();
        }
        if (e.isShiftDown()){
            System.out.println("123");
            hero.runAnimation();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        char button = e.getKeyChar();
         if (button == 'w'){
             hero.idleAnimation();
             hero.stopWalkAnimation();
         }
         if (isWalk && e.getKeyCode() == KeyEvent.VK_SHIFT){
             hero.stopRunningAnimation();
         }
    }
}
