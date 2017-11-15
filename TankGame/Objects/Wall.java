/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 *
 * @author jack
 */
public class Wall extends GameObject{
    Boolean breakable;
    private int width,height;
    private Rectangle wall;
    public Wall(Image img, int x, int y, int speed, Boolean canBreak){
        super(img,x,y,speed);
        this.breakable = canBreak;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        wall = new Rectangle(x,y,width,height);
    }
    public boolean isBreakable(){
        return this.breakable;
    }
    @Override
    public void draw(ImageObserver obs, Graphics2D g){
        g.drawImage(getImg(), x, y , obs);
    }
    public void update(){

    }
}
