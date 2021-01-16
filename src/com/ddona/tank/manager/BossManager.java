package com.ddona.tank.manager;

import com.ddona.tank.model.Bird;
import com.ddona.tank.model.TankBoss;
import com.ddona.tank.model.TankPlayer;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossManager {
    private final List<TankBoss> mBosses;
    private final MapManager mMapManager;
    private final TankPlayer mPlayer;
    private final Bird mBird;
    private int bossCount;
    private final Random mRandom;

    public BossManager(MapManager mapManager, TankPlayer tankPlayer, Bird bird) {
        this.mMapManager = mapManager;
        this.mPlayer = tankPlayer;
        this.mBird = bird;
        this.mBosses = new ArrayList<>();
        this.bossCount = 24;
        this.mRandom = new Random();
    }

    public boolean addBosses() {
        if (mBosses.size() < 2 && bossCount > 0) {
            System.out.println("add 3 bosses");
            for (int i = 0; i < 3; i++) {
                int level = bossCount >= 12 ? 1 : bossCount >= 6 ? 2 : 3;
                System.out.println("add boss:" + level);
                mBosses.add(new TankBoss(
                        i * (Const.MAP_SIZE / 2 / Const.TANK_SIZE) * Const.TANK_SIZE,
                        0,
                        level));
            }
            bossCount -= 3;
            return true;
        }
        return false;
    }

    public void drawAllBosses(Graphics2D g2d) {
        for (TankBoss boss : mBosses) {
            boss.draw(g2d);
        }
    }


    public void moveAllBosses() {
        for (TankBoss boss : mBosses) {
            boss.moveTank(mMapManager, mPlayer, mBird, this, mRandom.nextInt(1000) > 996);
        }
    }

    public int getBossCount() {
        return bossCount;
    }

    public List<TankBoss> getBosses() {
        return mBosses;
    }
}
