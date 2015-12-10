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
    public int BotDelayShooting;

    public Bot(TheSlayersUtils utils) {
        this.utils = utils;
        ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyD.png"));
        bot = player.getImage();

        x = new Random().nextInt(1094 - bot.getWidth(null));
        y = new Random().nextInt(671 - bot.getHeight(null));

        BotDelayShooting = 150;
        
        if(RectangleTest()){
            newPosition();
        }
    }

    public void drawBot(Graphics g) {
        g.drawImage(bot, x, y, null);
    }
    
    public boolean RectangleTest() {

        for (int i = 0; i < utils.prekazky.length; i++) {
            if (getRectangle().intersects(utils.prekazky[i])) {
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
    
    public void setImage(char x){
        if(x == 'u'){
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyU.png"));
            bot = player.getImage();
        }
        if(x == 'd'){
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyD.png"));
            bot = player.getImage();
        }
        if(x == 'l'){
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyL.png"));
            bot = player.getImage();
        }
        if(x == 'r'){
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/enemyR.png"));
            bot = player.getImage();
        }
    }
}
