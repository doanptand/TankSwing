package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class Bullet extends TankObject {
    private final int id;
    private final int orient;

    public Bullet(int id, int x, int y, int orient) {
        super(x, y);
        this.id = id;
        this.orient = orient;
        this.width = Const.BULLET_SIZE;
        this.height = Const.BULLET_SIZE;
        this.icon = ImageMgr.arrBulletImages.get(orient);
    }

    public boolean moveBullet() {
        switch (this.orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= Const.STANDARD_SPEED;
                    return true;
                } else{
                    return false;
                }
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.BULLET_SIZE) {
                    y += Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }
            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }
            case Const.RIGH_ORIENT:
                if (x < Const.MAP_SIZE - Const.BULLET_SIZE) {
                    x += Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                ", orient=" + orient +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public int getId() {
        return id;
    }
}
