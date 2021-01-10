package com.ddona.tank.gui;

import com.ddona.tank.manager.MapManager;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private GameFrame mGameFrame;
    private MapManager mapManager;
    public MainPanel(GameFrame frame) {
        this.mGameFrame = frame;
        setLayout(null);
        setBackground(Color.RED);
        mapManager = new MapManager(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
    }
}
