/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author dfuje
 */
public class MouseImput implements MouseListener {
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //hlavne menu
        if (TheSlayersUtils.State == TheSlayersUtils.STATE.MENU) {
            if (my > 518 && my < 584) {
                //singleplayer
                if (mx > 153 && mx < 463) {
                    Menu.single = true;
                    Menu.multi = false;
                    
                    Menu.singI = false;
                    Menu.multI = false;
                }
                //multiplayer
                if (mx > 470 && mx < 780) {
                    Menu.single = false;
                    Menu.multi = true;
                    
                    Menu.singI = false;
                    Menu.multI = false;
                }
                //exit
                if (mx > 787 && mx < 950) {
                    System.exit(1);
                }
            }
            if (my > 585 && my < 662) {
                //normal
                if (mx > 127 && mx < 417) {
                    if(Menu.single){
                        Menu.singN = true;
                        Menu.multN = false;
                        
                        TheSlayersUtils.State = TheSlayersUtils.STATE.GAME;
                    }
                    else{
                        Menu.singN = false;
                        Menu.multN = true;
                    }
                }
                //hard
                if (mx > 420 && mx < 652) {
                    if(Menu.single){
                        Menu.singH = true;
                        Menu.multH = false;
                    }
                    else{
                        Menu.singH = false;
                        Menu.multH = true;
                    }
                }
                //instruction
                if (mx > 655 && mx < 982) {
                    if(Menu.single){
                        Menu.singI = true;
                        Menu.multI = false;
                    }
                    else{
                        Menu.singI = false;
                        Menu.multI = true;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
