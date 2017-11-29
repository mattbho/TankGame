/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import TankGame.GameFrame;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author jack
 */
public class Wall extends GameObject{
    Boolean breakable;
    private int width,height,cooldown;
    private Rectangle wall;
    
    public Wall(Image img, int x, int y, Boolean canBreak){
        super(img,x,y, 0);
        this.breakable = canBreak;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        System.out.println(height);
    }
    public boolean isBreakable(){
        return this.breakable;
    }
    
    
    @Override
    public void draw(ImageObserver obs, Graphics2D g){
        if(cooldown == 0) {
            g.drawImage(img, x, y, obs);
            update();
        }
        else{
            cooldown--;
        }
    }
    public int getCool(){
        return cooldown;
    }
    public void breakWall(){
        cooldown = 800;
    }
    public int getCooldown(){
        return cooldown;
    }
    
    public void update(){
        if(Shell.isShowing()==false){
            if ((GameFrame.getP1()).collision(this.x, this.y, this.width, this.height)){
                if(GameFrame.getP1().x>(this.x)){ 
                    GameFrame.getP1().x+=3;
                }else if(GameFrame.getP1().x<(this.x)){
                    GameFrame.getP1().x-=3;
                }
                if(GameFrame.getP1().y>(this.y)){
                    GameFrame.getP1().y+=3;
                }else if(GameFrame.getP1().y<this.y){
                    GameFrame.getP1().y-=3;
                }
            }
            if (GameFrame.getP2().collision(this.x, this.y, width, height)){
                if(GameFrame.getP2().x>this.x){
                    GameFrame.getP2().x+=3;
                }else if(GameFrame.getP2().x<this.x){
                    GameFrame.getP2().x-=3;
                }
                if(GameFrame.getP2().y>this.y){
                    GameFrame.getP2().y+=3;
                }else if(GameFrame.getP2().y<(this.y)){
                    GameFrame.getP2().y-=3;
                }
            }
        }
    }
}
