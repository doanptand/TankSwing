package com.ddona.tank.manager;

import com.ddona.tank.model.Boss;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BossManager {
    private List<Boss> mBosses;
    private int bossCount;

    public BossManager() {
        mBosses = new ArrayList<>();
        bossCount = 24;
    }

    public boolean addMoreBosses() {
        if (mBosses.size() < 2 && bossCount > 0) {
            int level = bossCount > 12 ? 1 : bossCount > 3 ? 2 : 3;
            for (int i = 0; i < 3; i++) {
                mBosses.add(new Boss(
                        i * (Const.MAP_SIZE / 2 - Const.TANK_SIZE / 2),
                        0,
                        level)
                );
            }
            bossCount -= 3;
            return true;
        }
        return false;
    }

    public void drawAllBosses(Graphics2D g2d) {
        for (int i = 0; i < mBosses.size(); i++) {
            mBosses.get(i).draw(g2d);
        }
    }
}
