/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import javax.swing.JFrame;
/**
 *
 * @author FUJ0009, GAB0046
 */
public class TheSlayers extends JFrame{
    
    public TheSlayers(){
        setTitle("The Slayers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        TheSlayersUtils utils = new TheSlayersUtils();
        add(utils);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TheSlayers slayers = new TheSlayers();
        slayers.setLocationRelativeTo(null);
        slayers.setResizable(false);
        slayers.setVisible(true);
    }
    
}