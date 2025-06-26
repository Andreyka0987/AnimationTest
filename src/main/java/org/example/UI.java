package org.example;

import org.example.math.QuitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.border.Border;

public class UI extends JFrame implements KeyListener, ActionListener, MouseListener {
    Hero hero = new Hero();
    static boolean isWalk = false;
    static AnotherHero anotherHero = new AnotherHero();
    boolean jumpCheck = true;
    static boolean isJump = false;
    PlayButton jButton = new PlayButton();
    JBack jBack = new JBack();
    Turn turn = new Turn();
    MainMenu mainMenu = new MainMenu();
    static JLabel points = new JLabel();
    WinnerPanel winnerPanel = new WinnerPanel();
    QuitButton quitButton = new QuitButton();

    int carWidth = 180;
    int carHeight = 260;

    int turnX = 81;
   static int turnY = -1000; //-1000
    int turnWidth = 987;
    int turnHeight = 950;

    boolean isReacted = false;
   static boolean isTurnStarted = false;

    int enemyCarWidth = 140;
    int enemyCarHeight = 235;

    Random random = new Random();

    static TurnThread turnThread;

    static int timerDelay = 10000;

    static int position = 5;

    static boolean isWinner = false;



     UI() {
         setLayout(null);
         setPreferredSize(new Dimension(1000, 940));
         setBackground(Color.WHITE);
         setResizable(false);

         turn.setOpaque(false);


         points.setBounds(930,20,50,50);
         points.setFont(new Font("Monospaced",Font.BOLD,25));
         points.setForeground(Color.white);
         points.setText("P"+position);

         hero.setBounds(Hero.posX,Hero.posY,carWidth, carHeight);
         anotherHero.setBounds(AnotherHero.posX,AnotherHero.posY,enemyCarWidth,enemyCarHeight);
         turn.setBounds(turnX,turnY,turnWidth,turnHeight);


         quitButton.setBounds(360,350,290,75);
         quitButton.setText("quit");
         quitButton.setOpaque(false);
         quitButton.setContentAreaFilled(false);
         quitButton.setBorderPainted(false);
         quitButton.setFocusPainted(false);
         quitButton.addActionListener(this);
         quitButton.addMouseListener(this);

         jButton.setBounds(360,250,290,75);
         jButton.setText("start");
         jButton.setOpaque(false);
         jButton.setContentAreaFilled(false);
         jButton.setBorderPainted(false);
         jButton.setFocusPainted(false);
         jButton.addActionListener(this);
         jButton.addMouseListener(this);


         anotherHero.pause();
         hero.hide();
         anotherHero.hide();




         addKeyListener(this);
         setFocusable(true);
         requestFocusInWindow();


        setContentPane(mainMenu);


          add(quitButton);
     //      add(points);
           add(jButton);
     //    add(hero);
     //    add(anotherHero);
     //    add(turn);




         pack();
         setVisible(true);

    }



    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        char button = e.getKeyChar();

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (jumpCheck) {jumpCheck = false;}}
        if (button == 'd'){
            if (Hero.posX >= 635){
                Hero.posX = 635;
            }
            else {
                hero.walkAnimationLeft();
            }
        }
        if (button == 'a'){
            if (Hero.posX <= 170) {
                Hero.posX = 170;
            }
            else {
                hero.walkAnimationRight();
            }
        }
        if (e.isShiftDown()){
            if (isTurnStarted) {
                isReacted = true;
                turnY = -1000;

            }
        }


    }



    @Override
    public void keyReleased(KeyEvent e) {
        char button = e.getKeyChar();
         if (button == 'd'){
             hero.stopWalkAnimation();
         }
        if (button == 'a'){
            hero.stopWalkAnimation();
        }
         if (isWalk && e.getKeyCode() == KeyEvent.VK_SHIFT){}
         if (!jumpCheck && e.getKeyCode() == KeyEvent.VK_SPACE){}jumpCheck = true;

         if (button == 'w'){}

     }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("start")){
            jButton.hide();
            remove(quitButton);
            setContentPane(jBack);
            add(hero);
            add(anotherHero);
            add(turn);
            add(points);
            hero.show();
            anotherHero.show();
            anotherHero.resume();
            TimerThread timerThread = new TimerThread();
            timerThread.start();
//            new Settings(this);
//            this.requestFocusInWindow();
        }
        if (button.equals("quit")){
            System.exit(0);
        }
    }

    public void startTurns(){
        turnThread = new TurnThread();
         turnThread.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jButton){
            PlayButton.currentModel = PlayButton.models[1];
            PlayButton.repaintFunction(PlayButton.currentModel);
        }
        if (e.getSource() == quitButton){
            QuitButton.currentModelQuit = QuitButton.models[1];
            QuitButton.repaintFunction(QuitButton.currentModelQuit);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jButton){
            PlayButton.currentModel = PlayButton.models[0];
            PlayButton.repaintFunction(PlayButton.currentModel);
        }
        if (e.getSource() == quitButton){
            QuitButton.currentModelQuit = QuitButton.models[0];
            QuitButton.repaintFunction(QuitButton.currentModelQuit);
        }
    }


    public class TurnThread extends Thread {
        @Override
        public void run() {
            super.run();
            int targetY = 0;
                while (turnY < targetY) {
                    turnY += 20;
                    turn.setBounds(turnX, turnY, turnWidth, turnHeight);
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                while (Hero.posY >= 15 && !isReacted) {
                    isTurnStarted = true;
                    Hero.posY -= 30;
                    hero.setLocation(Hero.posX, Hero.posY);
                    repaint();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                isTurnStarted = false;

                if (isReacted) {
                } else {
                    System.exit(0);
                }

                int tempTurn = random.nextInt(2);
                if (tempTurn == 1){
                    Turn.isLeft = true;
                    turnX = -81;
                }
                else{
                    Turn.isLeft = false;
                }
                anotherHero.resume();
                Hero.posY = 450;
                hero.setLocation(Hero.posX, Hero.posY);
                turnY = -1000;
                turn.setBounds(turnX, turnY, turnWidth, turnHeight);
                isReacted = false;
            }

    }


    public class TimerThread extends Thread{
        @Override
        public void run() {
            super.run();
        while (true) {
            try {
                if (timerDelay == 0){
                    timerDelay = 20000;
                    startTurns();
                }

                sleep(1);
                timerDelay -= 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isWinner){
                hero.hide();
                anotherHero.hide();
                anotherHero.pause();
                winnerPanel.setBounds(0,0,1000,940);
                add(winnerPanel);

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);

            }

        }

        }
    }


    }
