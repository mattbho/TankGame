/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import TankGame.GameFrame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Jack
 */
public class SpeedPower extends GameObject{
     public SpeedPower(Image img, int x, int y){
         super(img, x,y);         
     }
     
     public void draw(ImageObserver obs, Graphics2D g2){
         g2.drawImage(img,x,y,obs);
         this.update();
     }
     
     public void update(){
         if(GameFrame.getP1().collision(this.x,this.y,this.width,this.height)){
             GameFrame.getP1().SpeedIncrease(); 
         }
         if(GameFrame.getP2().collision(this.x,this.y,this.width,this.height)){
             GameFrame.getP2().SpeedIncrease(); 
         }
     }
     
}
