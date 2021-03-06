package com.ddona.tank.gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private SettingsPanel mSettingsPanel;
    private MainPanel mMainPanel;

    public GameFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new CardLayout());
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        mMainPanel = new MainPanel(this);
        mSettingsPanel = new SettingsPanel(this);
        add(mSettingsPanel);
        add(mMainPanel);
    }

    public void showGame() {
        mMainPanel.setVisible(true);
        mSettingsPanel.setVisible(false);
        mMainPanel.startGame();
    }

    public void showSettings() {
        mMainPanel.setVisible(false);
        mSettingsPanel.setVisible(true);
    }
}
