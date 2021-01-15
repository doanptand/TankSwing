package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class TankPlayer extends Tank {

    private int liveCount = 3;
    private boolean canFire;

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
                break;
            case Const.DOWN_ORIENT:
                y += speed;
                break;
            case Const.LEFT_ORIENT:
                x -= speed;
                break;
            case Const.RIGH_ORIENT:
                x += speed;
                break;
        }
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
