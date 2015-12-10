/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Dalibor Fujerík, FUJ0009
 */
public class Bullets {

    private TheSlayersUtils utils;
    private Image bullet; // obrazok
    private int x; //suradnice
    private int y;
    private int speed;
    private boolean delete; // ci treba objekt vymazať z array listu
    private char direction;

    public Bullets(TheSlayersUtils utils, int x, int y, char direction, Image img) {
        this.utils = utils;
        ImageIcon image = new ImageIcon(this.getClass().getResource("../pictures/bullet.png"));
        bullet = image.getImage();
        this.direction = direction;
        setPosition(x, y, img);
        this.delete = false;
        this.speed = 20;
    }

    public void setPosition(int x, int y, Image img) {
        if (direction == 'u') {
            this.x = x + (img.getWidth(null) / 2) - (bullet.getWidth(null) / 2);
            this.y = y - bullet.getHeight(null) - 3;
        }
        if (direction == 'd') {
            this.x = x + (img.getWidth(null) / 2) - (bullet.getWidth(null) / 2);
            this.y = y + img.getHeight(null) + 3;
        }
        if (direction == 'l') {
            this.x = x - bullet.getWidth(null) - 3;
            this.y = y + (img.getHeight(null) / 2) - (bullet.getHeight(null) / 2);
        }
        if (direction == 'r') {
            this.x = x + img.getWidth(null) + 3;
            this.y = y + (img.getHeight(null) / 2) - (bullet.getHeight(null) / 2);
        }
    }

    public void vykreslit(Graphics g) {
        g.drawImage(bullet, x, y, null);
    }

    public void move() {
        if (direction == 'u') {
            y -= speed;
        }
        if (direction == 'd') {
            y += speed;
        }
        if (direction == 'l') {
            x -= speed;
        }
        if (direction == 'r') {
            x += speed;
        }

    }

    public boolean deleting() {

        if (y > utils.getHeight() || y < 0 || x > utils.getWidth() || x < 0) {
            delete = true;
        }
        for (int i = 0; i < utils.prekazky.length; i++) {
            if (getRectangle().intersects(utils.prekazky[i])) {
                delete = true;
            }
        }
        return delete;
    }

    public Rectangle getRectangle() {
        Rectangle rectang = new Rectangle(x, y, bullet.getWidth(null), bullet.getHeight(null));
        return rectang;
    }
}
