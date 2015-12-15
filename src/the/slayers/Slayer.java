/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class Slayer extends BaseSlayer {

    public Slayer(TheSlayersUtils utils) {
        super(utils, "../pictures/playerD.png");
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
