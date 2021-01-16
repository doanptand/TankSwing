package com.ddona.tank.manager;

import com.ddona.tank.model.*;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BulletManager {
    private final List<Bullet> mBullets;
    private final TankPlayer mPlayer;
    private final MapManager mMapManager;
    private final Bird mBird;
    private final GiftManager giftManager;
    private final BossManager mBossManager;
    private final Random rd;

    public BulletManager(TankPlayer mPlayer, MapManager mMapManager, Bird mBird, BossManager bossManager, GiftManager giftManager) {
        this.mPlayer = mPlayer;
        this.mMapManager = mMapManager;
        this.mBird = mBird;
        this.mBossManager = bossManager;
        this.giftManager = giftManager;
        this.mBullets = new ArrayList<>();
        this.rd = new Random();
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
            for (int i = 0; i < mBossManager.getBosses().size(); i++) {
                if (bullet.getRect().intersects(mBossManager.getBosses().get(i).getRect())) {
                    if (mBossManager.getBosses().get(i).beHit()) {
                        mBossManager.getBosses().remove(i);
                        //TODO play bum animation
                        SoundMgr.play(SoundMgr.killBoss);
                    }
                    if (rd.nextInt(100) > 90) {
                        giftManager.addNewGift(rd.nextInt(650), rd.nextInt(650),  rd.nextInt(2));
                    }
                    return true;
                }
            }
            return false;
        } else {
            if(bullet.getRect().intersects(mPlayer.getRect())) {
                return mPlayer.beHit();
            }
            return false;
        }
    }

    private boolean checkWithBullets(Bullet bullet) {
        //We only check boss's bullet due to the tank player's bullets never intersect with them
        //only check boss bullet intersect with tank player bullet
        if (bullet.getId() == Const.BOSS_ID) {
            for (int i = 0; i < mBullets.size(); i++) {
                if (mBullets.get(i).getId() == Const.TANK_ID && bullet.getRect().intersects(mBullets.get(i).getRect())) {
                    mBullets.remove(i);
                    mBullets.remove(bullet);
                    return true;
                }
            }
        }
        return false;
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
            mBird.setAlive(false);
            return true;
        }
        return false;
    }
}
