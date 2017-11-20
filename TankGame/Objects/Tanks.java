/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import TankGame.GameEvents;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jack
 */
public class Tanks extends GameObject {
    private int health, lives, up, down, left, right, angle = 0, shotCoolDown = 0, shotRate, shotButton, cooldown = 0;
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private Image shellImage;
    private ArrayList<Shell> bulletList = new ArrayList<>();

    public Tanks(Image tank, int x, int y, int speed, int health, int lives, int w, 
            int h, int up, int down, int left, int right, int shotButton, int angle) {
        super(tank, x, y, speed);
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.health = health;
        this.lives = lives;
        this.width = w - 65;
        this.height = h - 90;
        this.angle = angle;
        shotRate = 15;
        this.shotButton = shotButton;
        try {
            shellImage = ImageIO.read(new File("TankGame/Resource/Shell_basic_strip60.png"));
        } catch (Exception e) {
            System.out.println("No Shell File Found");
        }

    }

    @Override
    public void draw(ImageObserver obs, Graphics2D g2) {
        shotCoolDown--;
        g2.drawImage(img, x, y, x + (img.getWidth(null) / 60),
                y + img.getHeight(null), angle * 64, 0,
                angle * 64 + 64, img.getHeight(null), obs);
    }


    @Override
    public boolean collision(int x, int y, int width, int height) {
        // box = new Rectangle(this.x, this.y, this.width, this.height);
        return false;
    }

    public void updateMove() {
        if (moveUp == true) {
            x += speed * Math.cos(Math.toRadians(6 * angle));
            y -= speed * Math.sin(Math.toRadians(6 * angle));
            }
        if (moveDown == true) {
            x -= speed * Math.cos(Math.toRadians(6 * angle));
            y += speed * Math.sin(Math.toRadians(6 * angle));
            }


        if (moveRight == true)
            angle -= 1;
        if (moveLeft == true)
            angle += 1;
        if (angle == -1)
            angle = 59;
        else if (angle == 60)
            angle = 0;
        if (cooldown > 0) {
            moveLeft = false;
            moveRight = false;
            moveUp = false;
            moveDown = false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if (ge.getType() == 1) {
            KeyEvent e = (KeyEvent) ge.getEvent();
            if (e.getKeyCode() == left) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    moveLeft = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    moveLeft = true;
            }
            if (e.getKeyCode() == right) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    moveRight = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    moveRight = true;
            }
            if (e.getKeyCode() == up) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    moveUp = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    moveUp = true;
            }
            if (e.getKeyCode() == down) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    moveDown = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    moveDown = true;
            }
            if (e.getKeyCode() == shotButton && shotCoolDown <= 0) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    shotCoolDown = shotRate;
                    shoot();
                }

            }
        } else if (ge.getType() == 2) {
            String msg = (String) ge.getEvent();
            if (msg.equals("Explosion")) {
                System.out.println("Explosion! Reduce Health");
            }
    }

}

    public void shoot() {
        Shell playerShell;
        playerShell = new Shell(shellImage, this.x + (img.getWidth(null)/120)-10, 
                this.y +img.getHeight(null)/2-20, 10, 5, this);
        bulletList.add(playerShell);
    }

    public int getAngle() {
        return this.angle;
    }

    public ArrayList<Shell> getBulletList() {
        return bulletList;
    }

}

