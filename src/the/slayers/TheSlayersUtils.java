/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class TheSlayersUtils extends JPanel implements ActionListener {

    private Slayer slayer;
    private Bot bot;
    private Timer timer;

    public TheSlayersUtils() {
        setBackground(Color.black);
        setFocusable(true);
        slayer = new Slayer(this);
        addKeyListener(slayer);
        bot = new Bot(this);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        slayer.drawSlayer(g);
        bot.drawBot(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slayer.move();
        this.repaint();
    }
}
