package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

import java.awt.*;

public class Bullet extends TankObject{
    private int id;
    private int orient;

    public Bullet(int id,int x, int y, int orient) {
        super(x, y);
        this.id = id;
        this.orient = orient;
        this.width = Const.BULLET_SIZE;
        this.height = Const.BULLET_SIZE;
        this.icon = ImageMgr.arrBulletImages.get(orient);
    }

    public void moveBullet() {
        switch (orient) {
            case Const.UP_ORIENT:
                this.y -= Const.STANDARD_SPEED;
                break;
            case Const.DOWN_ORIENT:
                this.y += Const.STANDARD_SPEED;
                break;
            case Const.LEFT_ORIENT:
                this.x -= Const.STANDARD_SPEED;
                break;
            case Const.RIGH_ORIENT:
                this.x += Const.STANDARD_SPEED;
                break;
        }
    }
}
