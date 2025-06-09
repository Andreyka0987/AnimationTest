package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class UI extends JFrame implements MouseListener, KeyListener, ActionListener {
    Hero hero = new Hero();
    static boolean isWalk = false;
    static AnotherHero anotherHero = new AnotherHero();
    boolean jumpCheck = true;
    static boolean isJump = false;
    JButton jButton = new JButton();

    JBack jBack = new JBack();

     UI() {
         setLayout(null);
         setPreferredSize(new Dimension(600, 600));
         setBackground(Color.WHITE);
         setResizable(false);


         hero.setBounds(100,400,70, 85);
         anotherHero.setBounds(400,425,50,60);


         jButton.setBounds(530,15,44,44);
         jButton.setText("⚙");
         jButton.addActionListener(this);

         addMouseListener(this);
         addKeyListener(this);
         setFocusable(true);
         requestFocusInWindow();


        setContentPane(jBack);

         add(jButton);
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
            jBack.walkAnimationRight();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("⚙")){
            new Settings();
        }
    }
}
