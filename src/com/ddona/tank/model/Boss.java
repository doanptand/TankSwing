package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class Boss extends TankObject {
    private int id;
    private int level;
    private float speed;
    private int orient;
    private int hp;


    public Boss(int x, int y, int level) {
        super(x, y);
        this.width = Const.TANK_SIZE;
        this.height = Const.TANK_SIZE;
        this.level = level;
        this.hp = level;
        this.id = Const.BOSS_ID;
        this.speed = Const.STANDARD_SPEED / 4;
        this.orient = Const.DOWN_ORIENT;
        updateIcon();
    }

    private void updateIcon() {
        if (level == 1) {
            this.icon = ImageMgr.arrBoss1Images.get(orient);
        } else if (level == 2) {
            if (hp == 2) {
                this.icon = ImageMgr.arrBoss22Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss21Images.get(orient);
            }
        } else {
            if (hp == 3) {
                this.icon = ImageMgr.arrBoss33Images.get(orient);
            } else if (hp == 2) {
                this.icon = ImageMgr.arrBoss32Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss31Images.get(orient);
            }
        }
    }

    public void moveBoss() {
        switch (orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= speed;
                }
                break;
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.TANK_SIZE) {
                    this.y += speed;
                }
                break;
            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= speed;
                }
                break;
            case Const.RIGHT_ORIENT:
                if (x < Const.MAP_SIZE - Const.TANK_SIZE) {
                    this.x += speed;
                }
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
