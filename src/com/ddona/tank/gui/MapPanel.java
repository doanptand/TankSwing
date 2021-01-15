package com.ddona.tank.gui;

import com.ddona.tank.manager.BulletManager;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.model.Bird;
import com.ddona.tank.model.Bullet;
import com.ddona.tank.model.TankPlayer;
import com.ddona.tank.util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapPanel extends JPanel implements KeyListener, Runnable {
    private MapManager mapManager;
    private Bird mBird;
    private TankPlayer mTankPlayer;
    private BulletManager mBulletManager;
    private boolean isRunning = true;
    private long count = 0;

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
        new Thread(this).start();
    }

    private void initComponents() {
        mapManager = new MapManager(1);
        mBird = new Bird(
                12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
        mTankPlayer = new TankPlayer();
        mBulletManager = new BulletManager();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        mBird.draw(g2d);
        mBulletManager.drawAllBullets(g2d);//should draw below tank
        mTankPlayer.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            case KeyEvent.VK_SPACE:
                if (mTankPlayer.isCanFire()) {
                    mBulletManager.addBullets(mTankPlayer.fireBullet());
                    mTankPlayer.setCanFire(false);
                }
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (mBird.isAlive()) {
            if (isRunning) {
                count++;
                if (count % 80 == 0) {
                    mTankPlayer.setCanFire(true);
                }
                mBulletManager.moveAllBullets();
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}
