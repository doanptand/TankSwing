package com.ddona.tank.gui;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.util.Const;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private GameFrame mGameFrame;
    private MapPanel mMapPanel;

    public MainPanel(GameFrame frame) {
        this.mGameFrame = frame;
        setLayout(null);
        mMapPanel = new MapPanel();
        add(mMapPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ImageMgr.imageLeft,
                Const.PADDING_LEFT,
                Const.PADDING_TOP,
                Const.LEFT_RIGHT_SIZE,
                Const.MAP_SIZE,
                null);

        g2d.drawImage(ImageMgr.imageRight,
                Const.RIGHT_START_X,
                Const.PADDING_TOP,
                Const.LEFT_RIGHT_SIZE,
                Const.MAP_SIZE,
                null);
    }
}
