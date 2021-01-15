package com.ddona.tank.manager;

import com.ddona.tank.model.TankBoss;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossManager {
    private List<TankBoss> mBosses;
    private int bossCount;
    private final Random mRandom;

    public BossManager() {
        mBosses = new ArrayList<>();
        bossCount = 24;
        mRandom = new Random();
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
            if (mRandom.nextInt(1000) > 996) {
                boss.moveTank(mRandom.nextInt(4));
            } else {
                boss.moveTank(boss.getOrient());
            }

        }
    }

    public int getBossCount() {
        return bossCount;
    }
}
