/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Objects;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Matt
 */
public abstract class GameObject implements Observer {
    protected Image img;
    Rectangle box;
    protected int x, y, width, height, speed;
    private boolean collide = false;

    public GameObject(Image img, int x, int y, int speed){
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.speed = speed;

    }
    public GameObject(Image img, int x, int y){
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    
    
    public boolean collision(int x, int y, int width, int height){
        box = new Rectangle(this.x,this.y,this.width,this.height);
        Rectangle box2 = new Rectangle(x,y,width,height);
        if(this.box.intersects(box2)){
            collide = true;
        }
        return collide;
    }

    public void update(Observable o, Object arg){

    }
    public void draw(ImageObserver obs, Graphics2D g2){


    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;

    }
    public int getHeight(){
        return height;
    }
    public int getSpeed(){
        return speed;
    }
    public Image getImg(){
        return img;
    }
}
