package com.ddona.tank.gui;

import com.ddona.tank.manager.MapManager;
import com.ddona.tank.model.Bird;
import com.ddona.tank.util.Const;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private MapManager mapManager;
    private Bird mBird;

    public MapPanel() {
        setBounds((Const.WIDTH_FRAME - Const.MAP_SIZE) / 2,
                Const.PADDING_TOP,
                Const.MAP_SIZE,
                Const.MAP_SIZE);
        setBackground(Color.BLACK);
        initComponents();
        setLayout(null);
    }

    private void initComponents() {
        mapManager = new MapManager(1);
        mBird = new Bird(
                12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        mBird.draw(g2d);
    }
}
