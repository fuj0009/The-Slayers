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

    private BaseSlayer slayer;
    private BaseSlayer slayer2;
    private int slayer1Deads;
    private int slayer2Deads;

    private ArrayList<Bullets> bullets;

    private ArrayList<Bot> bots;
    private int BotTeleporting;

    private Menu menu;
    public boolean single;
    public boolean multi;
    public boolean zombieB;

    private Bot bot;
    private Timer timer;

    public static enum STATE {

        MENU,
        START,
        GAME,
    };
    public static STATE State = STATE.MENU;

    public TheSlayersUtils() {
        init();
        start();
    }

    private void init() {
        ImageIcon back = new ImageIcon(this.getClass().getResource("../pictures/floor.jpg"));
        background = back.getImage();
        setFocusable(true);
    }

    private void start() {
        bots = new ArrayList<Bot>();
        addPrekazky();

        slayer = new Slayer(this);
        addKeyListener(slayer);
        slayer1Deads = 0;

        if (multi) {
            slayer2 = new Slayer2(this);
            addKeyListener(slayer2);
            slayer2Deads = 0;
        }

        addMouseListener(new MouseImput());

        BotTeleporting = 1735;
        botAdd(3);

        bullets = new ArrayList<Bullets>();

        menu = new Menu(this);

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (State == STATE.GAME) {

            g.drawImage(background, 0, 0, this);

            if (single) {
                slayer.drawSlayer(g);

                for (int i = 0; i < bots.size(); i++) {
                    Bot bot = bots.get(i);
                    bot.drawBot(g);
                }

            } else { //multi
                slayer.drawSlayer(g);
                slayer2.drawSlayer(g);

                if (zombieB) {
                    for (int i = 0; i < bots.size(); i++) {
                        Bot bot = bots.get(i);
                        bot.drawBot(g);
                    }
                }
            }

            g.drawImage(prekazka1, 450, 200, this);
            g.drawImage(prekazka2, 150, 100, this);
            g.drawImage(prekazka3, 230, 500, this);
            g.drawImage(prekazka4, 880, 100, this);

            for (int i = 0; i < bullets.size(); i++) {
                Bullets bullet = bullets.get(i);
                bullet.vykreslit(g);
            }
        } else if (State == STATE.MENU) {
            menu.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (State == STATE.START) {
            State = STATE.GAME;
            timer.stop();
            this.start();
        }

        if (State == STATE.GAME) {

            if (single) {
                slayer.move();
                this.deadS(slayer);
                bulletAdd(slayer);
                for (int i = 0; i < bots.size(); i++) {
                    Bot bot = bots.get(i);
                    botShooting(bot, slayer);
                    dead(bot);
                    if (zombieB) {
                        bot.move();
                    } else {
                        if (BotTeleporting < 0) {
                            bot.newPosition();
                            BotTeleporting = 1735;
                        }
                        BotTeleporting--;
                    }
                }
            } else { // multi
                slayer.move();
                bulletAdd(slayer);
                this.deadS(slayer);
                slayer2.move();
                bulletAdd(slayer2);
                this.deadS2(slayer2);
                if (zombieB) {
                    for (int i = 0; i < bots.size(); i++) {
                        Bot bot = bots.get(i);
                        botShooting(bot, slayer);
                        botShooting(bot, slayer2);

                        dead(bot);
                        bot.move();
                    }
                }
            }

            bulletMove();
            bulletDelete();
        } else {
            menu.setting();
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

    private void bulletAdd(BaseSlayer slay) {
        if (slay.getShooting()) {
            Bullets bullet = new Bullets(this, slay.getX(), slay.getY(), slay.getDirection(), slay.getImage());
            bullets.add(bullet);
            slay.disableShooting();
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

    public ArrayList<Bot> getBots() {
        return bots;
    }

    public BaseSlayer getSlayer() {
        return slayer;
    }

    public BaseSlayer getSlayer2() {
        return slayer2;
    }

    private void botShooting(Bot bot, BaseSlayer slay) {
        if (bot.BotDelayShooting < 0) {
            if ((bot.getxEnd() >= slay.getX() && bot.getxStart() <= slay.getX())
                    || (bot.getxEnd() >= slay.getxEnd() && bot.getxStart() <= slay.getxEnd())) {

                if (bot.getyStart() > slay.getY()) {
                    bot.setImage('u');
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'u', bot.getImage());
                    bullets.add(bullet);
                } else {
                    bot.setImage('d');
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'd', bot.getImage());
                    bullets.add(bullet);
                }
                bot.BotDelayShooting = 300;
            }

            if ((bot.getyEnd() >= slay.getY() && bot.getyStart() <= slay.getY())
                    || (bot.getyEnd() >= slay.getyEnd() && bot.getyStart() <= slay.getyEnd())) {
                if (bot.getxStart() > slay.getX()) {
                    bot.setImage('l');
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'l', bot.getImage());
                    bullets.add(bullet);
                } else {
                    bot.setImage('r');
                    Bullets bullet = new Bullets(this, bot.getxStart(), bot.getyStart(), 'r', bot.getImage());
                    bullets.add(bullet);
                }
                bot.BotDelayShooting = 300;
            }

        }
        bot.BotDelayShooting--;
    }

    private void dead(Bot bot) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            if (bot.getRectangle().intersects(bullet.getRectangle())) {
                bullets.remove(bullet);
                bot.newPosition();
            }
        }
    }
    
    private void deadS(BaseSlayer slay) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            if (slay.getRectangle().intersects(bullet.getRectangle())) {
                bullets.remove(bullet);
                slay.newPosition();
                slayer1Deads ++;
            }
 
        }
    }
    
    private void deadS2(BaseSlayer slay) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullets bullet = bullets.get(i);
            if (slay.getRectangle().intersects(bullet.getRectangle())) {
                bullets.remove(bullet);
                slay.newPosition();
                slayer2Deads ++;
            }
 
        }
    }

}
