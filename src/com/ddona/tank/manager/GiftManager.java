package com.ddona.tank.manager;

import com.ddona.tank.model.Gift;
import com.ddona.tank.model.TankPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GiftManager {
    private final List<Gift> arrGift;
    private final BossManager boss;
    private final TankPlayer player;
    public GiftManager(TankPlayer player, BossManager boss) {
        arrGift = new ArrayList<>();
        this.player = player;
        this.boss = boss;
    }

    public void drawAllGifts(Graphics2D g2d) {
        int size = arrGift.size();
        for (int i = 0; i < size; i++) {
            arrGift.get(i).draw(g2d);
        }
    }

    public void removeGift(Gift g) {
        arrGift.remove(g);
    }

    public void addNewGift(int x, int y, int type) {
        arrGift.add(new Gift(x, y, type));
    }


    public void isTankIntersectWithGift() {
        for (int i = 0; i < arrGift.size(); i++) {
            if (arrGift.get(i).getRect().intersects(player.getRect())) {
                if (arrGift.get(i).getIndexGift() == 0) {
                    for (int k = 0; k < boss.getBosses().size(); k++) {
                        //TODO play effect to bum all bosses
                    }
                    boss.getBosses().clear();
                    arrGift.remove(i);
                } else if (arrGift.get(i).getIndexGift() == 1) {
                    //TODO increase live count for tank player
                    arrGift.remove(i);
                }
            }
        }
    }
}
