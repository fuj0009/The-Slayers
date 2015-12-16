/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.UUID;
import javax.swing.ImageIcon;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class Bot {

    private TheSlayersUtils utils;
    private Image bot;
    private int x;
    private int y;
    private int moveX;
    private int moveY;
    public int BotDelayShooting;
    private int pohyb;
    private int zmenaPohyb;
    String uniqueID = UUID.randomUUID().toString();

    public Bot(TheSlayersUtils utils) {
        this.utils = utils;
        ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyD.png"));
        bot = player.getImage();

        pohyb = new Random().nextInt(4) + 1;
        x = new Random().nextInt(1094 - bot.getWidth(null));
        y = new Random().nextInt(671 - bot.getHeight(null));
        moveX = 0;
        moveY = 0;
        zmenaPohyb = 300;
        BotDelayShooting = 150;

        if (RectangleTest()) {
            newPosition();
        }
    }

    public String getUID() {
        return uniqueID;
    }

    public void drawBot(Graphics g) {
        g.drawImage(bot, x, y, null);
    }

    public boolean RectangleTest() {
        Rectangle botO = new Rectangle(x + moveX, y + moveY, bot.getWidth(null), bot.getHeight(null));

        if (utils.getSlayer().getRectangle().intersects(botO)) {
            return true;
        }

        for (int i = 0; i < utils.prekazky.length; i++) {
            if (botO.intersects(utils.prekazky[i])) {
                return true;
            }
        }
        for (int i = 0; i < utils.getBots().size(); i++) {
            Bot bot = utils.getBots().get(i);
            if (botO.intersects(bot.getRectangle()) && uniqueID != bot.getUID()) {
                return true;
            }
        }
        return false;
    }

    public void newPosition() {
        x = new Random().nextInt(1094 - bot.getWidth(null));
        y = new Random().nextInt(671 - bot.getHeight(null));

        while (RectangleTest()) {
            x = new Random().nextInt(1094 - bot.getWidth(null));
            y = new Random().nextInt(671 - bot.getHeight(null));
        }
    }

    public void move() {
        zmenaPohyb--;
        ImageIcon zombie;
        switch (pohyb) {
            //hore
            case 1:
                zombie = new ImageIcon(this.getClass().getResource("../pictures/zombieU.gif"));
                bot = zombie.getImage();
                moveY = -1;
                moveX = 0;
                break;
            //dole
            case 2:
                zombie = new ImageIcon(this.getClass().getResource("../pictures/zombieD.gif"));
                bot = zombie.getImage();
                moveY = 1;
                moveX = 0;
                break;
            //do lava
            case 3:
                zombie = new ImageIcon(this.getClass().getResource("../pictures/zombieL.gif"));
                bot = zombie.getImage();
                moveX = -1;
                moveY = 0;
                break;
            //do prava
            case 4:
                zombie = new ImageIcon(this.getClass().getResource("../pictures/zombieR.gif"));
                bot = zombie.getImage();
                moveX = 1;
                moveY = 0;
                break;
        }
        
        if ((x + moveX) < 0 || (x + moveX) >= (utils.getWidth() - bot.getWidth(null))) {
            moveX = 0;
            pohyb = new Random().nextInt(4) + 1;
        }
        if ((y + moveY) < 0 || (y + moveY) > (utils.getHeight() - bot.getHeight(null))) {
            moveY = 0;
            pohyb = new Random().nextInt(4) + 1;
        }

        if (RectangleTest()) {
            moveX = 0;
            moveY = 0;
            pohyb = new Random().nextInt(4) + 1;
        }
        if (zmenaPohyb < 0) {
            pohyb = new Random().nextInt(4) + 1;
            zmenaPohyb = 300;
        }

        x += moveX;
        y += moveY;
    }

    public int getxStart() {
        return x;
    }

    public int getxEnd() {
        return x + bot.getWidth(null);
    }

    public int getyStart() {
        return y;
    }

    public int getyEnd() {
        return y + bot.getHeight(null);
    }

    public Rectangle getRectangle() {
        Rectangle rectang = new Rectangle(x, y, bot.getWidth(null), bot.getHeight(null));
        return rectang;
    }

    public Image getImage() {
        return bot;
    }

    public void setImage(char x) {
        if (x == 'u') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyU.png"));
            bot = player.getImage();
        }
        if (x == 'd') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyD.png"));
            bot = player.getImage();
        }
        if (x == 'l') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyL.png"));
            bot = player.getImage();
        }
        if (x == 'r') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyR.png"));
            bot = player.getImage();
        }
    }
}
