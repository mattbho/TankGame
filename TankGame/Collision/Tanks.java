/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Collision;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author jack
 */
public class Tanks extends Collision implements Observer{
    int left, right, up, down, shoot, spawnX , spawnY, width, height;
    Image tank;
    
    Rectangle box;
    
    public Tanks(Image tank, int x,int y, int s) {
        this.tank = tank;
        this.spawnX = x;
        this.spawnY = y;
        this.shoot = s;
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
    public void music() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        Controls con = new Controls();
        con.update(o, arg);
    }
    
}
