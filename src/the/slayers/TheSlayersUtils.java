/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.slayers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author FUJ0009, GAB0046
 */
public class TheSlayersUtils extends JPanel implements ActionListener {

    private Image background;

    private Image prekazka1;
    private Image prekazka2;
    private Image prekazka3;
    private Image prekazka4;
    public Rectangle[] prekazky = new Rectangle[4];

    private ArrayList<Bullets> bullets;

    private ArrayList<Bot> bots;
    private int BotTeleporting;

    private Slayer slayer;
    private Bot bot;
    private Timer timer;

    public TheSlayersUtils() {
        ImageIcon back = new ImageIcon(this.getClass().getResource("../pictures/floor.jpg"));
        background = back.getImage();
        setFocusable(true);

        addPrekazky();

        slayer = new Slayer(this);
        addKeyListener(slayer);

        BotTeleporting = 1735;
        bots = new ArrayList<Bot>();
        botAdd(3);

        bullets = new ArrayList<Bullets>();
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        slayer.drawSlayer(g);

        for (int i = 0; i < bots.size(); i++) {
            Bot bot = bots.get(i);
            bot.drawBot(g);
        }

        g.drawImage(prekazka1, 450, 200, this);
        g.drawImage(prekazka2, 150, 100, this);
        g.drawImage(prekazka3, 230, 500, this);
        g.drawImage(prekazka4, 880, 100, this);

        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            bullet.vykreslit(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slayer.move();
        bulletAdd();
        bulletMove();
        bulletDelete();

        for (int i = 0; i < bots.size(); i++) {
            Bot bot = bots.get(i);
            botShooting(bot);
            botDead(bot);
            
            if (BotTeleporting < 0) {
                bot.newPosition();
                BotTeleporting = 1735;
            }
            BotTeleporting--;
        }

        this.repaint();
    }

    private void addPrekazky() {
        ImageIcon prekazka = new ImageIcon(this.getClass().getResource("../pictures/prakazka0.png"));
        prekazka1 = prekazka.getImage();
        prekazka = new ImageIcon(this.getClass().getResource("../pictures/prakazka1.png"));
        prekazka2 = prekazka.getImage();
        prekazka = new ImageIcon(this.getClass().getResource("../pictures/prakazka2.png"));
        prekazka3 = prekazka.getImage();
        prekazka = new ImageIcon(this.getClass().getResource("../pictures/prakazka3.png"));
        prekazka4 = prekazka.getImage();

        prekazky[0] = new Rectangle(450, 200, prekazka1.getWidth(null), prekazka1.getHeight(null));
        prekazky[1] = new Rectangle(150, 100, prekazka2.getWidth(null), prekazka2.getHeight(null));
        prekazky[2] = new Rectangle(230, 500, prekazka3.getWidth(null), prekazka3.getHeight(null));
        prekazky[3] = new Rectangle(880, 100, prekazka4.getWidth(null), prekazka4.getHeight(null));
    }

    private void bulletMove() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            bullet.move();
        }
    }

    private void bulletAdd() {
        if (slayer.getShooting()) {
            Bullets bullet = new Bullets(this, slayer.getX(), slayer.getY(), slayer.getDirection(), slayer.getImage());
            bullets.add(bullet);
            slayer.disableShooting();
        }
    }

    private void bulletDelete() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            if (bullet.deleting()) {
                bullets.remove(bullet);
            }
        }
    }

    private void botAdd(int n) {
        for (int i = 0; i < n; i++) {
            Bot bot = new Bot(this);
            bots.add(bot);
        }
    }

    private void botShooting(Bot bot) {
        if (bot.BotDelayShooting < 0) {
            if ((bot.getxEnd() >= slayer.getX() && bot.getxStart() <= slayer.getX())
                    || (bot.getxEnd() >= slayer.getxEnd() && bot.getxStart() <= slayer.getxEnd())) {

                if (bot.getyStart() > slayer.getY()) {

                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'u', bot.getImage());
                    bullets.add(bullet);
                } else {

                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'd', bot.getImage());
                    bullets.add(bullet);
                }
                bot.BotDelayShooting = 300;
            }

            if ((bot.getyEnd() >= slayer.getY() && bot.getyStart() <= slayer.getY())
                    || (bot.getyEnd() >= slayer.getyEnd() && bot.getyStart() <= slayer.getyEnd())) {
                if (bot.getxStart() > slayer.getX()) {
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'l', bot.getImage());
                    bullets.add(bullet);
                } else {
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'r', bot.getImage());
                    bullets.add(bullet);
                }
                bot.BotDelayShooting = 150;
            }

        }
        bot.BotDelayShooting--;
    }
    
    private void botDead(Bot bot) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            if (bot.getRectangle().intersects(bullet.getRectangle())) {
                bullets.remove(bullet);
                bot.newPosition();
            }
            if (slayer.getRectangle().intersects(bullet.getRectangle())) {
                bullets.remove(bullet);
                slayer.newPosition();
            }
        }
    }
    
}
