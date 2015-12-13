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
    private char lastMovement;
    private boolean shooting;

    public Slayer(TheSlayersUtils utils) {
        this.utils = utils;
        ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerD.png"));
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
        for (int i = 0; i < utils.getBots().size(); i++) {
            Bot bot = utils.getBots().get(i);
            if (slayr.intersects(bot.getRectangle())) {
                return true;
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
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerU.gif"));
            slayer = player.getImage();
            moveY = -3;
            moveX = 0;
            lastMovement = 'u';
        }
        if (key == KeyEvent.VK_DOWN) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerD.gif"));
            slayer = player.getImage();
            moveY = 3;
            moveX = 0;
            lastMovement = 'd';
        }
        if (key == KeyEvent.VK_RIGHT) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerR.gif"));
            slayer = player.getImage();
            moveX = 3;
            moveY = 0;
            lastMovement = 'r';
        }
        if (key == KeyEvent.VK_LEFT) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerL.gif"));
            slayer = player.getImage();
            moveX = -3;
            moveY = 0;
            lastMovement = 'l';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        moveX = 0;
        moveY = 0;

        if (lastMovement == 'd') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerD.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'u') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerU.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'l') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerL.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'r') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/playerR.png"));
            slayer = player.getImage();
        }

        int key = e.getKeyCode();
        if (key == 32) {
            shooting = true;
        }
        if (key == 27) {
            TheSlayersUtils.State = TheSlayersUtils.STATE.MENU;
        }
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
