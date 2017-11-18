/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import TankGame.GameEvents;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jack
 */
public class Tanks extends GameObject{
    private int  health, lives, up, down, left, right, angle = 0, shotCoolDown = 0, shotRate, shotButton, cooldown = 0;
    private boolean moveUp,moveDown,moveLeft,moveRight;

    public Tanks(Image tank, int x,int y, int speed, int health, int lives, int up, int down, int left, int right, int shotButton) {
        super(tank,x,y,speed);
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.health = health;
        this.lives = lives;
        shotRate = 15;
        this.shotButton = shotButton;

        
        
    }
    @Override
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(img,x,y,x + (img.getWidth(null)/60),
                y+img.getHeight(null),angle*64,0,
                angle*64+64, img.getHeight(null),obs);
    }


    @Override
    public boolean collision(int x, int y, int width, int height) {
       // box = new Rectangle(this.x, this.y, this.width, this.height);
        return false;
    }
    public void updateMove() {
        if (moveUp) {
            x += speed * Math.cos(Math.toRadians(6 * angle));
            y -= speed * Math.sin(Math.toRadians(6 * angle));
        }
        if (moveDown) {
            x -= speed * Math.cos(Math.toRadians(6 * angle));
            y += speed * Math.sin(Math.toRadians(6 * angle));
        }
        if (moveRight)
            angle -= 1;
        if (moveLeft)
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
                    //shoot(this);
                }

            }
        } else if (ge.getType() == 2) {
                String msg = (String) ge.getEvent();
                if (msg.equals("Explosion")) {
                    System.out.println("Explosion! Reduce Health");
                }
            }

        }


    }

