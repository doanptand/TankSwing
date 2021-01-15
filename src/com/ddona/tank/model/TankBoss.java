package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

import java.util.Random;

public class TankBoss extends Tank {
    private final int bossLevel;
    private int hp;

    public TankBoss(int x, int y, int level) {
        super(Const.BOSS_ID);
        this.x = x;
        this.y = y;
        this.bossLevel = level;
        speed = Const.STANDARD_SPEED / 4;
        this.hp = level;
        updateIcon();
    }

    public void updateIcon() {
        if (this.bossLevel == 3) {
            if (hp == 1) {
                this.icon = ImageMgr.arrBoss31Images.get(orient);
            } else if (hp == 2) {
                this.icon = ImageMgr.arrBoss32Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss33Images.get(orient);
            }
        } else if (this.bossLevel == 2) {
            if (hp == 2) {
                this.icon = ImageMgr.arrBoss22Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss21Images.get(orient);
            }
        } else {
            this.icon = ImageMgr.arrBoss1Images.get(orient);
        }
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
}
