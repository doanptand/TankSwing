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
    private BossManager mBossManager;
    private final MainPanel mainPanel;
    private boolean isRunning = true;
    private long count = 0;
    private final BitSet bitSet = new BitSet(256);

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
        new Thread(this).start();
    }

    private void initComponents() {
        bitSet.clear();
        mapManager = new MapManager(1);
        mBird = new Bird(
                12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
        mTankPlayer = new TankPlayer();
        mBossManager = new BossManager();
        mBulletManager = new BulletManager(mTankPlayer, mapManager, mBird, mBossManager);
        mTankPlayer.setReference(mapManager, mBird, mBossManager);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        mBird.draw(g2d);
        mBulletManager.drawAllBullets(g2d);//should draw below tank
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
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear(e.getKeyCode());
    }

    @Override
    public void run() {
        while (mBird.isAlive()) {
            if (isRunning) {
                count++;
                if (count % 80 == 0) {
                    mTankPlayer.setCanFire(true);
                }
                if (count == 100000000) {
                    count = 0;
                }
                if (mBossManager.addBosses()) {
                    mainPanel.updateBossIcon(mBossManager.getBossCount());
                    repaint();
                }
                mBulletManager.moveAllBullets();
                mBossManager.moveAllBosses();
            }
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catchEvent();
            repaint();
        }
        JOptionPane.showMessageDialog(this, "You are loser!");
    }

    private void catchEvent() {
        if (bitSet.get(KeyEvent.VK_UP)) {
            mTankPlayer.moveTank(Const.UP_ORIENT);
        } else if (bitSet.get(KeyEvent.VK_DOWN)) {
            mTankPlayer.moveTank(Const.DOWN_ORIENT);
        } else if (bitSet.get(KeyEvent.VK_LEFT)) {
            mTankPlayer.moveTank(Const.LEFT_ORIENT);
        } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
            mTankPlayer.moveTank(Const.RIGH_ORIENT);
        }
        if (bitSet.get(KeyEvent.VK_SPACE)) {
            if (mTankPlayer.isCanFire()) {
                mBulletManager.addBullets(mTankPlayer.fireBullet());
                mTankPlayer.setCanFire(false);
            }
        }
    }
}
