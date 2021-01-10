package com.ddona.tank.gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private GameFrame mGameFrame;

    public MainPanel(GameFrame frame) {
        this.mGameFrame = frame;
        setLayout(null);
        setBackground(Color.RED);
    }
}
