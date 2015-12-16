/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class Slayer2 extends BaseSlayer{

    public Slayer2(TheSlayersUtils utils) {
        super(utils, "../pictures/slayer2D.png");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2U.gif"));
            slayer = player.getImage();
            moveY = -3;
            moveX = 0;
            lastMovement = 'u';
        }
        if (key == KeyEvent.VK_S) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2D.gif"));
            slayer = player.getImage();
            moveY = 3;
            moveX = 0;
            lastMovement = 'd';
        }
        if (key == KeyEvent.VK_D) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2R.gif"));
            slayer = player.getImage();
            moveX = 3;
            moveY = 0;
            lastMovement = 'r';
        }
        if (key == KeyEvent.VK_A) {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2L.gif"));
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
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2D.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'u') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2U.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'l') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2L.png"));
            slayer = player.getImage();
        }
        if (lastMovement == 'r') {
            ImageIcon player = new ImageIcon(this.getClass().getResource("../pictures/slayer2R.png"));
            slayer = player.getImage();
        }

        int key = e.getKeyCode();
        if (key == 17) {
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
