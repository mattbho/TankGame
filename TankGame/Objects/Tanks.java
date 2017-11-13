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
public class Tanks extends GameObject implements Observer{
    private int  health, lives, up, down, left, right;


    public Tanks(Image tank, int x,int y, int speed, int health, int lives, int up, int down, int left, int right) {
        super(tank,x,y,speed);
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.health = health;
        this.lives = lives;

        
        
    }
    @Override
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(getImg(), getX(), getY(), obs);
    }


    @Override
    public boolean collision(int x, int y, int width, int height) {
       // box = new Rectangle(this.x, this.y, this.width, this.height);
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if(ge.getType() == 1) {
            KeyEvent e = (KeyEvent) ge.getEvent();
            int keyCode = e.getKeyCode();
            //System.out.println(keyCode);
            //System.out.println(left + " " + right + " "+ up + " "+ down+ " "+fire);
            if( keyCode == left) {
                if(x > 0)
                    x -= speed;
            }
            else if(keyCode == right){
                if(x < 570)
                    x += speed;
            }
            else if(keyCode == up){
                if(y > 0)
                    y -= speed;
            }
            else if(keyCode == down){
                if(y < 400)
                    y += speed;
            }
            //else if(keyCode == fire) {
             //   fire();
            //}

        }
                /**case KeyEvent.VK_LEFT:
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                        left = true;
                    else if(e.getID() == KeyEvent.KEY_RELEASED)
                        left = false;
                    break; 
                case KeyEvent.VK_RIGHT:
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                        right = true;
                    else if(e.getID() == KeyEvent.KEY_RELEASED)
                        right = false;
                    break;
                case KeyEvent.VK_UP:
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                        up = true;
                    else if(e.getID() == KeyEvent.KEY_RELEASED)
                        up = false;	        
                    break; 
                case KeyEvent.VK_DOWN:
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                        down = true;
                    else if(e.getID() == KeyEvent.KEY_RELEASED)
                        down = false;
                    break;*/


        else if(ge.getType() == 2) {
            String msg = (String)ge.getEvent();
            if(msg.equals("Explosion")) {
                System.out.println("Explosion! Reduce Health");        
            }
        }
        
    }
    
    
    
}
