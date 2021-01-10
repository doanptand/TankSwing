package com.ddona.tank.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsPanel extends JPanel implements KeyListener {
    private GameFrame mGameFrame;

    public SettingsPanel(GameFrame frame) {
        this.mGameFrame = frame;
        setLayout(null);
        setBackground(Color.BLUE);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            this.mGameFrame.showGame();
        } else if (e.getKeyChar() == KeyEvent.VK_P) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
