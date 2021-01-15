package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class TankPlayer extends Tank {

    private int liveCount = 3;

    public TankPlayer() {
        super(Const.TANK_ID);
    }

    @Override
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
