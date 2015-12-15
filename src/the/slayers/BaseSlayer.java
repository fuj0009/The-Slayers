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
public abstract class BaseSlayer implements KeyListener {

    private TheSlayersUtils utils;
    protected Image slayer;
    protected int x;
    protected int y;
    protected int moveX;
    protected int moveY;
    protected char lastMovement;
    protected boolean shooting;

    public BaseSlayer(TheSlayersUtils utils, String iconPath) {
        this.utils = utils;
        ImageIcon player = new ImageIcon(this.getClass().getResource(iconPath));
        slayer = player.getImage();

        x = new Random().nextInt(1094 - slayer.getWidth(null));
        y = new Random().nextInt(671 - slayer.getHeight(null));

        moveX = 0;
        moveY = 0;

        if (RectangleTest()) {
            newPosition();
        }

        shooting = false;
        lastMovement = 'd';
    }

    public void drawSlayer(Graphics g) {
        g.drawImage(slayer, x, y, null);
    }

    public boolean RectangleTest() {
        Rectangle slayr = new Rectangle(x + moveX, y + moveY, slayer.getWidth(null), slayer.getHeight(null));

        for (int i = 0; i < utils.prekazky.length; i++) {
            if (slayr.intersects(utils.prekazky[i])) {
                return true;
            }
        }
        if (utils.single) {
            for (int i = 0; i < utils.getBots().size(); i++) {
                Bot bot = utils.getBots().get(i);
                if (slayr.intersects(bot.getRectangle())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {
        if ((x + moveX) < 0 || (x + moveX) >= (utils.getWidth() - slayer.getWidth(null))) {
            moveX = 0;
        }
        if ((y + moveY) < 0 || (y + moveY) > (utils.getHeight() - slayer.getHeight(null))) {
            moveY = 0;
        }

        if (RectangleTest()) {
            moveX = 0;
            moveY = 0;
        }
        System.out.println(moveX);
        x += moveX;
        y += moveY;
    }

    public void newPosition() {
        x = new Random().nextInt(1094 - slayer.getWidth(null));
        y = new Random().nextInt(671 - slayer.getHeight(null));

        while (RectangleTest()) {
            x = new Random().nextInt(1094 - slayer.getWidth(null));
            y = new Random().nextInt(671 - slayer.getHeight(null));
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxEnd() {
        return x + slayer.getWidth(null);
    }

    public int getyEnd() {
        return y + slayer.getHeight(null);
    }

    public char getDirection() {
        return lastMovement;
    }

    public boolean getShooting() {
        return shooting;
    }

    public void disableShooting() {
        shooting = false;
    }

    public Image getImage() {
        return slayer;
    }

    public Rectangle getRectangle() {
        Rectangle rectang = new Rectangle(x, y, slayer.getWidth(null), slayer.getHeight(null));
        return rectang;
    }
}
