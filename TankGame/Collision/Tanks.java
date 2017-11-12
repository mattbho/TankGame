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
import java.net.URL;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author jack
 */
public class Tanks extends Collision{
    int left, right, up, down, shoot, spawnX , spawnY, width, height;
    
    Rectangle box;
    public Tanks(Image tankImg, int x,int y, int l, int r, int u, int d, int s) {
        this.left = l;
        this.right = r;
        this.up = u;
        this.down = d;
        this.spawnX = x;
        this.spawnY = y;
        this.shoot = s;
        this.width = tankImg.getWidth(null);
        this.height = tankImg.getHeight(null);
        
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
