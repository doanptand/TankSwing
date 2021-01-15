package com.ddona.tank.manager;

import com.ddona.tank.model.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private final List<Bullet> mBullets;

    public BulletManager() {
        mBullets = new ArrayList<>();
    }

    public void addBullets(Bullet bullet) {
        mBullets.add(bullet);
    }

    public void drawAllBullets(Graphics2D g2d) {
        for (Bullet mBullet : mBullets) {
            mBullet.draw(g2d);
        }
    }

    public void moveAllBullets() {
        for (Bullet mBullet : mBullets) {
            mBullet.moveBullet();
        }
    }
}
