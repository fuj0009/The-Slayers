/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class Slayer implements KeyListener {

    private TheSlayersUtils utils;
    private Image slayer;
    private int x;
    private int y;
    private int moveX;
    private int moveY;

    public Slayer(TheSlayersUtils utils) {
        this.utils = utils;
        ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerD.png"));
        slayer = player.getImage();

        x = new Random().nextInt(1094 - slayer.getWidth(null));
        y = new Random().nextInt(671 - slayer.getHeight(null));

        moveX = 0;
        moveY = 0;
    }

    public void drawSlayer(Graphics g) {
        g.drawImage(slayer, x, y, null);
    }

    public void move() {
        if ((x + moveX) < 0 || (x + moveX) >= (utils.getWidth() - slayer.getWidth(null))) {
            moveX = 0;
        }
        if ((y + moveY) < 0 || (y + moveY) > (utils.getHeight() - slayer.getHeight(null))) {
            moveY = 0;
        }
        Rectangle slayerR = new Rectangle(x + moveX, y + moveY, slayer.getWidth(null), slayer.getHeight(null));

        for (int i = 0; i < utils.prekazky.length; i++) {
            if (slayerR.intersects(utils.prekazky[i])) {
                moveX = 0;
                moveY = 0;
            }
        }

        x += moveX;
        y += moveY;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            moveY = -3;
            moveX = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            moveY = 3;
            moveX = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            moveX = 3;
            moveY = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            moveX = -3;
            moveY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        moveX = 0;
        moveY = 0;
    }
}
