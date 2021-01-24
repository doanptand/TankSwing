package com.ddona.tank.gui;

import com.ddona.tank.manager.BossManager;
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
import java.util.BitSet;

public class MapPanel extends JPanel implements KeyListener, Runnable {
    private MapManager mapManager;
    private Bird mBird;
    private TankPlayer mTankPlayer;
    private BulletManager mBulletManager;
    private boolean isRunning = true;
    private final BitSet mBitSet = new BitSet(256);
    private long count;
    private BossManager mBossManager;
    private MainPanel mainPanel;

    public MapPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
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
        mBitSet.clear();
        mapManager = new MapManager(1);
        isRunning = true;
        mBird = new Bird(
                12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
        mTankPlayer = new TankPlayer();
        mTankPlayer.setReference(mapManager, mBird);
        mBulletManager = new BulletManager(mapManager);
        mBossManager = new BossManager();
        new Thread(this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        mBird.draw(g2d);
        mBulletManager.drawAllBullets(g2d);
        mTankPlayer.draw(g2d);
        mBossManager.drawAllBosses(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.isRunning = !isRunning;
        }
        mBitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mBitSet.clear(e.getKeyCode());
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning && mBird.isAlive()) {
                count++;
                if (count % 80 == 0) {
                    mTankPlayer.setCanFire(true);
                }
                if (count == 100000000) {
                    count = 0;
                }
                if (mBossManager.addMoreBosses()) {
                    mainPanel.updateBossCount();
                    repaint();
                }
                mBulletManager.moveAllBullets();
            }
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catchEvent();
            repaint();
        }
    }

    private void catchEvent() {
        if (!isRunning) {
            return;
        }
        if (mBitSet.get(KeyEvent.VK_UP)) {
            mTankPlayer.moveTank(Const.UP_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_DOWN)) {
            mTankPlayer.moveTank(Const.DOWN_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_LEFT)) {
            mTankPlayer.moveTank(Const.LEFT_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_RIGHT)) {
            mTankPlayer.moveTank(Const.RIGH_ORIENT);
        }
        if (mBitSet.get(KeyEvent.VK_SPACE)) {
            if (mTankPlayer.isCanFire()) {
                mBulletManager.addBullet(mTankPlayer.fireBullet());
                mTankPlayer.setCanFire(false);
            }
        }
    }
}
