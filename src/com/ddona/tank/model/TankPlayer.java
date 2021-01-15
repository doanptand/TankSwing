package com.ddona.tank.model;

import com.ddona.tank.manager.BossManager;
import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.util.Const;

import java.awt.*;

public class TankPlayer extends Tank {

    private int liveCount = 3;
    private boolean canFire;
    private MapManager mapManager;
    private BossManager bossManager;
    private Bird bird;

    public TankPlayer() {
        super(Const.TANK_ID);
        speed = Const.STANDARD_SPEED / 4;
    }

    @Override
    public void moveTank(int orient) {
        if (this.orient != orient) {
            setOrient(orient);
        }
        switch (this.orient) {
            case Const.UP_ORIENT:
                y -= speed;
                if (checkIntersects()) {
                    y += speed;
                }
                break;
            case Const.DOWN_ORIENT:
                y += speed;
                if (checkIntersects()) {
                    y -= speed;
                }
                break;
            case Const.LEFT_ORIENT:
                x -= speed;
                if (checkIntersects()) {
                    x += speed;
                }
                break;
            case Const.RIGH_ORIENT:
                x += speed;
                if (checkIntersects()) {
                    x -= speed;
                }
                break;
        }
    }

    public boolean checkIntersects() {
        int sizeMap = mapManager.getArrMaps().size();
        Rectangle rectItem;
        for (int i = 0; i < sizeMap; i++) {
            rectItem = mapManager.getArrMaps().get(i).getRect();
            if (this.getRect().intersects(rectItem) && !(mapManager.getArrMaps().get(i).allowTankPass())) {
                return true;
            }
        }
        int sizeBoss = bossManager.getBosses().size();
        Rectangle rectBoss;
        for (int i = 0; i < sizeBoss; i++) {
            rectBoss = bossManager.getBosses().get(i).getRect();
            if (this.getRect().intersects(rectBoss)) {
                return true;
            }
        }
        if (bird.getRect().intersects(this.getRect())) {
            return true;
        }
        return false;
    }

    public void setReference(MapManager mapManager, Bird bird, BossManager bossManager) {
        this.bossManager = bossManager;
        this.bird = bird;
        this.mapManager = mapManager;
    }

    @Override
    public void updateIcon() {
        this.icon = ImageMgr.arrPlayerImages.get(orient);
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }
}
