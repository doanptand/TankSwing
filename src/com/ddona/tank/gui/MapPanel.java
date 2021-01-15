package com.ddona.tank.gui;

import com.ddona.tank.manager.MapManager;
import com.ddona.tank.model.Bird;
import com.ddona.tank.model.TankPlayer;
import com.ddona.tank.util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapPanel extends JPanel implements KeyListener {
    private MapManager mapManager;
    private Bird mBird;
    private TankPlayer mTankPlayer;

    public MapPanel() {
        setBounds((Const.WIDTH_FRAME - Const.MAP_SIZE) / 2,
                Const.PADDING_TOP,
                Const.MAP_SIZE,
                Const.MAP_SIZE);
        setBackground(Color.BLACK);
        initComponents();
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);
    }

    private void initComponents() {
        mapManager = new MapManager(1);
        mBird = new Bird(
                12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
        mTankPlayer = new TankPlayer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        mBird.draw(g2d);
        mTankPlayer.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key press:" + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                mTankPlayer.moveTank(Const.UP_ORIENT);
                break;
            case KeyEvent.VK_DOWN:
                mTankPlayer.moveTank(Const.DOWN_ORIENT);
                break;
            case KeyEvent.VK_LEFT:
                mTankPlayer.moveTank(Const.LEFT_ORIENT);
                break;
            case KeyEvent.VK_RIGHT:
                mTankPlayer.moveTank(Const.RIGH_ORIENT);
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
