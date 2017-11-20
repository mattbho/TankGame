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
    private int width,height;
    private Rectangle wall;
    
    public Wall(Image img, int x, int y, Boolean canBreak){
        super(img,x,y);
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
        g.drawImage(img, x, y , obs);
    }
    
    public void update(){
       /* if ((GameFrame.getP1()).collision(this.x, this.y, width, height)){
            if(GameFrame.getP1().x>(x)){ // wont work if i did tankgame.p1.x>(x+this.width), since it will think that my tank is < the side, and it will not bounce back.
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
            if(GameFrame.getP2().x>(x)){
		GameFrame.getP2().x+=3;
            }else if(GameFrame.getP2().x<(this.x)){
                GameFrame.getP2().x-=3;
            }
            if(GameFrame.getP2().y>(this.y)){
		GameFrame.getP2().y+=3;
            }else if(GameFrame.getP2().y<this.y){
		GameFrame.getP2().y-=3;
            }
        }*/
    }
}
