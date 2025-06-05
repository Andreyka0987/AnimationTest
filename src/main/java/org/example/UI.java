package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

public class UI extends JFrame implements MouseListener, KeyListener {
    Hero hero = new Hero();
    static boolean isWalk = false;
    static AnotherHero anotherHero = new AnotherHero();
    boolean jumpCheck = true;
    static boolean isJump = false;

     UI() {
         setLayout(null);
         setPreferredSize(new Dimension(600, 600));
         setBackground(Color.WHITE);

         hero.setBounds(100,400,70, 85);
         anotherHero.setBounds(400,425,50,60);

         addMouseListener(this);
         addKeyListener(this);
         setFocusable(true);
         requestFocusInWindow();


         add(hero);
         add(anotherHero);

         anotherHero.idleAnimation();

         pack();
         setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
//        int button = e.getButton();
//        if (button == 1){
//            hero.defenceAnimation();
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//         hero.idleAnimation();
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


        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (jumpCheck) {
                    hero.jumpFunction();
                    jumpCheck = false;
                    System.out.println("123");
            }
        }
        if (button == 'd'){
            hero.walkAnimationRight();
        }
        if (button == 'a'){
            hero.walkAnimationLeft();
        }
        if (e.isShiftDown()){
            hero.runAnimation();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        char button = e.getKeyChar();
         if (button == 'd'){
             hero.idleAnimation();
             hero.stopWalkAnimation();
         }
        if (button == 'a'){
            hero.idleAnimation();
            hero.stopWalkAnimation();
        }
         if (isWalk && e.getKeyCode() == KeyEvent.VK_SHIFT){
             hero.stopRunningAnimation();
         }
         if (!jumpCheck && e.getKeyCode() == KeyEvent.VK_SPACE){}
            jumpCheck = true;
    }
}
