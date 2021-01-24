package com.ddona.tank.manager;

import com.ddona.tank.model.Boss;
import com.ddona.tank.model.Bullet;
import com.ddona.tank.model.MapItem;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private List<Bullet> mBullets;
    private MapManager mapManager;
    private BossManager bossManager;

    public BulletManager(MapManager map, BossManager bossManager) {
        this.mapManager = map;
        this.bossManager = bossManager;
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
            if (checkIntersectWithMap(mBullets.get(i))) {
                continue;
            }
            if (checkIntersectWithTank(mBullets.get(i))) {
                continue;
            }
        }
    }

    private boolean checkIntersectWithTank(Bullet bullet) {
        if (bullet.getId() == Const.TANK_ID) {
            for (int i = 0; i < bossManager.getBosses().size(); i++) {
                Boss boss = bossManager.getBosses().get(i);
                if (bullet.getRect().intersects(boss.getRect())) {
                    mBullets.remove(bullet);
                    if (boss.beHit()) {
                        bossManager.getBosses().remove(boss);
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean checkIntersectWithMap(Bullet bullet) {
        boolean isIntersect = false;
        for (int i = 0; i < mapManager.getArrMaps().size(); i++) {
            MapItem item = mapManager.getArrMaps().get(i);
            if (item.getRect().intersects(bullet.getRect())
                    && !item.isAllowBulletPass()) {
                mapManager.getArrMaps().remove(item);
                mBullets.remove(bullet);
                isIntersect = true;
            }
        }
        return isIntersect;
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
