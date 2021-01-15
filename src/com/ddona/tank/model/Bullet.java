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

    public void moveBullet() {
        switch (this.orient) {
            case Const.UP_ORIENT:
                y -= Const.STANDARD_SPEED;
                break;
            case Const.DOWN_ORIENT:
                y += Const.STANDARD_SPEED;
                break;
            case Const.LEFT_ORIENT:
                x -= Const.STANDARD_SPEED;
                break;
            case Const.RIGH_ORIENT:
                x += Const.STANDARD_SPEED;
                break;
        }
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
}
