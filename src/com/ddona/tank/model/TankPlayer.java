package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class TankPlayer extends TankObject {
    private int id;
    private int liveCount;
    private float speed;
    private int orient;

    public TankPlayer() {
        id = Const.TANK_ID;
        liveCount = 3;
        speed = Const.STANDARD_SPEED;
        orient = Const.UP_ORIENT;
        x = 8 * Const.ITEM_SIZE;
        y = 24 * Const.ITEM_SIZE;
        width = Const.TANK_SIZE;
        height = Const.TANK_SIZE;
        icon = ImageMgr.arrPlayerImages.get(orient);
    }

    public void moveTank(int orient) {
        if (this.orient != orient) {
            this.orient = orient;
            this.icon = ImageMgr.arrPlayerImages.get(this.orient);
        }
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
}
