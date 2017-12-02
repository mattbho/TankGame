/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;
import TankGame.GameFrame;
import TankGame.SoundPlayer;

/**
 *
 * @author jack
 */
public class Shell extends GameObject{
    private int damage, speed, angle;
    private Tanks tank;

    static boolean show;
    public Shell(Image img, int x, int y, int speed,int damage,Tanks tank){
        super(img, x, y, speed);
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
    public static boolean isShowing(){
        return show;
    }

    public void update(){
        x += speed * Math.cos( Math.toRadians( 6 * angle ) );
        y -= speed * Math.sin( Math.toRadians( 6 * angle ) );

        if(GameFrame.getP1().collision(this.x,this.y,this.width,this.height) && isShowing()
                && this.tank != GameFrame.getP1() && GameFrame.getP1().getCooldown() == 0){
            stopShowing();
            GameFrame.getP1().tankDamage(this.damage);
            addExplosion(GameFrame.getExplosionSmall(),this.x,this.y);
            SoundPlayer.player("TankGame/Resource/Explosion_small.wav", false);
        }else if(GameFrame.getP2().collision(this.x,this.y,this.width,this.height) && isShowing()
                && this.tank != GameFrame.getP2() && GameFrame.getP2().getCooldown() == 0){
            stopShowing();
            GameFrame.getP2().tankDamage(this.damage);
            addExplosion(GameFrame.getExplosionSmall(),this.x,this.y);
            SoundPlayer.player("TankGame/Resource/Explosion_small.wav", false);
        } else{
            for(int i = 0; i <GameFrame.getBreakwall().size() - 1; i++){
                Wall temp = GameFrame.getBreakwall().get(i);
                if(temp.collision(this.x,this.y,this.width,this.height) && temp.getCool() ==0){
                    temp.breakWall();
                    stopShowing();
                    addExplosion(GameFrame.getExplosionSmall(),temp.x,temp.y);
                    SoundPlayer.player("TankGame/Resource/Explosion_small.wav", false);

                }
            }
            for(int i = 0; i <GameFrame.getSolidwall().size() -1; i++){
                Wall temp = GameFrame.getSolidwall().get(i);
                if(temp.collision(this.x,this.y,this.width,this.height)){
                    stopShowing();
                    addExplosion(GameFrame.getExplosionSmall(),x, y);
                    SoundPlayer.player("TankGame/Resource/Explosion_small.wav", false);

                }
            }

        }
    }
    @Override
    public void draw (ImageObserver obs, Graphics2D g2){
        g2.drawImage(img, x, y, x + (img.getWidth(null) / 60),
                    y + img.getHeight(null), angle * 24, 0, angle * 24 + 24,
                    img.getHeight(null), obs);

        update();
    }    
}
