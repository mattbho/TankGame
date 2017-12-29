/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;

public class StartMenu{
    Image Title;
    public void Menu(){
        try{
            Title = ImageIO.read(new File("TankGame/Resource/Title.bmp"));
        }catch(Exception e){}
        
    }
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(Title, TankMain.getX()/2-Title.getWidth(null)/2, 100, obs);
        g2.drawRect(TankMain.getX()/2-100, 450, 200, 50);
        g2.setFont(new Font("arial", Font.BOLD, 30));
        g2.drawString("START", TankMain.getX()/2-50, 485);
        g2.drawRect(110, 600, 50, 50);
        g2.drawString("A", 125, 635);
        g2.drawRect(165, 600, 50, 50);
        g2.drawString("S", 180, 635);
        g2.drawRect(220, 600, 50, 50);
        g2.drawString("D", 235, 635);
        g2.drawRect(165, 545, 50, 50);
        g2.drawString("W", 175, 580);
        g2.drawRect(165, 675, 50, 50);
        g2.drawString("C", 180, 710);
        g2.drawRect(600, 600, 50, 50);
        g2.drawString("\u2190", 615, 635);
        g2.drawRect(655, 600, 50, 50);
        g2.drawString("\u2193", 670, 635);
        g2.drawRect(710, 600, 50, 50);
        g2.drawString("\u2192", 725, 635);
        g2.drawRect(655, 545, 50, 50);
        g2.drawString("\u2191", 670, 580);
        g2.drawRect(635, 675, 105, 50);
        g2.drawString("PG DN", 640, 710);
    }

}
