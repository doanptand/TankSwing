package com.ddona.tank.model;

import com.ddona.tank.manager.BossManager;
import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
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
        this.orient = Const.DOWN_ORIENT;
        this.speed = Const.STANDARD_SPEED / 4;
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

    public void moveTank(MapManager mapManager, TankPlayer player, Bird bird, BossManager bossManager, boolean changeOrient) {
        //FIXME fix thuat toan de impove khi boss va cham tuong. quay dau chua nhanh
        if (changeOrient) {
            getNewOrient();
        }
        switch (this.orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= speed;
                    if (isIntersectWithMapAndBird(mapManager, bird)) {
                        y += speed;
                        getNewOrient();
                    }
                } else if (isIntersectWithPlayer(player)) {
                    y += speed;
                    this.orient = Const.DOWN_ORIENT;
                } else if (isIntersectWithOtherBoss(bossManager)) {
                    y += speed;
                    getNewOrient();
                    System.out.println();
                } else if (nearCollision(bossManager, player)) {
                    y += speed;
                    getNewOrient();
                }
                break;
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.TANK_SIZE) {
                    y += speed;
                    if (isIntersectWithMapAndBird(mapManager, bird)) {
                        y -= speed;
                        getNewOrient();
                    }
                } else if (isIntersectWithPlayer(player)) {
                    y -= speed;
                    this.orient = Const.UP_ORIENT;
                } else if (isIntersectWithOtherBoss(bossManager)) {
                    y -= speed;
                    getNewOrient();
                } else if (nearCollision(bossManager, player)) {
                    y -= speed;
                    getNewOrient();
                }
                break;
            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= speed;
                    if (isIntersectWithMapAndBird(mapManager, bird)) {
                        x += speed;
                        getNewOrient();
                    }
                } else if (isIntersectWithPlayer(player)) {
                    x += speed;
                    this.orient = Const.RIGH_ORIENT;
                } else if (isIntersectWithOtherBoss(bossManager)) {
                    x += speed;
                    getNewOrient();
                } else if (nearCollision(bossManager, player)) {
                    x += speed;
                    getNewOrient();
                }
                break;
            case Const.RIGH_ORIENT:
                if (x < Const.MAP_SIZE - Const.TANK_SIZE) {
                    x += speed;
                    if (isIntersectWithMapAndBird(mapManager, bird)) {
                        x -= speed;
                        getNewOrient();
                    }
                } else if (isIntersectWithPlayer(player)) {
                    x -= speed;
                    this.orient = Const.LEFT_ORIENT;
                } else if (isIntersectWithOtherBoss(bossManager)) {
                    x -= speed;
                    getNewOrient();
                } else if (nearCollision(bossManager, player)) {
                    x -= speed;
                    getNewOrient();
                }
                break;
        }
    }

    private boolean nearCollision(BossManager bossManager, TankPlayer player) {
        //TODO implement more logic to check near Collision between tank-boss and boss-boss
//        for (int i = 0; i < bossManager.getBosses().size(); i++) {
//            if (!bossManager.getBosses().get(i).equals(this)) {
//                if (bossManager.getBosses().get(i).getRect().union(getRect()).getHeight() < 111
//                        || bossManager.getBosses().get(i).getRect().union(getRect()).getWidth() < 111) {
//                    return true;
//                }
//            }
//        }
//        if (getRect().union(player.getRect()).getWidth() < 140 || getRect().union(player.getRect()).getHeight() < 140) {
//            return true;
//        }
        return false;
    }

    private boolean isIntersectWithOtherBoss(BossManager bossManager) {
        //TODO implement intersect with boss-boss
        return false;
    }

    private boolean isIntersectWithPlayer(TankPlayer player) {
        //TODO implement intersect between boss-tank
        return false;
    }

    private boolean isIntersectWithMapAndBird(MapManager mapManager, Bird bird) {
        for (int i = 0; i < mapManager.getArrMaps().size(); i++) {
            MapItem mapItem = mapManager.getArrMaps().get(i);
            if (getRect().intersects(mapItem.getRect()) && !(mapItem.allowTankPass())) {
                return true;
            }
            if (getRect().intersects(bird.getRect())) {
                return true;
            }
        }

        return false;
    }

    private void getNewOrient() {
        int newOrient;
        do {
            newOrient = new Random().nextInt(4);
        } while (this.orient == newOrient);
        this.orient = newOrient;
        setOrient(orient);
    }

    public boolean beHit() {
        this.hp--;
        return hp == 0;
    }
}
