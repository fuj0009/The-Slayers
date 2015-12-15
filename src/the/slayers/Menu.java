/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class Menu {

    private TheSlayersUtils utils;
    private Image menu;
    private MouseImput mouse;

    public static boolean click;

    public static boolean single;
    public static boolean multi;

    public static boolean singN;
    public static boolean singH;
    public static boolean singI;

    public static boolean multN;
    public static boolean multH;
    public static boolean multI;

    public Menu(TheSlayersUtils utils) {
        this.utils = utils;
        single = true;
        multi = false;
    }

    public void init() {
        click = false;

        singN = false;
        singH = false;
        singI = false;

        multN = false;
        multH = false;
        multI = false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (single) {
            ImageIcon back = new ImageIcon(this.getClass().getResource("../pictures/Smenu.png"));
            menu = back.getImage();
        } else {
            ImageIcon back = new ImageIcon(this.getClass().getResource("../pictures/Mmenu.png"));
            menu = back.getImage();
        }

        if (singI) {
            ImageIcon back = new ImageIcon(this.getClass().getResource("../pictures/SingleI.png"));
            menu = back.getImage();
        }

        g.drawImage(menu, 0, 0, null);
    }

    public void setting() {
        if (single) {
            utils.single = true;
            utils.multi = false;

            if (singH) {
                utils.zombieB = true;
            } else {
                utils.zombieB = false;
            }
            if (click) {
                TheSlayersUtils.State = TheSlayersUtils.STATE.START;
                this.init();
            }
        } else {
            utils.single = false;
            utils.multi = true;

            if (multH) {
                utils.zombieB = true;
            } else {
                utils.zombieB = false;
            }
            if (click) {
                TheSlayersUtils.State = TheSlayersUtils.STATE.START;
                this.init();
            }
        }
    }

}
