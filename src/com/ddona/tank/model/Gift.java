package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public class Gift extends TankObject{

    private int indexGift;
    public Gift(int x, int y, int indexGift) {
        super();
        this.x = x;
        this.y = y;
        this.width = Const.TANK_SIZE;
        this.height = Const.TANK_SIZE;
        this.indexGift = indexGift;
        this.icon = ImageMgr.arrGiftImages.get(indexGift);
    }

    public int getIndexGift() {
        return indexGift;
    }

    public void setIndexGift(int indexGift) {
        this.indexGift = indexGift;
    }
}
