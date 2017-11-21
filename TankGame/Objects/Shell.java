/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;
import TankGame.*;
/**
 *
 * @author jack
 */
public class Shell extends GameObject{
    private int damage, speed, angle;
    private Tanks tank;

    boolean show;
    public Shell(Image img, int x, int y, int speed,int damage, int width, int height,Tanks tank){
        super(img, x, y, speed,width,height );
        this.width = img.getWidth(null)/60;
        this.height = img.getHeight(null);
        this.damage = damage;
        this.speed = speed;
        this.tank = tank;
        angle = tank.getAngle();
        show = true;

    }
    public void stopShowing(){
        show = false;
    }

    public void update(){
        x += speed * Math.cos( Math.toRadians( 6 * angle ) );
        y -= speed * Math.sin( Math.toRadians( 6 * angle ) );
    }
    @Override
    public void draw (ImageObserver obs, Graphics2D g2){
        g2.drawImage(img, x, y, x + (img.getWidth(null) / 60),
                    y + img.getHeight(null), angle * 24, 0, angle * 24 + 24,
                    img.getHeight(null), obs);

        update();
    }
    public boolean getShow(){
        return show;
    }

    
}
