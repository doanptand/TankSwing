package com.ddona.tank.manager;

import com.ddona.tank.model.Bird;
import com.ddona.tank.model.Bullet;
import com.ddona.tank.model.MapItem;
import com.ddona.tank.model.TankPlayer;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private final List<Bullet> mBullets;
    private TankPlayer mPlayer;
    private MapManager mMapManager;
    private Bird mBird;
    private BossManager mBossManager;

    public BulletManager(TankPlayer mPlayer, MapManager mMapManager, Bird mBird, BossManager bossManager) {
        this.mPlayer = mPlayer;
        this.mMapManager = mMapManager;
        this.mBird = mBird;
        this.mBossManager = bossManager;
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
        try {
            for (int i = mBullets.size() - 1; i >= 0; i--) {
                if (!mBullets.get(i).moveBullet()) {
                    mBullets.remove(i);
                    continue;
                }
                if (isBulletIntersect(mBullets.get(i))) {
                    mBullets.remove(i);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isBulletIntersect(Bullet bullet) {
        if (checkWithBird(bullet)) {
            System.out.println("true bird");
            return true;
        }
        if (checkWithWall(bullet)) {
            System.out.println("true walls");
            return true;
        }
        if (checkWithBullets(bullet)) {
            return true;
        }
        if (checkWithTank(bullet)) {
            return true;
        }
        return false;
    }

    private boolean checkWithTank(Bullet bullet) {
        if (bullet.getId() == Const.TANK_ID) {
            return false;
        } else {
            return false;
        }
    }

    private boolean checkWithBullets(Bullet bullet) {
        if (bullet.getId() == Const.TANK_ID) {
            return false;
        } else {
            return false;
        }
    }

    private boolean checkWithWall(Bullet bullet) {
        boolean isIntersect = false;
        int sizeMap = mMapManager.getArrMaps().size() - 1;
        for (int i = sizeMap; i >= 0; i--) {
            MapItem item = mMapManager.getArrMaps().get(i);
            if (bullet.getRect().intersects(item.getRect()) && !(item.allowBulletPass())) {
                if (item.getType() == 1) {
                    mMapManager.getArrMaps().remove(item);
                }
                mBullets.remove(bullet);
                isIntersect = true;
            }
        }
        return isIntersect;
    }

    private boolean checkWithBird(Bullet bullet) {
        if (bullet.getRect().intersects(mBird.getRect())) {
            //TODO play bird die animation then stop the game
            return true;
        }
        return false;
    }
}
