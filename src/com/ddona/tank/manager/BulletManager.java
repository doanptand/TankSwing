package com.ddona.tank.manager;

import com.ddona.tank.model.Bullet;
import com.ddona.tank.model.MapItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private List<Bullet> mBullets;
    private MapManager mapManager;

    public BulletManager(MapManager map) {
        this.mapManager = map;
        mBullets = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {
        mBullets.add(bullet);
    }

    public void moveAllBullets() {
        for (int i = 0; i < mBullets.size(); i++) {
            if (!mBullets.get(i).moveBullet()) {
                mBullets.remove(i);
                continue;
            }
            checkIntersectWithMap(mBullets.get(i));
        }
    }

    private void checkIntersectWithMap(Bullet bullet) {
        for (int i = 0; i < mapManager.getArrMaps().size(); i++) {
            MapItem item = mapManager.getArrMaps().get(i);
            if (item.getRect().intersects(bullet.getRect())
                    && !item.isAllowBulletPass()) {
                mapManager.getArrMaps().remove(item);
                mBullets.remove(bullet);
            }
        }
    }

    public void drawAllBullets(Graphics2D g2d) {
        for (int i = 0; i < mBullets.size(); i++) {
            mBullets.get(i).draw(g2d);
        }
    }

    public List<Bullet> getBullets() {
        return mBullets;
    }
}
