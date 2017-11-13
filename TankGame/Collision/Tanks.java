/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Collision;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author jack
 */
public class Tanks extends Collision implements Observer{
    int  speed, spawnX , spawnY, width, height;
    Image tank;
    boolean left, right,up, down;
    Rectangle box;

    public Tanks(Image tank, int x,int y, int s) {
        this.tank = tank;
        this.spawnX = x;
        this.spawnY = y;
        this.speed = s;
        this.width = tank.getWidth(null);
        this.height = tank.getHeight(null);
        
        
    }
    
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(tank, spawnX, spawnY, obs);
    }


    @Override
    public boolean collision(int left, int right, int top, int bottom) {
       // box = new Rectangle(this.x, this.y, this.width, this.height);
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if(ge.type == 1) {
            KeyEvent e = (KeyEvent) ge.event;
            switch (e.getKeyCode()) {    
                case KeyEvent.VK_LEFT:
                        spawnX -= speed;
	        	break; 
                    case KeyEvent.VK_RIGHT:
                        spawnX += speed;
	        	break;
                    case KeyEvent.VK_UP:
                        spawnY -= speed;
	        	break; 
                    case KeyEvent.VK_DOWN:
                        spawnY += speed;
	        	break;
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
                default:
                    if(e.getKeyChar() == ' ') {
                        System.out.println("Fire");  
                    }
            }
        }else if(ge.type == 2) {
            String msg = (String)ge.event;
            if(msg.equals("Explosion")) {
                System.out.println("Explosion! Reduce Health");        
            }
        }
        
    }
    
    
    
}
