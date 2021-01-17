package com.ddona.tank.manager;

import com.ddona.tank.model.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager{
    private List<Bullet> mBullets;

    public BulletManager() {
        mBullets = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {
        mBullets.add(bullet);
    }

    public void moveAllBullets() {
        for(int i=0;i<mBullets.size();i++) {
            mBullets.get(i).moveBullet();
        }
    }

    public void drawAllBullets(Graphics2D g2d) {
        for(int i=0;i<mBullets.size();i++) {
            mBullets.get(i).draw(g2d);
        }
    }

    public List<Bullet> getBullets() {
        return mBullets;
    }
}
