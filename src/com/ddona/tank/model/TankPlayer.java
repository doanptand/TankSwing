package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.util.Const;

public class TankPlayer extends TankObject {
    private int id;
    private int liveCount;
    private float speed;
    private int orient;
    private boolean canFire;
    private MapManager mapManager;
    private Bird bird;

    public TankPlayer() {
        id = Const.TANK_ID;
        liveCount = 3;
        speed = Const.STANDARD_SPEED / 2;
        orient = Const.UP_ORIENT;
        x = 8 * Const.ITEM_SIZE;
        y = 24 * Const.ITEM_SIZE;
        width = Const.TANK_SIZE;
        height = Const.TANK_SIZE;
        icon = ImageMgr.arrPlayerImages.get(orient);
        canFire = true;
    }

    public void setReference(MapManager mapManager, Bird bird) {
        this.mapManager = mapManager;
        this.bird = bird;
    }

    public void moveTank(int orient) {
        if (this.orient != orient) {
            this.orient = orient;
            x = (x + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
            y = (y + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
            this.icon = ImageMgr.arrPlayerImages.get(this.orient);
        }
        switch (this.orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= speed;
                    if (intersectWithMapAndBird()) {
                        y += speed;
                    }
                }
                break;
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.TANK_SIZE) {
                    y += speed;
                    if (intersectWithMapAndBird()) {
                        y -= speed;
                    }
                }
                break;
            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= speed;
                    if (intersectWithMapAndBird()) {
                        x += speed;
                    }
                }
                break;
            case Const.RIGHT_ORIENT:
                if (x < Const.MAP_SIZE - Const.TANK_SIZE) {
                    x += speed;
                    if (intersectWithMapAndBird()) {
                        x -= speed;
                    }
                }
                break;
        }
    }

    private boolean intersectWithMapAndBird() {
        for (int i = 0; i < mapManager.getArrMaps().size(); i++) {
            if (getRect().intersects(mapManager.getArrMaps().get(i).getRect())
                    && !mapManager.getArrMaps().get(i).isAllowTankPass()) {
                return true;
            }
        }
        if (getRect().intersects(bird.getRect())) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public Bullet fireBullet() {
        return new Bullet(Const.TANK_ID,
                x + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                y + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                orient);
    }
}
