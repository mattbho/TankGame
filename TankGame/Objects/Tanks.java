/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import TankGame.GameEvents;
import TankGame.GameFrame;
import TankGame.GameFrame.State;
import TankGame.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author jack
 */
public class Tanks extends GameObject {
    private int health, lives, up, down, left, right, angle = 0, shotCoolDown = 0, shotRate, shotButton, cooldown = 0;
    private boolean moveUp, moveDown, moveLeft, moveRight, shot;
    private Image shellImage;
    private ArrayList<Shell> bulletList = new ArrayList<>();
    private int ox,oy,oangle=0 ;
    public Tanks(Image tank, int x, int y, int speed,
             int up, int down, int left, int right, int shotButton, int angle) {
        super(tank, x, y, speed);
        ox = x;
        oy = y;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.health = 100;
        this.lives = 3;
        this.width = tank.getWidth(null)/60;
        //this.height = h - 90;
        this.angle = angle;
        oangle=angle;
        shotRate = 20;
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
        if(!isDead()){
            if(health > 0 && cooldown == 0){
                g2.drawImage(img, x, y, x + (img.getWidth(null) / 60),
                        y + img.getHeight(null), angle * 64, 0,
                        angle * 64 + 64, img.getHeight(null), obs);

            }else if(cooldown == 0){
                addExplosion(GameFrame.getExplosionLarge(),this.x,this.y);
                cooldown = 50;
                angle = oangle;
                x=ox;
                y=oy;
                health=100;
                lives--;
                SoundPlayer.player("TankGame/Resource/Explosion_large.wav", false);
            } else{
                cooldown--;
            }
        }else{
            
            GameFrame.getP1().health = 100;
            GameFrame.getP2().health = 100;
            GameFrame.getP1().lives = 3;
            GameFrame.getP2().lives = 3;
            GameFrame.getP1().x = GameFrame.getP1().ox;
            GameFrame.getP2().x = GameFrame.getP2().ox;
            GameFrame.getP1().y = GameFrame.getP1().oy;
            GameFrame.getP2().y = GameFrame.getP2().oy;
            GameFrame.setState(State.Menu);
        }
        if(GameFrame.getP1().cooldown == 0 && GameFrame.getP2().cooldown ==0) {
            if (GameFrame.getP1().collision(GameFrame.getP2().x, GameFrame.getP2().y, GameFrame.getP2().width, GameFrame.getP2().height)) {
                if (GameFrame.getP1().x > this.x) {
                    GameFrame.getP1().x += speed;
                    GameFrame.getP2().x -= speed;
                }
                if (GameFrame.getP1().x < this.x) {
                    GameFrame.getP1().x -= speed;
                    GameFrame.getP2().x += speed;
                }
                if (GameFrame.getP1().y > this.y) {
                    GameFrame.getP1().y += speed;
                    GameFrame.getP2().y -= speed;
                }
                if (GameFrame.getP1().y < this.y) {
                    GameFrame.getP1().y -= speed;
                    GameFrame.getP2().y += speed;
                }
            }
        }
        
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
                if(e.getID() == KeyEvent.KEY_PRESSED ){                    
                    shoot();
                    shotCoolDown = shotRate;
                    
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
        if(!isDead()&&shotCoolDown<=0) {
            Shell playerShell;
            playerShell = new Shell(shellImage, this.x + (img.getWidth(null) / 120) - 10,
                    this.y + 5 + img.getHeight(null) / 2 - 20, 10, 25, this);
            this.bulletList.add(playerShell);
        }
    }

    public int getAngle() {
        return this.angle;
    }

    public ArrayList<Shell> getBulletList(){
        return bulletList;
    }
    public void tankDamage(int damage){
        health -= damage;
    }
    
    public boolean isDead(){
        return (lives<0);
    }
    public int getCooldown(){
        return cooldown;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHealth(){
        return health;
    }
    public int getLives(){
        return lives;
    }
}

