package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Settings extends JFrame implements ActionListener {
    JRadioButton musicJRadio = new JRadioButton();
    Clip clip;
    SettingsBackGround backGround = new SettingsBackGround();

    Settings()  {
        setLayout(null);
        setPreferredSize(new Dimension(250, 200));
        backGround.setBounds(0,0,250,170);
        backGround.setLayout(null);
        add(backGround);
        setResizable(false);




        musicJRadio.setBounds(10,5,100,50);

        musicJRadio.setOpaque(false);
        musicJRadio.setContentAreaFilled(false);
        musicJRadio.setBorderPainted(false);
        musicJRadio.setFocusPainted(false);

        musicJRadio.addActionListener(this);
        musicJRadio.setText("Music");
        musicJRadio.setForeground(Color.WHITE);

        backGround.add(musicJRadio);

        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (musicJRadio.isSelected()){
            playSound();
        }
        else {
            stopSound();
        }
    }



    public void playSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sound/Carte-Blanq-_-Maxx-Power-33-Max-Verstappen.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    public void stopSound(){
        if (clip.isActive()){
            clip.stop();
            clip.close();
        }
    }



}
